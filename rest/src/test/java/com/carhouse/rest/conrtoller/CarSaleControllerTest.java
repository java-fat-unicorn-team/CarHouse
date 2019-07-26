package com.carhouse.rest.conrtoller;

import com.carhouse.model.Car;
import com.carhouse.model.CarSale;
import com.carhouse.model.User;
import com.carhouse.rest.JsonConverter;
import com.carhouse.service.CarSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarSaleControllerTest {

    @Mock
    private CarSaleService carSaleService;

    @InjectMocks
    private CarSaleController carSaleController;

    private JsonConverter jsonConverter = new JsonConverter();
    private List<CarSale> listCarSale;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carSaleController).build();
        listCarSale = new ArrayList<>() {{
            add(new CarSale(1, new BigDecimal(23200), new Date(), new User(1), new Car(3)));
            add(new CarSale(2, new BigDecimal(50250), new Date(), new User(1), new Car(3)));
            add(new CarSale(3, new BigDecimal(47320), new Date(), new User(1), new Car(3)));
        }};
    }

    @Test
    void getCarSales() throws Exception {
        when(carSaleController.getCarSales()).thenReturn(listCarSale);
        mockMvc.perform(get("/carSale"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonConverter.asJsonString(listCarSale)));
        verify(carSaleService, times(1)).getCarSales();
    }

    @Test
    void getCarSale() throws Exception {
        int carSaleId = 1;
        when(carSaleService.getCarSale(carSaleId)).thenReturn(listCarSale.get(carSaleId));
        mockMvc.perform(get("/carSale/{carSaleId}", carSaleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonConverter.asJsonString(listCarSale.get(carSaleId))));
        verify(carSaleService, times(1)).getCarSale(carSaleId);
    }

    @Test
    void addCarSale() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        when(carSaleService.addCarSale(any(CarSale.class))).thenReturn(carSaleId);
        mockMvc.perform(post("/carSale")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonConverter.asJsonString(carSale)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonConverter.asJsonString(carSaleId)));
        verify(carSaleService, times(1)).addCarSale(any(CarSale.class));
    }

    @Test
    void updateCarSale() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        mockMvc.perform(put("/carSale")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonConverter.asJsonString(carSale)))
                .andExpect(status().isOk());
        verify(carSaleService, times(1)).updateCarSale(any(CarSale.class));
    }

    @Test
    void deleteCarSale() throws Exception {
        int carSaleId = 1;
        mockMvc.perform(delete("/carSale/{id}", carSaleId))
                .andExpect(status().isOk());
        verify(carSaleService, times(1)).deleteCarSale(carSaleId);
    }
}
