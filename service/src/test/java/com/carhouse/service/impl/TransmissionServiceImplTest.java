package com.carhouse.service.impl;

import com.carhouse.dao.TransmissionDao;
import com.carhouse.model.Transmission;
import com.carhouse.service.TransmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransmissionServiceImplTest {

    @Mock
    private TransmissionDao transmissionDao;

    @InjectMocks
    private TransmissionServiceImpl transmissionService;

    private List<Transmission> listTransmissions;
    @BeforeEach
    public void addTransmissions() {
        listTransmissions = new ArrayList<>() {{
            add(new Transmission(1,"Manual"));
            add(new Transmission(2,"Stick"));
        }};
    }

    @Test
    void getTransmissions() {
        when(transmissionDao.getTransmissions()).thenReturn(listTransmissions);
        assertEquals(listTransmissions.size(), transmissionService.getTransmissions().size());
        verify(transmissionDao, times(1)).getTransmissions();
    }

    @Test
    void getTransmission() {
        int id = 1;
        Transmission transmission = listTransmissions.get(id);
        when(transmissionDao.getTransmission(id)).thenReturn(transmission);
        assertEquals(transmission.getTransmission(), transmissionService.getTransmission(id).getTransmission());
        verify(transmissionDao, times(1)).getTransmission(id);
    }

    @Test
    void getNonExistentTransmission() {
        when(transmissionDao.getTransmission(1)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> transmissionService.getTransmission(1));
    }
}