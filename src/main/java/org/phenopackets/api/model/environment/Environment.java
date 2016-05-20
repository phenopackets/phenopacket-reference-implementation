package org.phenopackets.api.model.environment;

import java.util.List;

import org.phenopackets.api.model.condition.Measurement;
import org.phenopackets.api.model.condition.TemporalRegion;
import org.phenopackets.api.model.ontology.ClassInstance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * An instance of any kind of environmental exposure. Here environment is defined broadly,
 * and can include things as diverse as:
 * <p/>
 * - a history of smoking
 * - living in a food desert
 * - taking a particular type of drug at a certain regularity of a time interval
 * - diet
 * - microbiome
 * 
 * We follow the CTD model, of an exposure event as an interaction between
 * a stressor and receptor
 *
 * @author cjm
 */
public class Environment extends ClassInstance {
    
    @JsonProperty("onset")
    @JsonPropertyDescription("the time region in which the exposure is first manifest")
    private TemporalRegion timeOfOnset;

    @JsonProperty("offset")
    @JsonPropertyDescription("the time region in which the exposure ceases to manifest")
    private TemporalRegion timeOfFinishing;

    private ClassInstance stressor;
    private ClassInstance transportPath;
    private List<Measurement> measurements;

    
    /**
     * @return the stressor
     */
    public ClassInstance getStressor() {
        return stressor;
    }
    /**
     * @param stressor the stressor to set
     */
    public void setStressor(ClassInstance stressor) {
        this.stressor = stressor;
    }
    /**
     * @return the transportPath
     */
    public ClassInstance getTransportPath() {
        return transportPath;
    }
    /**
     * @param transportPath the transportPath to set
     */
    public void setTransportPath(ClassInstance transportPath) {
        this.transportPath = transportPath;
    }

}
