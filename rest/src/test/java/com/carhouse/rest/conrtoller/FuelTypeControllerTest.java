package com.carhouse.rest.conrtoller;

import com.carhouse.model.FuelType;
import com.carhouse.service.FuelTypeService;
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
class FuelTypeControllerTest {

    @Mock
    private FuelTypeService fuelTypeService;

    @InjectMocks
    private FuelTypeController fuelTypeController;

    private List<FuelType> fuelTypeList;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(fuelTypeController).build();
        fuelTypeList = new ArrayList<>() {{
            add(new FuelType(1, "Bensin"));
            add(new FuelType(2, "Diesel"));
            add(new FuelType(3, "Electric"));
        }};
    }

    @Test
    void getFuelTypes() throws Exception {
        when(fuelTypeService.getFuelTypes()).thenReturn(fuelTypeList);
        mockMvc.perform(get("/carSale/car/fuelType"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(fuelTypeService, times(1)).getFuelTypes();
    }
}