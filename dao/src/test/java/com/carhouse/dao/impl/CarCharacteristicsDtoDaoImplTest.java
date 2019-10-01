package com.carhouse.dao.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import com.carhouse.model.dto.CarCharacteristicsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarCharacteristicsDtoDaoImplTest {

    @Autowired
    CarCharacteristicsDtoDao carCharacteristicsDtoDao;

    @Test
    void getCarCharacteristics() {
        CarCharacteristicsDto carCharacteristicsDto = carCharacteristicsDtoDao.getCarCharacteristics();
        assertEquals(2, carCharacteristicsDto.getCarMakeList().size());
        assertEquals(2, carCharacteristicsDto.getTransmissionList().size());
        assertEquals("Diesel", carCharacteristicsDto.getFuelTypeList().get(1).getFuelType());
    }
}