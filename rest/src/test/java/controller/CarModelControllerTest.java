package controller;

import com.carhouse.model.CarMake;
import com.carhouse.model.CarModel;
import config.RestTestConfig;
import com.carhouse.rest.controller.CarModelController;
import com.carhouse.service.CarModelService;
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
class CarModelControllerTest {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarModelController carModelController;

    private List<CarModel> listCarModel;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carModelController).build();
        listCarModel = new ArrayList<>() {{
            add(new CarModel(1, new CarMake(1, "Mercedes")));
            add(new CarModel(2, new CarMake(2, "BMW")));
        }};
    }

    @Test
    void getCarModels() throws Exception {
        when(carModelService.getCarModels()).thenReturn(listCarModel);
        mockMvc.perform(get("/carSale/car/carModel"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
        verify(carModelService, times(1)).getCarModels();
    }
}