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
        int id = 2;
        when(carMakeDao.getCarMake(id)).thenReturn(listCarMake.get(id));
        assertEquals(listCarMake.get(id).getCarMake(), carMakeService.getCarMake(id).getCarMake());
        verify(carMakeDao, times(1)).getCarMake(id);
    }

    @Test
    void gerNonExistentCarMake() {
        when(carMakeDao.getCarMake(10)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> carMakeService.getCarMake(10));
    }
}