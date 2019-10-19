package com.carhouse.service.impl;

import com.carhouse.dao.CarDao;
import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.Car;
import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarSaleServiceImplTest {

    @Mock
    private CarDao carDao;

    @Mock
    private CarSaleDao carSaleDao;

    @InjectMocks
    private CarSaleServiceImpl carSaleService;

    private static List<CarSale> listCarSale;
    private static List<CarSaleDto> listCarSaleDto;
    private static MockMultipartFile file;

    @BeforeAll
    static void addCarSales() {
        listCarSale = new ArrayList<>() {{
            add(new CarSale(1));
            add(new CarSale(2));
            add(new CarSale(3));
        }};
        listCarSaleDto = new ArrayList<>() {{
            add(new CarSaleDto().setCarSaleId(0));
            add(new CarSaleDto().setCarSaleId(1));
        }};
        file = new MockMultipartFile("file", "test.txt", "image/*",
                "There should be bytes of image".getBytes());
    }

    @Test
    void getCarSalesDto() {
        Map<String, String> conditionParams = new HashMap<>();
        conditionParams.put("carMakeId", "1");
        conditionParams.put("yearFrom", "2017-01-01");
        conditionParams.put("yearTo", "abcd");
        Map<String, String> validConditionParams = new HashMap<>();
        validConditionParams.put("carMakeId", "1");
        validConditionParams.put("yearFrom", "2017-01-01");
        when(carSaleDao.getListCarSales(validConditionParams)).thenReturn(listCarSaleDto);
        assertEquals(listCarSaleDto.size(), carSaleService.getListCarSales(conditionParams).size());
        verify(carSaleDao, times(1)).getListCarSales(validConditionParams);
    }

    @Test
    void getCarSale() throws NotFoundException {
        int carSaleId = 2;
        when(carSaleDao.getCarSale(carSaleId)).thenReturn(listCarSale.get(carSaleId));
        assertEquals(listCarSale.get(carSaleId).getCarSaleId(), carSaleService.getCarSale(carSaleId).getCarSaleId());
    }

    @Test
    void getNonExistentCarSale() {
        int carSaleId = 10;
        when(carSaleDao.getCarSale(carSaleId)).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carSaleService.getCarSale(carSaleId));
        assertTrue(thrown.getMessage().contains("there is not car sale with id = " + carSaleId));

    }

    @Test
    void addCarSale() throws FileSystemException {
        int newCarId = 3;
        int newCarSaleId = 5;
        Car car = new Car();
        CarSale carSale = new CarSale();
        carSale.setCar(car);
        when(carDao.addCar(car)).thenReturn(newCarId);
        when(carSaleDao.addCarSale(carSale, file)).thenReturn(newCarSaleId);
        assertEquals(newCarSaleId, carSaleService.addCarSale(carSale, file));
        verify(carSaleDao, times(1)).addCarSale(carSale, file);
        verify(carDao, times(1)).addCar(car);
    }

    @Test
    void addCarSaleWithWrongReference() throws FileSystemException {
        CarSale carSale = new CarSale();
        carSale.setCar(new Car());
        when(carDao.addCar(carSale.getCar())).thenReturn(2);
        when(carSaleDao.addCarSale(carSale, file)).thenThrow(DataIntegrityViolationException.class);
        WrongReferenceException thrown = assertThrows(WrongReferenceException.class,
                () -> carSaleService.addCarSale(carSale, file));
        assertTrue(thrown.getMessage().contains("there is wrong references in your car sale"));
    }

    @Test
    void addCarSaleErrorWritingFile() throws FileSystemException {
        String errorMsg = "Something went wrong. Error writing file.";
        CarSale carSale = new CarSale();
        carSale.setCar(new Car());
        when(carDao.addCar(carSale.getCar())).thenReturn(2);
        when(carSaleDao.addCarSale(carSale, file)).thenThrow(new FileSystemException(errorMsg));
        FileSystemException thrown = assertThrows(FileSystemException.class,
                () -> carSaleService.addCarSale(carSale, file));
        assertTrue(thrown.getMessage().contains(errorMsg));
    }

    @Test
    void updateCarSale() throws NotFoundException, FileSystemException {
        CarSale carSale = new CarSale(5);
        carSale.setCar(new Car());
        when(carDao.updateCar(carSale.getCar())).thenReturn(true);
        when(carSaleDao.updateCarSale(carSale, file)).thenReturn(true);
        carSaleService.updateCarSale(carSale, file);
        verify(carSaleDao, times(1)).updateCarSale(carSale, file);
    }

    @Test
    void updateCarSaleWithWrongReference() throws FileSystemException {
        CarSale carSale = new CarSale(4);
        carSale.setCar(new Car());
        when(carDao.updateCar(carSale.getCar())).thenReturn(true);
        doThrow(DataIntegrityViolationException.class).when(carSaleDao).updateCarSale(carSale, file);
        WrongReferenceException thrown = assertThrows(WrongReferenceException.class,
                () -> carSaleService.updateCarSale(carSale, file));
        assertTrue(thrown.getMessage().contains("there is wrong references in your car sale"));
    }

    @Test
    void updateNotExistCarSale() throws FileSystemException {
        CarSale carSale = new CarSale(17);
        carSale.setCar(new Car());
        when(carDao.updateCar(carSale.getCar())).thenReturn(true);
        when(carSaleDao.updateCarSale(carSale, file)).thenReturn(false);
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> carSaleService.updateCarSale(carSale, file));
        assertTrue(thrown.getMessage().contains("there is not car sale with id = " + carSale.getCarSaleId()));
    }

    @Test
    void updateCarSaleWithNotExistCar() {
        CarSale carSale = new CarSale(7);
        carSale.setCar(new Car(15));
        when(carDao.updateCar(carSale.getCar())).thenReturn(false);
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> carSaleService.updateCarSale(carSale, file));
        assertTrue(thrown.getMessage().contains("there is not car with id = " + carSale.getCar().getCarId()));
    }

    @Test
    void deleteCarSale() throws NotFoundException {
        int carSaleId = 3;
        when(carSaleDao.deleteCarSale(carSaleId)).thenReturn(true);
        carSaleService.deleteCarSale(carSaleId);
        verify(carSaleDao, times(1)).deleteCarSale(carSaleId);
    }

    @Test
    void deleteNotExistCarSale() {
        int carSaleId = 12;
        when(carSaleDao.deleteCarSale(carSaleId)).thenReturn(false);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carSaleService.deleteCarSale(carSaleId));
        assertTrue(thrown.getMessage().contains("there is not car sale with id = " + carSaleId + " to delete"));
    }
}