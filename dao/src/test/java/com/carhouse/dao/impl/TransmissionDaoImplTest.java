package com.carhouse.dao.impl;

import com.carhouse.dao.TransmissionDao;
import config.TestConfig;
import config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class, TestSpringJDBCConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TransmissionDaoImplTest {

    private TransmissionDao transmissionDao;

    @Autowired
    TransmissionDaoImplTest(TransmissionDao transmissionDao) {
        this.transmissionDao = transmissionDao;
    }

    @Test
    void getTransmissions() {
        assertEquals(2, transmissionDao.getTransmissions().size());
    }

    @Test
    void getTransmission() {
        Assertions.assertEquals("Automatic", transmissionDao.getTransmission(1).getTransmission());
        Assertions.assertEquals("Manual", transmissionDao.getTransmission(2).getTransmission());
    }

    @Test
    void getNonExistentTransmission() {
        assertThrows(EmptyResultDataAccessException.class, () -> transmissionDao.getTransmission(5));
    }
}
