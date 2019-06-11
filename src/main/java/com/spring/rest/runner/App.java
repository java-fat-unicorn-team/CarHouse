package com.spring.rest.runner;

import com.spring.rest.config.AppConfig;
import com.spring.rest.config.SpringJDBCConfig;
import com.spring.rest.dao.CarCharacteristicsDao;
import com.spring.rest.dao.CarSaleDao;
import com.spring.rest.dao.FuelTypeDao;
import com.spring.rest.dao.TransmissionDao;
import com.spring.rest.dao.CommentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * The type App.
 */
final class App {

    /**
     * The constant SEVEN.
     */
    public static final int SEVEN = 7;
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
        CarCharacteristicsDao car = context.getBean(
                CarCharacteristicsDao.class);
        CarSaleDao carSale = context.getBean(CarSaleDao.class);
        FuelTypeDao fuelType = context.getBean(FuelTypeDao.class);
        TransmissionDao transmission = context.getBean(TransmissionDao.class);
        transmission.getTransmissions().forEach(System.out::println);
        System.out.println();
        car.getCarsCharacteristics().forEach(System.out::println);
        System.out.println();
        fuelType.getFuelTypes().forEach(System.out::println);
        System.out.println();
        carSale.getCarSales().forEach(System.out::println);
        System.out.println();
        CommentDao comment = context.getBean(CommentDao.class);
        comment.getCarSaleComments(SEVEN).forEach(System.out::println);
    }
}
