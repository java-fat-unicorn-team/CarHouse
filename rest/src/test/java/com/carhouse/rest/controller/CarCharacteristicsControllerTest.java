package com.carhouse.rest.controller;

import com.carhouse.dao.exception.IncorrectJsonException;
import com.carhouse.model.dto.CarCharacteristicsDto;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.rest.testConfig.RestTestConfig;
import com.carhouse.service.CarCharacteristicsDtoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = RestTestConfig.class)
class CarCharacteristicsControllerTest {

    private static final String CAR_CHARACTERISTICS_GET_URL = "/carCharacteristics";

    @Mock
    private CarCharacteristicsDtoService carCharacteristicsDtoService;
    @InjectMocks
    private CarCharacteristicsController carCharacteristicsController;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carCharacteristicsController)
                .setControllerAdvice(restExceptionHandler)
                .build();
    }

    @Test
    void getCarCharacteristicsDto() throws Exception {
        CarCharacteristicsDto carCharacteristicsDto = new CarCharacteristicsDto();
        when(carCharacteristicsDtoService.getCarCharacteristics()).thenReturn(carCharacteristicsDto);
        mockMvc.perform(get(CAR_CHARACTERISTICS_GET_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(carCharacteristicsDto)));
        verify(carCharacteristicsDtoService).getCarCharacteristics();
    }

    @Test
    void getCarCharacteristicsDtoError() throws Exception {
        when(carCharacteristicsDtoService.getCarCharacteristics()).thenThrow(IncorrectJsonException.class);
        mockMvc.perform(get(CAR_CHARACTERISTICS_GET_URL))
                .andExpect(status().isInternalServerError());
        verify(carCharacteristicsDtoService).getCarCharacteristics();
    }
}
