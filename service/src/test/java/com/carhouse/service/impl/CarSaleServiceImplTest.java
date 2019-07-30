package com.carhouse.service.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.CarSale;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarSaleServiceImplTest {

    @Mock
    private CarSaleDao carSaleDao;

    @InjectMocks
    private CarSaleServiceImpl carSaleService;

    private static List<CarSale> listCarSale;

    @BeforeAll
    static void addCarSales() {
        listCarSale = new ArrayList<>() {{
            add(new CarSale(1));
            add(new CarSale(2));
            add(new CarSale(3));
        }};
    }

    @Test
    void getCarSales() {
        when(carSaleDao.getCarSales()).thenReturn(listCarSale);
        assertEquals(listCarSale.size(), carSaleService.getCarSales().size());
        verify(carSaleDao, times(1)).getCarSales();
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
        assertTrue(thrown.getMessage().contains("there is not such car sale"));

    }

    @Test
    void addCarSale() {
        CarSale carSale = new CarSale(7);
        carSaleService.addCarSale(carSale);
        verify(carSaleDao, times(1)).addCarSale(carSale);
    }

    @Test
    void addCarSaleWithWrongReference() {
        CarSale carSale = new CarSale(7);
        when(carSaleDao.addCarSale(carSale)).thenThrow(DataIntegrityViolationException.class);
        WrongReferenceException thrown = assertThrows(WrongReferenceException.class,
                () -> carSaleService.addCarSale(carSale));
        assertTrue(thrown.getMessage().contains("there is wrong references in your car sale"));
    }

    @Test
    void updateCarSale() throws NotFoundException {
        CarSale carSale = new CarSale(5);
        carSaleService.updateCarSale(carSale);
        verify(carSaleDao, times(1)).updateCarSale(carSale);
    }

    @Test
    void updateCarSaleWithWrongReference() {
        CarSale carSale = new CarSale(4);
        when(carSaleDao.updateCarSale(carSale)).thenThrow(DataIntegrityViolationException.class);
        WrongReferenceException thrown = assertThrows(WrongReferenceException.class,
                () -> carSaleService.updateCarSale(carSale));
        assertTrue(thrown.getMessage().contains("there is wrong references in your car sale"));
    }

    @Test
    void updateNotExistCarSale() {
        CarSale carSale = new CarSale(17);
        when(carSaleDao.getCarSale(carSale.getCarSaleId())).thenThrow(EmptyResultDataAccessException.class);
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> carSaleService.updateCarSale(carSale));
        assertTrue(thrown.getMessage().contains("there is not such car sale"));
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
        assertTrue(thrown.getMessage().contains("car sale with id = " + carSaleId
                + " you try to delete does not exist"));
    }
}