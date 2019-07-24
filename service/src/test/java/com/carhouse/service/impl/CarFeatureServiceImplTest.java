package com.carhouse.service.impl;

import com.carhouse.dao.CarFeatureDao;
import com.carhouse.model.CarFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarFeatureServiceImplTest {

    @Mock
    private CarFeatureDao carFeatureDao;

    @InjectMocks
    private CarFeatureServiceImpl carFeatureService;

    private List<CarFeature> listCarFeature;

    @BeforeEach
    void addCarFeature() {
        listCarFeature = new ArrayList<>() {{
            add(new CarFeature(1, "Winter tires"));
            add(new CarFeature(2, "Air conditioning"));
            add(new CarFeature(3, "Leather interior"));
        }};
    }

    @Test
    void getCarFeatures() {
        when(carFeatureDao.getCarFeatures(2)).thenReturn(listCarFeature);
        assertEquals(listCarFeature.size(), carFeatureService.getCarFeatures(2).size());
        verify(carFeatureDao, times(1)).getCarFeatures(2);
    }
}