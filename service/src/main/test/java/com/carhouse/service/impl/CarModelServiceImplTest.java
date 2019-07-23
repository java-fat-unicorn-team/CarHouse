package com.carhouse.service.impl;

import com.carhouse.dao.CarModelDao;
import com.carhouse.model.CarMake;
import com.carhouse.model.CarModel;
import org.junit.jupiter.api.BeforeEach;
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
class CarModelServiceImplTest {

    @Mock
    private CarModelDao carModelDao;

    @InjectMocks
    private CarModelServiceImpl carModelService;

    private List<CarModel> listCarModel;

    @BeforeEach
    void addCarModels() {
        listCarModel = new ArrayList<>() {{
            add(new CarModel(1, new CarMake(1,"Mercedes")));
            add(new CarModel(2, new CarMake(2,"BMW")));
        }};
    }

    @Test
    void getCarModels() {
        when(carModelDao.getCarModels()).thenReturn(listCarModel);
        assertEquals(listCarModel.size(), carModelService.getCarModels().size());
        verify(carModelDao, times(1)).getCarModels();
    }

    @Test
    void getCarModel() {
        int id = 1;
        when(carModelDao.getCarModel(id)).thenReturn(listCarModel.get(id));
        assertEquals(listCarModel.get(id).getCarModelId(), carModelService.getCarModel(id).getCarModelId());
        verify(carModelDao, times(1)).getCarModel(id);
    }

    @Test
    void gerNonExistentCatModel() {
        when(carModelDao.getCarModel(10)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> carModelService.getCarModel(10));
    }
}