package com.carhouse.rest.conrtoller;

import com.carhouse.model.CarFeature;
import com.carhouse.rest.JsonConverter;
import com.carhouse.service.CarFeatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarFeatureControllerTest {

    @Mock
    private CarFeatureService carFeatureService;

    @InjectMocks
    private CarFeatureController carFeatureController;

    private JsonConverter jsonConverter = new JsonConverter();
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
        mockMvc.perform(get("/carSale/car/{carId}/carFeature", carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonConverter.asJsonString(listCarFeature)));
        verify(carFeatureService, times(1)).getCarFeatures(carId);
    }

    @Test
    void getAllFeatures() throws Exception {
        when(carFeatureService.getAllFeatures()).thenReturn(listCarFeature);
        mockMvc.perform(get("/carSale/car/carFeature"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonConverter.asJsonString(listCarFeature)));
        verify(carFeatureService, times(1)).getAllFeatures();
    }
}
