package org.monarchinitiative.ppk.model.association;

import org.monarchinitiative.ppk.model.condition.Condition;
import org.monarchinitiative.ppk.model.entity.Entity;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class ConditionAssociation<T extends Entity, S extends Condition> {

    private T entity;
    private S condition;

    public ConditionAssociation(T entity, S condition) {
        this.entity = entity;
        this.condition = condition;
    }

    public T getEntity() {
        return entity;
    }

    public S getCondition() {
        return condition;
    }
}
