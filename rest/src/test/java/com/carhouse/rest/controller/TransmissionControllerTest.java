package com.carhouse.rest.controller;

import com.carhouse.model.Transmission;
import com.carhouse.service.TransmissionService;
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
class TransmissionControllerTest {

    private static final String TRANSMISSION_LIST_GET_URL = "/carSale/car/transmission";
    @Mock
    private TransmissionService transmissionService;

    @InjectMocks
    private TransmissionController transmissionController;

    private List<Transmission> listTransmission;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(transmissionController).build();
        this.listTransmission = new ArrayList<>() {{
            add(new Transmission(1, "Manual"));
            add(new Transmission(2, "Stick"));
        }};
    }

    @Test
    void getTransmissions() throws Exception {
        when(transmissionService.getTransmissions()).thenReturn(listTransmission);
        mockMvc.perform(get(TRANSMISSION_LIST_GET_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(transmissionService, times(1)).getTransmissions();
    }
}