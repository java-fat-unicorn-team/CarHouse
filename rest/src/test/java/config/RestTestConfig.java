package config;

import com.carhouse.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan({"com.carhouse.rest.controller", "com.carhouse.rest.handler", "com.carhouse.rest.response"})
public class RestTestConfig {

    @Bean
    public CarFeatureService getCarFeatureService() {
        return mock(CarFeatureService.class);
    }

    @Bean
    public CarMakeService getCarMakeService() {
        return mock(CarMakeService.class);
    }

    @Bean
    public CarModelService getCarModelService() {
        return mock(CarModelService.class);
    }

    @Bean
    public CarSaleService getCarSaleService() {
        return mock(CarSaleService.class);
    }

    @Bean
    public CarService getCarService() {
        return mock(CarService.class);
    }

    @Bean
    public CommentService getCommentService() {
        return mock(CommentService.class);
    }

    @Bean
    public FuelTypeService getFuelTypeService() {
        return mock(FuelTypeService.class);
    }

    @Bean
    public TransmissionService getTransmissionService() {
        return mock(TransmissionService.class);
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
