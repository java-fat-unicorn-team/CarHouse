package com.carhouse.dao.builders;

import com.carhouse.dao.builders.models.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class is used to build sql query with conditions to get list of car sales.
 */
@Component
public class CarSaleSqlBuilder {

    @Value("${car.sales.list.get}")
    private String GET_LIST_CAR_SALES_SQL;
    @Value("${car.make.id.property}")
    private String CAR_MAKE_ID_PROPERTY;
    @Value("${car.make.id.column}")
    private String CAR_MAKE_ID_COLUMN;
    @Value("${car.model.id.property}")
    private String CAR_MODEL_ID_PROPERTY;
    @Value("${car.model.id.column}")
    private String CAR_MODEL_ID_COLUMN;
    @Value("${car.manufacture.year.from.property}")
    private String CAR_MANUFACTURE_YEAR_FROM_PROPERTY;
    @Value("${car.manufacture.year.to.property}")
    private String CAR_MANUFACTURE_YEAR_TO_PROPERTY;
    @Value("${car.manufacture.year.column}")
    private String CAR_MANUFACTURE_YEAR_COLUMN;
    @Value("${car.price.from.property}")
    private String CAR_PRICE_FROM_PROPERTY;
    @Value("${car.price.to.property}")
    private String CAR_PRICE_TO_PROPERTY;
    @Value("${car.price.column}")
    private String CAR_PRICE_COLUMN;

    private static final Logger LOGGER = LogManager.getLogger(CarSaleSqlBuilder.class);

    /**
     * Build sql query string.
     *
     * @param conditionList the condition list
     * @return the string
     */
    public String buildSqlQuery(final List<Condition> conditionList) {
        LOGGER.debug("method buildSqlQuery");
        String predicate = " WHERE ";
        StringBuilder query = new StringBuilder(GET_LIST_CAR_SALES_SQL);
        for (Condition condition : conditionList) {
            query.append(predicate);
            query.append(condition.getColumnName());
            query.append(condition.getCondition());
            query.append("'");
            query.append(condition.getValue());
            query.append("'");
            predicate = " AND ";
        }
        return query.toString();
    }

    /**
     * Build conditions list.
     *
     * @param requestParams the request params
     * @return the conditions list
     */
    public List<Condition> buildConditionList(final Map<String, String> requestParams) {
        LOGGER.debug("method buildConditionList with parameter: {}", requestParams);
        List<Condition> conditionMap = new ArrayList<>();
        if (requestParams.get(CAR_MAKE_ID_PROPERTY) != null) {
            conditionMap.add(buildCondition(CAR_MAKE_ID_COLUMN,
                    requestParams.get(CAR_MAKE_ID_PROPERTY), " = "));
            if (requestParams.get(CAR_MODEL_ID_PROPERTY) != null) {
                conditionMap.add(buildCondition(CAR_MODEL_ID_COLUMN,
                        requestParams.get(CAR_MODEL_ID_PROPERTY), " = "));
            }
        }
        if (requestParams.get(CAR_MANUFACTURE_YEAR_FROM_PROPERTY) != null) {
            conditionMap.add(buildCondition(CAR_MANUFACTURE_YEAR_COLUMN,
                    requestParams.get(CAR_MANUFACTURE_YEAR_FROM_PROPERTY), " >= "));
        }
        if (requestParams.get(CAR_MANUFACTURE_YEAR_TO_PROPERTY) != null) {
            conditionMap.add(buildCondition(CAR_MANUFACTURE_YEAR_COLUMN,
                    requestParams.get(CAR_MANUFACTURE_YEAR_TO_PROPERTY), " <= "));
        }
        if (requestParams.get(CAR_PRICE_FROM_PROPERTY) != null) {
            conditionMap.add(buildCondition(CAR_PRICE_COLUMN,
                    requestParams.get(CAR_PRICE_FROM_PROPERTY), " >= "));
        }
        if (requestParams.get(CAR_PRICE_TO_PROPERTY) != null) {
            conditionMap.add(buildCondition(CAR_PRICE_COLUMN,
                    requestParams.get(CAR_PRICE_TO_PROPERTY), " <= "));
        }
        return conditionMap;
    }

    /**
     * Build condition condition.
     *
     * @param columnName the column name
     * @param value      the value
     * @param condition  the condition
     * @return the condition
     */
    private Condition buildCondition(final String columnName, final String value, final String condition) {
        return new Condition()
                .setColumnName(columnName)
                .setValue(value)
                .setCondition(condition);
    }
}

