package com.carhouse.rest.controller;

import com.carhouse.model.CarMake;
import com.carhouse.model.CarModel;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.service.CarModelService;
import com.carhouse.rest.testConfig.RestTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = RestTestConfig.class)
class CarModelControllerTest {

    private static final String CAR_MODEL_LIST_GET_URL = "/carSale/car/carModel/list/{carMakeId}";
    private static final String CAR_MODEL_GET_URL = "/carSale/car/carModel/{carModelId}";

    @Mock
    private CarModelService carModelService;

    @InjectMocks
    private CarModelController carModelController;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;
    private List<CarModel> listCarModel;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carModelController)
                .setControllerAdvice(restExceptionHandler)
                .build();
        listCarModel = new ArrayList<>() {{
            add(new CarModel(1, new CarMake(1, "Mercedes")));
            add(new CarModel(2, new CarMake(2, "BMW")));
        }};
    }

    @Test
    void getCarModels() throws Exception {
        Integer carMakeId = 2;
        when(carModelService.getCarModels(carMakeId)).thenReturn(listCarModel);
        mockMvc.perform(get(CAR_MODEL_LIST_GET_URL, carMakeId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(carModelService, times(1)).getCarModels(carMakeId);
    }

    @Test
    void getCarModelsOfNotExistCarMake() throws Exception {
        Integer carMakeId = 112;
        when(carModelService.getCarModels(carMakeId)).thenThrow(NotFoundException.class);
        mockMvc.perform(get(CAR_MODEL_LIST_GET_URL, carMakeId))
                .andExpect(status().isNotFound());
        verify(carModelService).getCarModels(carMakeId);
    }

    @Test
    void getCarModel() throws Exception {
        Integer carModelId = 1;
        when(carModelService.getCarModel(carModelId)).thenReturn(listCarModel.get(carModelId));
        mockMvc.perform(get(CAR_MODEL_GET_URL, carModelId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listCarModel.get(carModelId))));
        verify(carModelService).getCarModel(carModelId);
    }

    @Test
    void getNotExistCarModel() throws Exception {
        Integer carModelId = 112;
        when(carModelService.getCarModel(carModelId)).thenThrow(NotFoundException.class);
        mockMvc.perform(get(CAR_MODEL_GET_URL, carModelId))
                .andExpect(status().isNotFound());
        verify(carModelService).getCarModel(carModelId);
    }
}