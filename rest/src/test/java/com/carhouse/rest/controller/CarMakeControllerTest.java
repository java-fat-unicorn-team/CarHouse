package com.carhouse.rest.controller;

import com.carhouse.model.CarMake;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.service.CarMakeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.carhouse.rest.testConfig.RestTestConfig;
import javassist.NotFoundException;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = RestTestConfig.class)
class CarMakeControllerTest {

    private static final String CAR_MAKE_LIST_GET_URL = "/carSale/car/carModel/carMake";
    private static final String CAR_MAKE_GET_URL = "/carSale/car/carModel/carMake/{carMakeId}";

    @Mock
    private CarMakeService carMakeService;

    @InjectMocks
    private CarMakeController carMakeController;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;
    private List<CarMake> listCarMake;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carMakeController)
                .setControllerAdvice(restExceptionHandler)
                .build();
        listCarMake = new ArrayList<>() {{
            add(new CarMake(1, "Mercedes"));
            add(new CarMake(2, "BMW"));
            add(new CarMake(3, "Audi"));
        }};
    }

    @Test
    void getCarMakes() throws Exception {
        when(carMakeService.getCarMakes()).thenReturn(listCarMake);
        mockMvc.perform(get(CAR_MAKE_LIST_GET_URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listCarMake)));
        verify(carMakeService).getCarMakes();
    }

    @Test
    void getCarMake() throws Exception {
        Integer carMakeId = 1;
        when(carMakeService.getCarMake(carMakeId)).thenReturn(listCarMake.get(carMakeId));
        mockMvc.perform(get(CAR_MAKE_GET_URL, carMakeId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listCarMake.get(carMakeId))));
        verify(carMakeService).getCarMake(carMakeId);
    }

    @Test
    void getNotExistCarMake() throws Exception {
        Integer carMakeId = 112;
        when(carMakeService.getCarMake(carMakeId)).thenThrow(NotFoundException.class);
        mockMvc.perform(get(CAR_MAKE_GET_URL, carMakeId))
                .andExpect(status().isNotFound());
        verify(carMakeService).getCarMake(carMakeId);
    }
}