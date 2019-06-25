package com.carhouse.dao.impl;

import com.carhouse.dao.TransmissionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@SpringTestConfiguration
class TransmissionDaoImplTest {

    TransmissionDao transmissionDao;

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
