package com.carhouse.service.impl;

import com.carhouse.dao.CarCharacteristicsDtoDao;
import com.carhouse.model.dto.CarCharacteristicsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarCharacteristicsDtoServiceImplTest {

    @Mock
    private CarCharacteristicsDtoDao carCharacteristicsDtoDao;

    @InjectMocks
    private CarCharacteristicsDtoServiceImpl carCharacteristicsDtoService;

    @Test
    void getCarCharacteristics() throws JsonProcessingException {
        CarCharacteristicsDto carCharacteristicsDto = new CarCharacteristicsDto();
        String carCharacteristicsDtoJson = new ObjectMapper().writeValueAsString(carCharacteristicsDto);
        when(carCharacteristicsDtoDao.getCarCharacteristics()).thenReturn(carCharacteristicsDtoJson);
        assertEquals(carCharacteristicsDtoJson, carCharacteristicsDtoService.getCarCharacteristics());
    }
}