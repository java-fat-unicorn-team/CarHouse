package com.spring.rest.runner;

import com.spring.rest.config.AppConfig;
import com.spring.rest.config.SpringJDBCConfig;
import com.spring.rest.dao.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
final class App {
    /**
     * private constructor.
     */
    private App() {
    }

    /**
     * start point of program.
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class,
                        SpringJDBCConfig.class);
        CommentDao comment = context.getBean(CommentDao.class);
        CarDao car = context.getBean(CarDao.class);
        CarSaleDao carSale = context.getBean(CarSaleDao.class);
        FuelTypeDao fuelType = context.getBean(FuelTypeDao.class);
        TransmissionDao transmission = context.getBean(TransmissionDao.class);
        transmission.getAllTransmission().forEach(System.out::println);
        System.out.println();
        car.getAllCars().forEach(System.out::println);
        System.out.println();
        fuelType.getAllFuelTypes().forEach(System.out::println);
        System.out.println();
        carSale.getAllCarSales().forEach(System.out::println);
        System.out.println();
        comment.getAllCommentsOfCarSale(7).forEach(System.out::println);
    }
}
