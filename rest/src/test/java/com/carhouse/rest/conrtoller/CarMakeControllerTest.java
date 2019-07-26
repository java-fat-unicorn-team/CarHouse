package com.carhouse.rest.conrtoller;

import com.carhouse.model.CarMake;
import com.carhouse.service.CarMakeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarMakeControllerTest {

    @Mock
    private CarMakeService carMakeService;

    @InjectMocks
    private CarMakeController carMakeController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<CarMake> listCarMake;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carMakeController).build();
        listCarMake = new ArrayList<>() {{
            add(new CarMake(1, "Mercedes"));
            add(new CarMake(2, "BMW"));
            add(new CarMake(3, "Audi"));
        }};
    }

    @Test
    void getCarMakes() throws Exception {
        when(carMakeService.getCarMakes()).thenReturn(listCarMake);
        mockMvc.perform(get("/carSale/car/carModel/carMake"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listCarMake)));
        verify(carMakeService, times(1)).getCarMakes();
    }
}