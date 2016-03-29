[![Build Status](https://travis-ci.org/phenopackets/phenopacket-reference-implementation.svg?branch=master)](https://travis-ci.org/phenopackets/phenopacket-reference-implementation)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.phenopackets/phenopackets-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.phenopackets/phenopackets-api)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/org.phenopackets/phenopackets-api/badge.svg)](http://www.javadoc.io/doc/org.phenopackets/phenopackets-api)

# Phenopackets/PXF Reference Implementation

This project provides a reference java implementation for the Phenotype eXchange Format (PXF). Jackson annotations are 
used to describe the mapping to the JSON serialization of PXF. We are also experimenting with generating the JSON Schema 
from the reference implementation.

See [phenopacket-format repo](https://github.com/phenopackets/phenopacket-format) and the [src/test/resources](https://github.com/phenopackets/phenopacket-reference-implementation/tree/master/src/test/resources) package of this repo for examples of 
the serialised JSON and YAML formats. The phenopacket-format repo [wiki](https://github.com/phenopackets/phenopacket-format/wiki) contains more details on the project 
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
Most of these examples have been taken from the [test](https://github.com/phenopackets/phenopacket-reference-implementation/tree/master/src/test) package.
Check there for more thorough examples. 

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
### Entities
Entities refer to things such as individuals of an organism/people, variants or diseases. For example here is a new Person:
```java
Person person = new Person();
person.setId("person#1");
person.setLabel("Joe Bloggs");
person.setSex("M");
```
here is the disease Pfeiffer syndrome
```java
Disease disease = new Disease();
disease.setId("OMIM:101600");
disease.setLabel("Pfeiffer syndrome");
List<OntologyClass> phenotypes = ImmutableList.of(
        OntologyClass.of("HP:0000272", "Malar flattening"),
        OntologyClass.of("HP:0005347", "Cartilaginous trachea"),
        OntologyClass.of("HP:0001249", "Intellectual disability"),
        OntologyClass.of("HP:0005048", "Synostosis of carpal bones"),
        OntologyClass.of("HP:0004440", "Coronal craniosynostosis"),
        OntologyClass.of("HP:0001156", "Brachydactyly syndrome")
);
disease.setTypes(phenotypes);
```

### Conditions
A Condition is usually defined with a phenotype, location, time of onset/offset
the severity and the environment in which this condition was observed. A condition could simply be a phenotype - here is 
one from the [HPO](http://human-phenotype-ontology.org), with a negative type too (i.e. this was tested for and not observed).
```java
Phenotype phenotype = new Phenotype();
phenotype.setTypes(ImmutableList.of(
        OntologyClass.of("HP:0000272", "Malar flattening")
));
phenotype.setNegatedTypes(ImmutableList.of(
        OntologyClass.of("HP:0001249", "Intellectual disability")
));
```
or an occurrence of a disease
```java
DiseaseOccurrence diseaseOccurrence = new DiseaseOccurrence();
DiseaseStage stage = new DiseaseStage();
stage.setDescription("Childhood onset");
stage.setTypes(ImmutableList.of(
        OntologyClass.of("HP:0011463", "Childhood onset")
));
diseaseOccurrence.setStage(stage);
```

Typically an observation is accompanied with some kind of evidence attribution - here we have a journal
```java
Evidence journalEvidence = new Evidence();
journalEvidence.setTypes(ImmutableList.of(OntologyClass.of("ECO:0000033", "TAS")));
Publication pub = new Publication.Builder().setId("PMID:23455423").build();
journalEvidence.setSupportingPublications(ImmutableList.of(pub));
```

### Associating Entities with Conditions
Entities can have associated Conditions.Entities and Conditions are linked by an Association.
Associations are also immutable objects which use a Builder. 

Here we're going to associate the phenotype with the person from the previous examples.
```java
PhenotypeAssociation patientPhenotypeAssociation = new PhenotypeAssociation.Builder(phenotype)
        .setEntity(person)
        .addEvidence(journalEvidence)
        .build();
```
and the Disease with the DiseaseAssociation.
```java
DiseaseOccurrenceAssociation association = new DiseaseOccurrenceAssociation.Builder(diseaseOccurrence)
        .setEntity(disease)
        .build();
```
### Adding Entities and Associations to a PhenoPacket
Using the API it s now trivial to create a phenopacket containing the person and their observed phenotype. 
```java
PhenoPacket pk = new PhenoPacket.Builder()
                .id(id)
                .title("Patient with a phenotype")
                .addPerson(person)
                .addPhenotypeAssociation(patientPhenotypeAssociation)
                .build();
```
or a disease as characterised by its associated pehnotypes and its time of onset
```java
PhenoPacket pk = new PhenoPacket.Builder()
                .id(id)
                .title("Description of a disease and its time of onset")
                .addDisease(disease)
                .addDiseaseOccurrenceAssociation(diseaseOccurrenceAssociation)
                .build();
```

### Writing to JSON/YAML
```java
PhenoPacket phenoPacket ...
String yamlString = YamlGenerator.render(phenoPacket);
```
