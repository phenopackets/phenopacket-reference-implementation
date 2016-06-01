package org.phenopackets.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.phenopackets.api.PhenoPacket;

import com.github.jsonldjava.core.Context;
import com.github.jsonldjava.core.JsonLdApi;
import com.github.jsonldjava.core.JsonLdError;

public class ContextUtil {

	public static final String defaultContextURI = "http://phenopackets.org/context";

	/**
	 * @param packet
	 *            A PhenoPacket
	 * @return JSON-LD context for this PhenoPacket. If the PhenoPacket has a
	 *         `@context` key, its value will be used to create the JSON-LD
	 *         context. Otherwise, the default PhenoPacket context will be
	 *         returned.
	 * @throws JsonLdError
	 */
	public static Context getJSONLDContext(PhenoPacket packet)
			throws JsonLdError {
		if (packet.getContext() != null) {
			return new Context().parse(packet.getContext());
		} else {
			return getDefaultContext();
		}
	}

	/**
	 * @return The latest PhenoPacket default JSON-LD context. The JSON-LD API
	 *         will obtain this automatically via the classpath (see
	 *         'jarcache.json').
	 * @throws JsonLdError
	 */
	public static Context getDefaultContext() throws JsonLdError {
		return new Context().parse(defaultContextURI);
	}

	/**
	 * Use a context obtained from the provided PhenoPacket to expand the given
	 * identifier, assuming the identifier is used in a document as a value for
	 * "@id". Depending on the context, the result may or may not be a valid
	 * IRI.
	 * 
	 * @param identifier
	 * @param packet
	 * @return
	 * @throws JsonLdError
	 */
	public static String expandIdentifierAsValue(String identifier,
			PhenoPacket packet) throws JsonLdError {
		Context context = getJSONLDContext(packet);
		return expandIdentifierAsValue(identifier, context);
	}

	/**
	 * Use the provided JSON-LD context to expand the given identifier, assuming
	 * the identifier is used in a document as a value for "@id". Depending on
	 * the context, the result may or may not be a valid IRI.
	 * 
	 * @param identifier
	 * @param context
	 *            JSON-LD context
	 * @return
	 * @throws JsonLdError
	 */
	public static String expandIdentifierAsValue(String identifier,
			Context context) throws JsonLdError {
		// This is very convoluted due to how jsonld-api works
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("@id", identifier);
		// A value for @type is required to make sure the value of @id is
		// treated as a resource
		jsonMap.put("@type", "http://example.org/faketype");
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) (new JsonLdApi()
				.expand(context, jsonMap));
		return result.get("@id").toString();
	}

	/**
	 * Use a context obtained from the provided PhenoPacket to expand the given
	 * identifier, assuming the identifier is used in a document as a property.
	 * Depending on the context, the result may or may not be a valid IRI.
	 * 
	 * @param identifier
	 * @param packet
	 * @return
	 * @throws JsonLdError
	 */
	public static String expandIdentifierAsPropertyOrType(String identifier,
			PhenoPacket packet) throws JsonLdError {
		Context context = getJSONLDContext(packet);
		return expandIdentifierAsPropertyOrType(identifier, context);
	}

	/**
	 * Use the provided JSON-LD context to expand the given identifier, assuming
	 * the identifier is used in a document as a property. Depending on the
	 * context, the result may or may not be a valid IRI.
	 * 
	 * @param identifier
	 * @param context
	 * @return
	 * @throws JsonLdError
	 */
	public static String expandIdentifierAsPropertyOrType(String identifier,
			Context context) throws JsonLdError {
		// This is very convoluted due to how jsonld-api works
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put(identifier, "ignore this value");
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) (new JsonLdApi()
				.expand(context, jsonMap));
		return result.keySet().iterator().next();
	}

	/**
	 * Use a context obtained from the provided PhenoPacket to compact the given
	 * identifier, assuming the identifier is used in a document as a value for
	 * "@id".
	 * 
	 * @param identifier
	 * @param packet
	 * @return
	 * @throws JsonLdError
	 */
	public static String compactIdentifierAsValue(String identifier,
			PhenoPacket packet) throws JsonLdError {
		Context context = getJSONLDContext(packet);
		return compactIdentifierAsValue(identifier, context);
	}

	/**
	 * Use the provided JSON-LD context to compact the given identifier,
	 * assuming the identifier is used in a document as a value for "@id".
	 * 
	 * @param identifier
	 * @param context
	 * @return
	 * @throws JsonLdError
	 */
	public static String compactIdentifierAsValue(String identifier,
			Context context) throws JsonLdError {
		// This is very convoluted due to how jsonld-api works
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("@id", identifier);
		// A value for @type is required to make sure the value of @id is
		// treated as a resource
		jsonMap.put("@type", "http://example.org/faketype");
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) (new JsonLdApi()
				.compact(context, "@type", jsonMap));
		result.remove("@type");
		return result.values().iterator().next().toString();
	}

	/**
	 * Use a context obtained from the provided PhenoPacket to compact the given
	 * identifier, assuming the identifier is used in a document as a property.
	 * 
	 * @param identifier
	 * @param packet
	 * @return
	 * @throws JsonLdError
	 */
	public static String compactIdentifierAsPropertyOrType(String identifier,
			PhenoPacket packet) throws JsonLdError {
		Context context = getJSONLDContext(packet);
		return compactIdentifierAsPropertyOrType(identifier, context);
	}

	/**
	 * Use the provided JSON-LD context to compact the given identifier,
	 * assuming the identifier is used in a document as a property.
	 * 
	 * @param identifier
	 * @param context
	 * @return
	 * @throws JsonLdError
	 */
	public static String compactIdentifierAsPropertyOrType(String identifier,
			Context context) throws JsonLdError {
		// This is very convoluted due to how jsonld-api works
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put(identifier, new ArrayList<>());
		@SuppressWarnings("unchecked")
		Map<String, Object> result = (Map<String, Object>) (new JsonLdApi()
				.compact(context, identifier, jsonMap));
		return result.keySet().iterator().next();
	}

}
