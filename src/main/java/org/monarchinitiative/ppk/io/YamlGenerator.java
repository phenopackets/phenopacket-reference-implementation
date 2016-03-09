package org.monarchinitiative.ppk.io;

import org.monarchinitiative.ppk.model.condition.Phenotype;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

public class YamlGenerator {
	
	public String render(Object obj) {
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);

		
		Constructor constructor = new Constructor();
		constructor.addTypeDescription(new TypeDescription(Phenotype.class, "!test"));
		Yaml yaml = new Yaml(constructor, representer);
		return yaml.dump(obj);
	}

}
