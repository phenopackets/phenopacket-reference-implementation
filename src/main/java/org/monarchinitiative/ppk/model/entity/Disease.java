package org.monarchinitiative.ppk.model.entity;

import org.monarchinitiative.ppk.model.ontology.ClassInstance;

import java.util.Objects;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class Disease extends ClassInstance implements Entity {

    private String id;
    private String label;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public EntityType getType() {
        return EntityType.DISEASE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return Objects.equals(id, disease.id) &&
                Objects.equals(label, disease.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
