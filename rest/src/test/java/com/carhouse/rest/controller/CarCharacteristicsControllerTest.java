package com.carhouse.rest.controller;

import com.carhouse.model.dto.CarCharacteristicsDto;
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
    private ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carCharacteristicsController).build();
    }

    @Test
    void getCarCharacteristicsDto() throws Exception {
        CarCharacteristicsDto carCharacteristicsDto = new CarCharacteristicsDto();
        String carCharacteristicsDtoJson = objectMapper.writeValueAsString(carCharacteristicsDto);
        when(carCharacteristicsDtoService.getCarCharacteristics()).thenReturn(carCharacteristicsDtoJson);
        mockMvc.perform(get(CAR_CHARACTERISTICS_GET_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().json(carCharacteristicsDtoJson));
        verify(carCharacteristicsDtoService).getCarCharacteristics();
    }
}
