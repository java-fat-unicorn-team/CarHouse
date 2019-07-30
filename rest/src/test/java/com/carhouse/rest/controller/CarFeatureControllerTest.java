package com.carhouse.rest.controller;

import com.carhouse.model.CarFeature;
import com.carhouse.service.CarFeatureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.carhouse.rest.testConfig.RestTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
class CarFeatureControllerTest {

    private static final String CAR_FEATURE_LIST_GET_URL = "/carSale/car/{carId}/carFeature";
    private static final String FEATURE_LIST_GET_URL = "/carSale/car/carFeature";

    @Mock
    private CarFeatureService carFeatureService;

    @InjectMocks
    private CarFeatureController carFeatureController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<CarFeature> listCarFeature;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carFeatureController).build();
        listCarFeature = new ArrayList<>() {{
            add(new CarFeature(1, "Winter tires"));
            add(new CarFeature(2, "Air conditioning"));
            add(new CarFeature(3, "Leather interior"));
        }};
    }

    @Test
    void getCarFeatures() throws Exception {
        int carId = 2;
        when(carFeatureService.getCarFeatures(carId)).thenReturn(listCarFeature);
        mockMvc.perform(get(CAR_FEATURE_LIST_GET_URL, carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(listCarFeature)));
        verify(carFeatureService, times(1)).getCarFeatures(carId);
    }

    @Test
    void getAllFeatures() throws Exception {
        when(carFeatureService.getAllFeatures()).thenReturn(listCarFeature);
        mockMvc.perform(get(FEATURE_LIST_GET_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(listCarFeature)));
        verify(carFeatureService, times(1)).getAllFeatures();
    }
}
