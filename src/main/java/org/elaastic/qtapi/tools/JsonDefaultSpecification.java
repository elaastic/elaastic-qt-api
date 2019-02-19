package org.elaastic.qtapi.tools;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Json based specification
 */
class JsonDefaultSpecification implements JsonSpecification {

    protected HashMap specificationProperties;

    private String jsonString;

    /**
     * Default constructor
     */
    public JsonDefaultSpecification() {
        specificationProperties = new HashMap<String,Object>();
    }

    /**
     * Construct a specification based on the json string description
     * @param jsonString the specification as json string
     */
    public JsonDefaultSpecification(String jsonString) {
        this.jsonString = jsonString;
        ObjectMapper mapper = new ObjectMapper();
        specificationProperties = new HashMap<String, Object>();

        try {
            specificationProperties = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getJsonString() {
        if (validateSpecification()) {
            return jsonString;
        }
        return null;
    }

    /**
     * Validate a specification
     */
    public boolean validateSpecification() {
        return true;
    }


    /**
     * Get the value corresponding to the property name
     * @param propertyName name
     */
    public void getSpecificationProperty(String propertyName) {
        specificationProperties.get(propertyName);
    }

    /**
     * Set a property value
     * @param propertyName the property name
     * @param propertyValue the property value
     */
    public void setSpecificationProperty(String propertyName, Object propertyValue) {
        specificationProperties.put(propertyName, propertyValue);
    }

}
