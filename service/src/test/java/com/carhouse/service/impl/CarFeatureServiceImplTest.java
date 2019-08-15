package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarFeatureDao;
import com.carhouse.model.CarFeature;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarFeatureServiceImplTest {

    @Mock
    private CarFeatureDao carFeatureDao;

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarFeatureServiceImpl carFeatureService;

    private static List<CarFeature> listCarFeature;

    @BeforeAll
    static void addCarFeature() {
        listCarFeature = new ArrayList<>() {{
            add(new CarFeature(1, "Winter tires"));
            add(new CarFeature(2, "Air conditioning"));
            add(new CarFeature(3, "Leather interior"));
        }};
    }

    @Test
    void getCarFeatures() throws NotFoundException {
        int carId = 2;
        when(carFeatureDao.getCarFeatures(carId)).thenReturn(listCarFeature);
        assertEquals(listCarFeature.size(), carFeatureService.getCarFeatures(carId).size());
        verify(carFeatureDao, times(1)).getCarFeatures(carId);
    }

    @Test
    void getCarFeaturesOfNotExistCar() {
        int carId = 12;
        when(carDao.getCar(carId)).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carFeatureService.getCarFeatures(carId));
        assertEquals("there is not car with id = " + carId, thrown.getMessage());
    }

    @Test
    void getAllFeatures() {
        when(carFeatureDao.getAllFeatures()).thenReturn(listCarFeature);
        assertEquals(listCarFeature.size(), carFeatureService.getAllFeatures().size());
        verify(carFeatureDao, times(1)).getAllFeatures();
    }
}