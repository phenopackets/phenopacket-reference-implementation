package org.phenopackets.api.io;

import java.io.StringReader;
import java.io.StringWriter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.phenopackets.api.PhenoPacket;

import com.fasterxml.jackson.core.JsonProcessingException;

public class RDFGenerator {

	public static Model toRDF(PhenoPacket packet)
			throws JsonProcessingException {
		Model rdfModel = ModelFactory.createDefaultModel();
		StringReader reader = new StringReader(JsonGenerator.render(packet));
		RDFDataMgr.read(rdfModel, reader, null, Lang.JSONLD);
		return rdfModel;
	}

	public static String render(PhenoPacket packet, Lang format)
			throws JsonProcessingException {
		StringWriter writer = new StringWriter();
		RDFDataMgr.write(writer, toRDF(packet), format);
		return writer.toString();
	}
}
