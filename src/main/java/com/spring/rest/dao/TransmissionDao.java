package com.spring.rest.dao;

import com.spring.rest.model.Transmission;

import java.util.List;

public interface TransmissionDao {
    List<Transmission> getAllTransmission();
    Transmission getTransmission(int index);
}
