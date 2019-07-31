package com.carhouse.rest.config;

import com.carhouse.rest.controller.CarController;
import database.test.config.TestSpringJDBCConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, TestSpringJDBCConfig.class})
public class SpringConfigTest {

    @Autowired
    private CarController carController;

    @Test
    public void getCars() {
        carController.getCars();
    }
}
