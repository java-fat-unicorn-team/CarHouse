package com.carhouse.dao.builders.models;

/**
 * The condition class is used to create conditions in sql query.
 */
public class Condition {
    private String columnName;
    private String value;
    private String condition;

    /**
     * Gets column name.
     *
     * @return the param
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets column name.
     *
     * @param columnName the param
     * @return the param
     */
    public Condition setColumnName(final String columnName) {
        this.columnName = columnName;
        return this;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     * @return the value
     */
    public Condition setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * Gets condition.
     *
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets condition.
     *
     * @param condition the condition
     * @return the condition
     */
    public Condition setCondition(final String condition) {
        this.condition = condition;
        return this;
    }
}
