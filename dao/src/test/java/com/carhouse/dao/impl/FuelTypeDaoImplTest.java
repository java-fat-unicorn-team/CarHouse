package com.carhouse.dao.impl;

import com.carhouse.dao.FuelTypeDao;
import com.carhouse.dao.config.TestConfiguration;
import com.carhouse.dao.config.TestSpringJDBCConfig;
import com.carhouse.model.FuelType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles ="h2-database")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FuelTypeDaoImplTest {

    private FuelTypeDao fuelTypeDao;

    @Autowired
    FuelTypeDaoImplTest(FuelTypeDao fuelTypeDao) {
        this.fuelTypeDao = fuelTypeDao;
    }

    @Test
    void getFuelTypes() {
        List<FuelType> fuelTypeList = fuelTypeDao.getFuelTypes();
        assertEquals(4, fuelTypeList.size());
        assertEquals("Bensin", fuelTypeList.get(0).getFuelType());
    }
}
