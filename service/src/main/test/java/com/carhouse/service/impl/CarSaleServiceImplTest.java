package com.carhouse.service.impl;

import com.carhouse.dao.CarSaleDao;
import com.carhouse.model.CarSale;
import org.junit.jupiter.api.BeforeEach;
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

    private List<CarSale> listCarSale;

    @BeforeEach
    void addCarSales() {
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
    void getCarSale() {
        int carSaleId = 2;
        when(carSaleDao.getCarSale(carSaleId)).thenReturn(listCarSale.get(carSaleId));
        assertEquals(listCarSale.get(carSaleId).getCarSaleId(), carSaleService.getCarSale(carSaleId).getCarSaleId());
    }

    @Test
    void getNonExistentCarSale() {
        when(carSaleDao.getCarSale(10)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> carSaleService.getCarSale(10));
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
        assertThrows(DataIntegrityViolationException.class, () -> carSaleDao.addCarSale(carSale));
    }

    @Test
    void updateCarSale() {
        CarSale carSale = new CarSale(5);
        carSaleService.updateCarSale(carSale);
        verify(carSaleDao, times(1)).updateCarSale(carSale);
    }

    @Test
    void deleteCarSale() {
        carSaleService.deleteCarSale(3);
        verify(carSaleDao, times(1)).deleteCarSale(3);
    }
}