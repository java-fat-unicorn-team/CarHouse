package com.carhouse.service.impl;

import com.carhouse.dao.CarModelDao;
import com.carhouse.model.CarModel;
import com.carhouse.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides method to get CarModel models on service layer.
 * It is realisation of CarModelService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarModelService
 */
@Service
public class CarModelServiceImpl implements CarModelService {

    private CarModelDao carModelDao;

    /**
     * Instantiates a new Car model service.
     *
     * @param carModelDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CarModelServiceImpl(CarModelDao carModelDao) {
        this.carModelDao = carModelDao;
    }

    /**
     * Gets car models.
     *
     * @return the list of car models
     */
    @Override
    public List<CarModel> getCarModels() {
        return carModelDao.getCarModels();
    }

    /**
     * Gets car model by id.
     *
     * @param id the car model id.
     * @return the car model
     */
    @Override
    public CarModel getCarModel(int id) {
        return carModelDao.getCarModel(id);
    }
}
