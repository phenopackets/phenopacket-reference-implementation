package org.phenopackets.api.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class AbstractSchemaTest {
	
	protected void writeSchema(String fn, String info) throws IOException {
		FileUtils.writeStringToFile(new File("target/"+fn), info);
	}

}
