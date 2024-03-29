package com.carhouse.service.impl;

import com.carhouse.dao.FuelTypeDao;
import com.carhouse.model.FuelType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class FuelTypeServiceImplTest {

    @Mock
    private FuelTypeDao fuelTypeDao;

    @InjectMocks
    private FuelTypeServiceImpl fuelTypeService;

    private static List<FuelType> listFuelTypes;

    @BeforeAll
    static void addFuelTypesToService() {
        listFuelTypes = new ArrayList<>() {{
            add(new FuelType(1, "Bensin"));
            add(new FuelType(2, "Diesel"));
            add(new FuelType(3, "Electric"));
        }};
    }

    @Test
    void getFuelTypes() {
        when(fuelTypeDao.getFuelTypes()).thenReturn(listFuelTypes);
        List<FuelType> obtainedFuelTypeList = fuelTypeService.getFuelTypes();
        assertEquals(listFuelTypes.size(), obtainedFuelTypeList.size());
        verify(fuelTypeDao, times(1)).getFuelTypes();
    }
}