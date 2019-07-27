package controller;

import com.carhouse.model.CarMake;
import config.RestTestConfig;
import com.carhouse.rest.controller.CarMakeController;
import com.carhouse.service.CarMakeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RestTestConfig.class)
class CarMakeControllerTest {

    @Autowired
    private CarMakeService carMakeService;

    @Autowired
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