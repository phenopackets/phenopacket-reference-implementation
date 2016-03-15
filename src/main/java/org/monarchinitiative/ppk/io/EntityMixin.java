package org.monarchinitiative.ppk.io;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.monarchinitiative.ppk.model.entity.*;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = Disease.class, name = "disease"),
        @Type(value = Person.class, name = "person"),
        @Type(value = Variant.class, name = "variant"),
        @Type(value = GenomicEntity.class, name = "genomic_entity"),
        @Type(value = Organism.class, name = "organism") })
abstract class EntityMixin {

}
