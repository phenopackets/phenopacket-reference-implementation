[![Build Status](https://travis-ci.org/phenopackets/phenopacket-reference-implementation.svg?branch=master)](https://travis-ci.org/phenopackets/phenopacket-reference-implementation)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.phenopackets/phenopackets-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.phenopackets/phenopackets-api)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/org.phenopackets/phenopackets-api/badge.svg)](http://www.javadoc.io/doc/org.phenopackets/phenopackets-api)

# Phenopackets/PXF Reference Implementation

This project provides a reference java implementation for the Phenotype eXchange Format (PXF). Jackson annotations are 
used to describe the mapping to the JSON serialization of PXF. We are also experimenting with generating the JSON Schema 
from the reference implementation.

See [Phenopcket-format repo](https://github.com/phenopackets/phenopacket-format) and the [src/test/resources](https://github.com/phenopackets/phenopacket-reference-implementation/tree/master/src/test/resources) package of this repo for examples of 
the input and output formats. [Phenopcket-format wiki](https://github.com/phenopackets/phenopacket-format/wiki) contains more details on the project 
in general.

# Including phenopackets-api in your code:
## Maven
```xml
    <dependency>
        <groupId>org.phenopackets</groupId>
        <artifactId>phenopackets-api</artifactId>
        <version>${project.version}</version>
    </dependency>
```

## Gradle
```groovy
    compile 'org.phenopackets:phenopackets-api:${project.version}'
```

# Using it

[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/org.phenopackets/phenopackets-api/badge.svg)](http://www.javadoc.io/doc/org.phenopackets/phenopackets-api)

### Reading a JSON/YAML file
```java
PhenoPacket phenoPacket = YamlReader.readFile("phenopacket.yaml");
```

### Creating a Phenopacket
Where possible the Builder pattern is used to provide a fluent API for building objects and create immutable instances. 
The PhenoPacket class is immutable, although instances of classes from the org.phenopackets.api.model.condition and 
org.phenopackets.api.model.entity package are not.

```java
PhenoPacket phenoPacket = new PhenoPacket.Builder()
                .id("PXF:000001")
                .title("Empty phenopacket")
                .build();
```
### Writing to JSON/YAML
```java
PhenoPacket phenoPacket ...
String yamlString = YamlGenerator.render(phenoPacket);
```
