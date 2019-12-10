package com.carhouse.rest.controller;

import com.carhouse.model.CarSale;
import com.carhouse.model.dto.CarSaleDto;
import com.carhouse.rest.handler.RestExceptionHandler;
import com.carhouse.rest.testConfig.RestTestConfig;
import com.carhouse.service.CarSaleService;
import com.carhouse.service.exception.WriteFileException;
import com.carhouse.service.exception.WrongReferenceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = RestTestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarSaleControllerTest {

    private static final String CAR_SALE_LIST_STORAGE_JSON = "car-sale-list-storage.json";
    private static final String CAR_SALE_DTO_LIST_GET_URL = "/carSale";
    private static final String CAR_SALE_GET_URL = "/carSale/{carSaleId}";
    private static final String CAR_SALE_ADD_URL = "/carSale";
    private static final String CAR_SALE_UPDATE_URL = "/carSale/{carSaleId}";
    private static final String CAR_SALE_DELETE_URL = "/carSale/{id}";

    @Mock
    private CarSaleService carSaleService;

    @InjectMocks
    private CarSaleController carSaleController;

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;
    private List<CarSale> listCarSale;
    private MockMultipartFile multipartFile;
    private MockMultipartFile jsonPart;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(carSaleController)
                .setControllerAdvice(restExceptionHandler)
                .build();
        File file = new ClassPathResource(CAR_SALE_LIST_STORAGE_JSON).getFile();
        listCarSale = objectMapper.readValue(file, new TypeReference<List<CarSale>>() {
        });
        multipartFile = new MockMultipartFile("file", "test.txt", "image/*",
                "There should be bytes of image".getBytes());
        jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(listCarSale.get(2)));
    }

    @Test
    void getCarSalesDto() throws Exception {
        List<CarSaleDto> listCarSaleDto = new ArrayList<>() {{
            add(new CarSaleDto().setCarSaleId(1));
            add(new CarSaleDto().setCarSaleId(2));
        }};
        when(carSaleService.getListCarSales(anyMap())).thenReturn(listCarSaleDto);
        mockMvc.perform(get(CAR_SALE_DTO_LIST_GET_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(listCarSaleDto)));
        verify(carSaleService, times(1)).getListCarSales(anyMap());
    }

    @Test
    void getCarSale() throws Exception {
        int carSaleId = 1;
        when(carSaleService.getCarSale(carSaleId)).thenReturn(listCarSale.get(carSaleId));
        mockMvc.perform(get(CAR_SALE_GET_URL, carSaleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(listCarSale.get(carSaleId))));
        verify(carSaleService, times(1)).getCarSale(carSaleId);
    }

    @Test
    void getNotExistCarSale() throws Exception {
        int carSaleId = 21;
        when(carSaleService.getCarSale(carSaleId)).thenThrow(NotFoundException.class);
        mockMvc.perform(get(CAR_SALE_GET_URL, carSaleId))
                .andExpect(status().isNotFound());
        verify(carSaleService, times(1)).getCarSale(carSaleId);
    }

    @Test
    void addCarSale() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        when(carSaleService.addCarSale(any(CarSale.class), any(MultipartFile.class))).thenReturn(carSaleId);
        MockMultipartFile jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(carSale));
        mockMvc.perform(MockMvcRequestBuilders.multipart(CAR_SALE_ADD_URL)
                .file(multipartFile)
                .file(jsonPart))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(carSaleId)));
        verify(carSaleService, times(1)).addCarSale(any(CarSale.class), any(MultipartFile.class));
    }

    @Test
    void addCarSaleWithWrongReference() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        MockMultipartFile jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(carSale));
        when(carSaleService.addCarSale(any(CarSale.class), any(MultipartFile.class)))
                .thenThrow(WrongReferenceException.class);
        mockMvc.perform(MockMvcRequestBuilders.multipart(CAR_SALE_ADD_URL)
                .file(multipartFile)
                .file(jsonPart))
                .andExpect(status().isFailedDependency());
        verify(carSaleService, times(1)).addCarSale(any(CarSale.class), any(MultipartFile.class));
    }

    @Test
    void addCarSaleErrorWritingFile() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        MockMultipartFile jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(carSale));
        when(carSaleService.addCarSale(any(CarSale.class), any(MultipartFile.class)))
                .thenThrow(WriteFileException.class);
        mockMvc.perform(MockMvcRequestBuilders.multipart(CAR_SALE_ADD_URL)
                .file(multipartFile)
                .file(jsonPart))
                .andExpect(status().isUnprocessableEntity());
        verify(carSaleService, times(1)).addCarSale(any(CarSale.class), any(MultipartFile.class));
    }

    @Test
    void updateCarSale() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        MockMultipartFile jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(carSale));
        mockMvc.perform(MockMvcRequestBuilders.multipart(CAR_SALE_UPDATE_URL, carSaleId)
                .file(multipartFile)
                .file(jsonPart))
                .andExpect(status().isOk());
        verify(carSaleService, times(1)).updateCarSale(any(CarSale.class),
                any(MultipartFile.class));
    }

    @Test
    void updateCarSaleWithWrongReference() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        MockMultipartFile jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(carSale));
        doThrow(WrongReferenceException.class).when(carSaleService).updateCarSale(any(CarSale.class),
                any(MultipartFile.class));
        mockMvc.perform(MockMvcRequestBuilders.multipart(CAR_SALE_UPDATE_URL, carSaleId)
                .file(multipartFile)
                .file(jsonPart))
                .andExpect(status().isFailedDependency());
        verify(carSaleService, times(1)).updateCarSale(any(CarSale.class),
                any(MultipartFile.class));
    }

    @Test
    void updateNotExistCarSale() throws Exception {
        int carSaleId = 2;
        CarSale carSale = listCarSale.get(carSaleId);
        MockMultipartFile jsonPart = new MockMultipartFile("carSale", "carSale",
                "application/json", objectMapper.writeValueAsBytes(carSale));
        doThrow(NotFoundException.class).when(carSaleService).updateCarSale(any(CarSale.class),
                any(MultipartFile.class));
        mockMvc.perform(MockMvcRequestBuilders.multipart(CAR_SALE_UPDATE_URL, carSaleId)
                .file(multipartFile)
                .file(jsonPart))
                .andExpect(status().isNotFound());
        verify(carSaleService, times(1)).updateCarSale(any(CarSale.class),
                any(MultipartFile.class));
    }

    @Test
    void deleteCarSale() throws Exception {
        int carSaleId = 1;
        mockMvc.perform(delete(CAR_SALE_DELETE_URL, carSaleId))
                .andExpect(status().isOk());
        verify(carSaleService, times(1)).deleteCarSale(carSaleId);
    }

    @Test
    void deleteNotExistCarSale() throws Exception {
        int carSaleId = 1;
        doThrow(NotFoundException.class).when(carSaleService).deleteCarSale(carSaleId);
        mockMvc.perform(delete(CAR_SALE_DELETE_URL, carSaleId))
                .andExpect(status().isNotFound());
        verify(carSaleService, times(1)).deleteCarSale(carSaleId);
    }
}
