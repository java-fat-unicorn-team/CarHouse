package com.carhouse.service.impl;

import com.carhouse.dao.TransmissionDao;
import com.carhouse.model.Transmission;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransmissionServiceImplTest {

    @Mock
    private TransmissionDao transmissionDao;

    @InjectMocks
    private TransmissionServiceImpl transmissionService;

    private static List<Transmission> listTransmissions;

    @BeforeAll
    static void addTransmissions() {
        listTransmissions = new ArrayList<>() {{
            add(new Transmission(1, "Manual"));
            add(new Transmission(2, "Stick"));
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
        int transmissionId = 1;
        Transmission transmission = listTransmissions.get(transmissionId);
        when(transmissionDao.getTransmission(transmissionId)).thenReturn(transmission);
        assertEquals(transmission.getTransmission(),
                transmissionService.getTransmission(transmissionId).getTransmission());
        verify(transmissionDao, times(1)).getTransmission(transmissionId);
    }

    @Test
    void getNonExistentTransmission() {
        int transmissionId = 1;
        when(transmissionDao.getTransmission(transmissionId)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> transmissionService.getTransmission(transmissionId));
    }
}