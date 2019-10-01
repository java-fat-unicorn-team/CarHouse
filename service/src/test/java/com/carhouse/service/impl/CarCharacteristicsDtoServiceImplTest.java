package com.carhouse.service.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import com.carhouse.dao.exception.IncorrectJsonException;
import com.carhouse.model.dto.CarCharacteristicsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarCharacteristicsDtoServiceImplTest {

    @Mock
    private CarCharacteristicsDtoDao carCharacteristicsDtoDao;

    @InjectMocks
    private CarCharacteristicsDtoServiceImpl carCharacteristicsDtoService;

    @Test
    void getCarCharacteristics() {
        CarCharacteristicsDto carCharacteristicsDto = new CarCharacteristicsDto();
        when(carCharacteristicsDtoDao.getCarCharacteristics()).thenReturn(carCharacteristicsDto);
        assertEquals(carCharacteristicsDto, carCharacteristicsDtoService.getCarCharacteristics());
    }

    @Test
    void getCarCharacteristicsError() {
        String errorMessage = "Sorry, Incorrect JSON obtained from the database, we are working on it";
        IncorrectJsonException exception = new IncorrectJsonException(errorMessage);
        when(carCharacteristicsDtoDao.getCarCharacteristics()).thenThrow(exception);
        IncorrectJsonException thrown = assertThrows(IncorrectJsonException.class,
                () -> carCharacteristicsDtoService.getCarCharacteristics());
        assertEquals(errorMessage, thrown.getMessage());

    }
}