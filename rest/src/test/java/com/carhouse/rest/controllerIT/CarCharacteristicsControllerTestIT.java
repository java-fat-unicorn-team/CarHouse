package com.carhouse.rest.controllerIT;

import com.carhouse.model.dto.CarCharacteristicsDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarCharacteristicsControllerTestIT {

    private static final String HOST = "http://localhost:8086";
    private static final String CAR_CHARACTERISTICS_GET_URL = "/carCharacteristics";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void getCarCharacteristicsDto() {
        ResponseEntity<CarCharacteristicsDto> response =
                restTemplate.getForEntity(HOST + CAR_CHARACTERISTICS_GET_URL, CarCharacteristicsDto.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
