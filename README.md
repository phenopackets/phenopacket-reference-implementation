[![Build Status](https://travis-ci.org/phenopackets/phenopacket-reference-implementation.svg?branch=master)](https://travis-ci.org/phenopackets/phenopacket-reference-implementation)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.phenopackets/phenopackets-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.phenopackets/phenopackets-api)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/org.phenopackets/phenopackets-api/badge.svg)](http://www.javadoc.io/doc/org.phenopackets/phenopackets-api)

# Phenopackets/PXF Reference Implementation

See https://github.com/phenopackets/phenopacket-format/ and the src/test/resources package of this repo for examples of 
the input and output formats and https://github.com/phenopackets/phenopacket-format/wiki for more details on the project 
in general.

This project provides a reference java implementation for the Phenotype eXchange Format (PXF). Jackson annotations are 
used to describe the mapping to the JSON serialization of PXF. We are also experimenting with generating the JSON Schema 
from the reference implementation.

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

Javadoc: [org.phenopackets/phenopackets-api](http://www.javadoc.io/doc/org.phenopackets/phenopackets-api)

Need some code examples here...

