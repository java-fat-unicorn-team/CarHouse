package com.carhouse.service.impl;

import com.carhouse.dao.CarMakeDao;
import com.carhouse.dao.CarModelDao;
import com.carhouse.model.CarMake;
import com.carhouse.model.CarModel;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarModelServiceImplTest {

    @Mock
    private CarModelDao carModelDao;
    @Mock
    private CarMakeDao carMakeDao;

    @InjectMocks
    private CarModelServiceImpl carModelService;

    private static List<CarModel> listCarModel;

    @BeforeAll
    static void addCarModels() {
        listCarModel = new ArrayList<>() {{
            add(new CarModel(1, new CarMake(1, "Mercedes")));
            add(new CarModel(2, new CarMake(2, "BMW")));
        }};
    }

    @Test
    void getCarModels() throws NotFoundException {
        int carMakeId = 2;
        when(carModelDao.getCarModels(carMakeId)).thenReturn(listCarModel);
        assertEquals(listCarModel.size(), carModelService.getCarModels(carMakeId).size());
        verify(carModelDao, times(1)).getCarModels(carMakeId);
    }

    @Test
    void getCarModelsOfNotExistentCarMake() {
        int carMakeId = 22;
        when(carMakeDao.getCarMake(carMakeId)).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> carModelService.getCarModels(carMakeId));
        assertTrue(thrown.getMessage().contains("there is not car make with id = " + carMakeId));
    }

    @Test
    void getCarModel() throws NotFoundException {
        int carModelId = 1;
        when(carModelDao.getCarModel(carModelId)).thenReturn(listCarModel.get(carModelId));
        assertEquals(listCarModel.get(carModelId).getCarModelId(), carModelService.getCarModel(carModelId).getCarModelId());
        verify(carModelDao, times(1)).getCarModel(carModelId);
    }

    @Test
    void gerNonExistentCarModel() {
        int carModelId = 10;
        when(carModelDao.getCarModel(carModelId)).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carModelService.getCarModel(carModelId));
        assertTrue(thrown.getMessage().contains("there is not car model with id = " + carModelId));
    }
}