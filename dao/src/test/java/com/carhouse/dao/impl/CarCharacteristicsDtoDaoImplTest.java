package com.carhouse.dao.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import com.carhouse.model.dto.CarCharacteristicsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles(profiles = "mysql-database")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
class CarCharacteristicsDtoDaoImplTest {

    @Autowired
    CarCharacteristicsDtoDao carCharacteristicsDtoDao;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getCarCharacteristics() throws JsonProcessingException {
        String carCharacteristicsDtoJSON = carCharacteristicsDtoDao.getCarCharacteristics();
        CarCharacteristicsDto carCharacteristicsDto = objectMapper.readValue(carCharacteristicsDtoJSON,
                CarCharacteristicsDto.class);
        assertEquals(2, carCharacteristicsDto.getCarMakeList().size());
        assertEquals(2, carCharacteristicsDto.getTransmissionList().size());
        assertEquals("Diesel", carCharacteristicsDto.getFuelTypeList().get(1).getFuelType());
    }
}