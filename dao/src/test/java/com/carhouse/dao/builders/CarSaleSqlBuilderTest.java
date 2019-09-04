package com.carhouse.dao.builders;

import com.carhouse.dao.builders.models.Condition;
import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
class CarSaleSqlBuilderTest {

    @Value("${car.sales.list.get}")
    private String GET_LIST_CAR_SALES_SQL;
    @Value("${car.make.id.column}")
    private String CAR_MAKE_ID_COLUMN;
    @Value("${car.model.id.column}")
    private String CAR_MODEL_ID_COLUMN;

    @Autowired
    private CarSaleSqlBuilder builder;

    @Test
    void buildSqlQuery() {
        String query = GET_LIST_CAR_SALES_SQL + " WHERE m.car_make_id = '2' AND cm.car_model_id = '1'";
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition()
                .setColumnName(CAR_MAKE_ID_COLUMN)
                .setValue("2")
                .setCondition(" = "));
        conditionList.add(new Condition()
                .setColumnName(CAR_MODEL_ID_COLUMN)
                .setValue("1")
                .setCondition(" = "));
        String resultQuery = builder.buildSqlQuery(conditionList);
        assertEquals(query, resultQuery);
    }

    @Test
    void buildConditionList() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("carMakeId", "1");
        requestParams.put("carModelId", "1");
        List<Condition> conditionList = builder.buildConditionList(requestParams);
        assertEquals(CAR_MAKE_ID_COLUMN, conditionList.get(0).getColumnName());
        assertEquals("1", conditionList.get(0).getValue());
        assertEquals(CAR_MODEL_ID_COLUMN, conditionList.get(1).getColumnName());
        assertEquals("1", conditionList.get(1).getValue());
        assertEquals(2, conditionList.size());
    }

    @Test
    void buildConditionListWithoutParameters() {
        Map<String, String> requestParams = new HashMap<>();
        List<Condition> conditionList = builder.buildConditionList(requestParams);
        assertTrue(conditionList.isEmpty());
    }
}