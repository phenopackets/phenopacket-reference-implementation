package org.phenopackets.api.io;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.UUID;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.phenopackets.api.PhenoPacket;
import org.phenopackets.api.util.ContextUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jsonldjava.core.JsonLdError;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class RdfGenerator {

	/**
	 * Convert a PhenoPacket to RDF triples using the JSON-LD context
	 * 
	 * @param packet
	 * @param base
	 *            URI base for generated RDF; if `null` a UUID-based base will
	 *            be used
	 * @return model containing RDF triples
	 * @throws JsonLdError
	 * @throws JsonProcessingException
	 */
	public static Model toRdf(PhenoPacket packet, String base)
			throws JsonLdError, JsonProcessingException {
		PhenoPacket packetWithContext;
		if (packet.getContext() == null) {
			packetWithContext = PhenoPacket.newBuilder(packet)
                            .context(ContextUtil.defaultContextURI)
                            .build();
		} else {
			packetWithContext = packet;
		}
		Model rdfModel = ModelFactory.createDefaultModel();
		StringReader reader = new StringReader(
				JsonGenerator.render(packetWithContext));
		String baseToUse;
		if (base != null) {
			baseToUse = base;
		} else {
			String uuid = UUID.randomUUID().toString();
			baseToUse = "http://phenopackets.org/local/" + uuid + "/";
		}
		RDFDataMgr.read(rdfModel, reader, baseToUse, Lang.JSONLD);
		return rdfModel;
	}

	/**
	 * Serialize a PhenoPacket as an RDF string
	 * 
	 * @param packet
	 * @param base
	 *            base URI base for generated RDF; if `null` a UUID-based base
	 *            will be used
	 * @param format
	 *            RDF serialization format
	 * @return
	 * @throws JsonLdError
	 * @throws JsonProcessingException
	 */
	public static String render(PhenoPacket packet, String base, Lang format)
			throws JsonLdError, JsonProcessingException {
		StringWriter writer = new StringWriter();
		RDFDataMgr.write(writer, toRdf(packet, base), format);
		return writer.toString();
	}
}
