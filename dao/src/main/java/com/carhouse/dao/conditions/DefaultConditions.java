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
     * Sets key.
     * It is used to get value from map and set parameter to sql query
     *
     * @param key the key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Gets default value.
     *
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets default value.
     *
     * @param defaultValue the default value
     */
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }
}