package com.carhouse.service.impl;

import com.carhouse.dao.impl.FuelTypeDaoImpl;
import com.carhouse.model.FuelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class FuelTypeServiceImplTest {

    @Mock
    private FuelTypeDaoImpl fuelTypeDao;

    @InjectMocks
    private FuelTypeServiceImpl fuelTypeService;

    private List<FuelType> listFuelTypes;
    @BeforeEach
    void addFuelTypesToService() {
        listFuelTypes = new ArrayList<>(){{
           add(new FuelType(1, "Bensin"));
           add(new FuelType(2, "Diesel"));
           add(new FuelType(3, "Electric"));
        }};
    }

    @Test
    void getFuelTypes() {
        when(fuelTypeDao.getFuelTypes()).thenReturn(listFuelTypes);
        fuelTypeService.getFuelTypes();
        verify(fuelTypeDao, times(1)).getFuelTypes();
    }

    @Test
    void getFuelType() {
        when(fuelTypeDao.getFuelType(1)).thenReturn(listFuelTypes.get(1));
        fuelTypeService.getFuelType(1);
        verify(fuelTypeDao, times(1)).getFuelType(1);
    }

    @Test
    void addFuelType() {
        when(fuelTypeDao.addFuelType(anyString())).thenReturn(4);
        assertEquals(4, fuelTypeService.addFuelType("wather"));
        verify(fuelTypeDao, times(1)).addFuelType(anyString());
    }

    @Test
    void updateFuelType() {
        String fuelType = "wood";
        fuelTypeService.updateFuelType(2, fuelType);
        verify(fuelTypeDao, times(1)).updateFuelType(2, fuelType);
    }

    @Test
    void deleteFuelType() {
        fuelTypeService.deleteFuelType(2);
        verify(fuelTypeDao, times(1)).deleteFuelType(2);
    }
}