package com.carhouse.rest.config;

import com.carhouse.rest.controller.CarController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("integrationTest")
@PropertySource("classpath:test-jdbc-connection.properties")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, SpringJDBCConfig.class})
public class SpringConfigTest {

    @Autowired
    private CarController carController;

    @Test
    public void getCars() {
        assertEquals(5, carController.getCars().size());
    }
}
