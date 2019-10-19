package com.carhouse.dao.conditions;

/**
 * The enum Default conditions.
 */
public enum DefaultConditions {
    /**
     * Car make default conditions.
     */
    CAR_MAKE("carMakeId", "%"),
    /**
     * Car model default conditions.
     */
    CAR_MODEL("carModelId", "%"),
    /**
     * Year from default conditions.
     */
    YEAR_FROM("yearFrom", "1999-01-01"),
    /**
     * Year to default conditions.
     */
    YEAR_TO("yearTo", "2019-12-31"),
    /**
     * Price from default conditions.
     */
    PRICE_FROM("priceFrom", "0"),
    /**
     * Price to default conditions.
     */
    PRICE_TO("priceTo", "70000");

    private String key;
    private String defaultValue;

    /**
     * Instantiates a new condition.
     *
     * @param key is used to get value from map
     * @param defaultValue is used if no value is found
     */
    DefaultConditions(final String key, final String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    /**
     * Gets key.
     * It is used to get value from map and set parameter to sql query
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets default value.
     * It is used if no value is found.
     *
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }
}
