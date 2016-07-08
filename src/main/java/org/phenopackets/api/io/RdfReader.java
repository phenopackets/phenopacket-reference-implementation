package org.phenopackets.api.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.github.jsonldjava.core.Context;
import com.github.jsonldjava.core.JsonLdError;
import com.google.common.collect.ImmutableList;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;

public class RdfReader {

	public static PhenoPacket readModel(Model model, String packetURI)
			throws JsonMappingException, JsonLdError {
		ObjectNode schema = generateSchema();
		ObjectNode phenopacketJSON = new SchemaProcessor(model, packetURI,
				schema).getJSON();
		return new ObjectMapper().convertValue(phenopacketJSON,
				PhenoPacket.class);
	}

	private static class SchemaProcessor {

		final Map<String, ObjectNode> repeatedStructureMap = new HashMap<>();
		final Context context;
		final Model model;
		final ObjectNode processedJSON;

		public SchemaProcessor(Model model, String packetURI, ObjectNode schema)
				throws JsonLdError {
			this.model = model;
			this.context = ContextUtil.getDefaultContext();
			processSchema(schema, repeatedStructureMap);
			Resource packetResource = ResourceFactory.createResource(packetURI);
			processedJSON = processObjectNode(schema, packetResource);
			processedJSON.put("@context", ContextUtil.defaultContextURI);
		}

		public ObjectNode getJSON() {
			return processedJSON;
		}

		private void processSchema(ObjectNode schemaNode,
				Map<String, ObjectNode> repeatedStructures) {
			if (schemaNode.has("id") && schemaNode.get("id").isTextual()) {
				repeatedStructures.put(schemaNode.get("id").textValue(),
						schemaNode);
			}
			schemaNode.elements().forEachRemaining(el -> {
				if (el.isObject()) {
					processSchema((ObjectNode) el, repeatedStructures);
				}
			});
		}

		private ObjectNode processObjectNode(ObjectNode incomingNode,
				Resource rdfNode) {
			ObjectNode schemaNode;
			if (incomingNode.has("id")) {
				schemaNode = incomingNode;
			} else if (incomingNode.has("$ref")) {
				schemaNode = repeatedStructureMap.get(incomingNode.get("$ref").textValue());
			} else {
				return null;
			}
			ObjectNode packetNode = JsonNodeFactory.instance.objectNode();
			ObjectNode propertiesObj = (ObjectNode) schemaNode.get("properties");
			propertiesObj
					.fieldNames()
					.forEachRemaining(
							field -> {
								if (!field.equals("@context")) {
									List<RDFNode> values;
									if (field.equals("id")) {
										values = ImmutableList.of(ResourceFactory
												.createPlainLiteral(rdfNode.getURI()));
									} else {
										Property property = getProperty(field, context);
										values = getValues(model, rdfNode, property);
									}
									String type = propertiesObj.get(field).get("type").textValue();
									boolean array;
									String valueType;
									ObjectNode valueObject;
									if (type.equals("array")) {
										array = true;
										valueObject = (ObjectNode) propertiesObj.get(field).get("items");
										valueType = valueObject.get("type").textValue();
									} else {
										array = false;
										valueObject = (ObjectNode) propertiesObj.get(field);
										valueType = type;
									}
									Stream<JsonNode> targetValues = Stream.empty();
									if (valueType.equals("string")) {
										targetValues = values
												.stream()
												.map(v -> JsonNodeFactory.instance.textNode(nodeToString(v))); //TODO if v is resource then compact with context
									} else if (valueType.equals("integer")) {
										targetValues = values
												.stream()
												.filter(v -> v.isLiteral())
												.map(v -> JsonNodeFactory.instance.numberNode(v.asLiteral().getInt()));
									} else if (valueType.equals("object")) {
										targetValues = values
												.stream()
												.filter(v -> v.isResource())
												.map(v -> v.asResource())
												.map(v -> processObjectNode(valueObject, v));
									} else {
										logger.error("Didn't account for " + valueType);
									}
									if (array) {
										ArrayNode valuesNode = JsonNodeFactory.instance.arrayNode();
										valuesNode.addAll(targetValues.collect(Collectors.toList()));
										packetNode.set(field, valuesNode);
									} else {
										packetNode.set(field, targetValues.findAny().orElse(null));
									}
								}
							});
			return packetNode;
		}
	}

	private static Property getProperty(String localName, Context context) {
		String expanded = null;
		try {
			if (localName.equals("types")) {
				expanded = RDF.type.getURI();
			} else {
				expanded = ContextUtil.expandIdentifierAsPropertyOrType(
						localName, context);
			}
		} catch (JsonLdError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResourceFactory.createProperty(expanded);
	}

	private static List<RDFNode> getValues(Model model, Resource subject,
			Property property) {
		return model.listObjectsOfProperty(subject, property).toList();
	}

	private static ObjectNode generateSchema() throws JsonMappingException {
		ObjectMapper m = new ObjectMapper();
		m.setFilterProvider(new SimpleFilterProvider().addFilter(
				"PhenoPacketClass", SimpleBeanPropertyFilter.serializeAll()));
		SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
		m.acceptJsonFormatVisitor(m.constructType(PhenoPacket.class), visitor);
		JsonSchema jsonSchema = visitor.finalSchema();
		return (ObjectNode) m.valueToTree(jsonSchema);
	}

	private static String nodeToString(RDFNode node) {
		if (node.isURIResource()) {
			return node.asResource().getURI();
		} else if (node.isLiteral()) {
			return node.asLiteral().getLexicalForm();
		} else {
			return node.toString();
		}
	}

	private static Logger logger = LoggerFactory.getLogger(RdfReader.class);
}
