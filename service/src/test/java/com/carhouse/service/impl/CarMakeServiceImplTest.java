package com.carhouse.service.impl;

import com.carhouse.dao.CarMakeDao;
import com.carhouse.model.CarMake;
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
class CarMakeServiceImplTest {

    @Mock
    private CarMakeDao carMakeDao;

    @InjectMocks
    private CarMakeServiceImpl carMakeService;

    private static List<CarMake> listCarMake;

    @BeforeAll
    static void addCarMakes() {
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
    void getCarMake() throws NotFoundException {
        int carMakeId = 2;
        when(carMakeDao.getCarMake(carMakeId)).thenReturn(listCarMake.get(carMakeId));
        assertEquals(listCarMake.get(carMakeId).getCarMake(), carMakeService.getCarMake(carMakeId).getCarMake());
        verify(carMakeDao, times(1)).getCarMake(carMakeId);
    }

    @Test
    void getNonExistentCarMake() {
        int carMakeId = 10;
        when(carMakeDao.getCarMake(carMakeId)).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carMakeService.getCarMake(carMakeId));
        assertTrue(thrown.getMessage().contains("there is not car make with id = " + carMakeId));
    }
}