package com.carhouse.service.impl;

import com.carhouse.dao.CarMakeDao;
import com.carhouse.model.CarMake;
import org.junit.jupiter.api.BeforeEach;
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
class CarMakeServiceImplTest {

    @Mock
    private CarMakeDao carMakeDao;

    @InjectMocks
    private CarMakeServiceImpl carMakeService;

    private List<CarMake> listCarMake;

    @BeforeEach
    void addCarMakes() {
        listCarMake = new ArrayList<>() {{
            add(new CarMake(1, "Mercedes"));
            add(new CarMake(2, "BMW"));
            add(new CarMake(3, "Audi"));
        }};
    }

    @Test
    void getCarMakes() {
        when(carMakeDao.getCarMakes()).thenReturn(listCarMake);
        assertEquals(listCarMake.size(), carMakeService.getCarMakes().size());
        verify(carMakeDao, times(1)).getCarMakes();
    }

    @Test
    void getCarMake() {
        int carMakeId = 2;
        when(carMakeDao.getCarMake(carMakeId)).thenReturn(listCarMake.get(carMakeId));
        assertEquals(listCarMake.get(carMakeId).getCarMake(), carMakeService.getCarMake(carMakeId).getCarMake());
        verify(carMakeDao, times(1)).getCarMake(carMakeId);
    }

    @Test
    void gerNonExistentCarMake() {
        int carMakeId = 10;
        when(carMakeDao.getCarMake(carMakeId)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> carMakeService.getCarMake(carMakeId));
    }
}