package org.phenopackets.api.model.entity;


/**
 * @author cjm
 */
public class Person extends Organism {

    @Override
    public EntityType getType() {
        return EntityType.PERSON;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + super.getId() + '\'' +
                ", label='" + super.getLabel() + '\'' +
                ", taxon=" + super.getTaxon() +
                ", strain=" + super.getStrain() +
                ", sex='" + super.getSex() + '\'' +
                ", dateOfBirth='" + super.getDateOfBirth() + '\'' +
                '}';
    }
}
