package org.phenopackets.api.io;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.util.ContextUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jsonldjava.core.JsonLdError;

public class RDFGenerator {

	public static Model toRDF(PhenoPacket packet)
			throws JsonProcessingException, JsonLdError {
		PhenoPacket packetWithContext;
		if (packet.getContext() == null) {
			packetWithContext = new PhenoPacket.Builder()
					.setContext(
							ContextUtil.getDefaultContext().getPrefixes(false))
					.id(packet.getId())
					.title(packet.getTitle())
					.setDiseaseOccurrenceAssociations(
							packet.getDiseaseOccurrenceAssociations())
					.setDiseases(packet.getDiseases())
					.setEnvironmentAssociations(
							packet.getEnvironmentAssociations())
					.setOrganisms(packet.getOrganisms())
					.setPersons(packet.getPersons())
					.setVariantAssociations(packet.getVariantAssociations())
					.setVariants(packet.getVariants()).build();
		} else {
			packetWithContext = packet;
		}
		Model rdfModel = ModelFactory.createDefaultModel();
		StringReader reader = new StringReader(
				JsonGenerator.render(packetWithContext));
		RDFDataMgr.read(rdfModel, reader, null, Lang.JSONLD);
		return rdfModel;
	}

	public static String render(PhenoPacket packet, Lang format)
			throws JsonProcessingException, JsonLdError {
		StringWriter writer = new StringWriter();
		RDFDataMgr.write(writer, toRDF(packet), format);
		return writer.toString();
	}
}
