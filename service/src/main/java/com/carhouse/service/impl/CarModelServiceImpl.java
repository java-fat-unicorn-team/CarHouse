package com.carhouse.service.impl;

import com.carhouse.dao.CarMakeDao;
import com.carhouse.dao.CarModelDao;
import com.carhouse.model.CarModel;
import com.carhouse.service.CarModelService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private CarMakeDao carMakeDao;

    private static final Logger LOGGER = LogManager.getLogger(CarModelServiceImpl.class);

    /**
     * Instantiates a new Car model service.
     *
     * @param carModelDao the class is provided CRUD operations for fuel type model.
     * @param carMakeDao  the car make dao
     */
    @Autowired
    public CarModelServiceImpl(final CarModelDao carModelDao, final CarMakeDao carMakeDao) {
        this.carModelDao = carModelDao;
        this.carMakeDao = carMakeDao;
    }

    /**
     * Gets car models of selected car make.
     *
     * @param carMakeId the car make id to get car models
     * @return the list of car models
     * @throws NotFoundException if there is not car make with selected id
     */
    @Override
    public List<CarModel> getCarModels(final Integer carMakeId) throws NotFoundException {
        LOGGER.debug("method getCarModels");
        try {
            carMakeDao.getCarMake(carMakeId);
            return carModelDao.getCarModels(carMakeId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not car make with id = " + carMakeId);
        }
    }

    /**
     * Gets car model by id.
     *
     * @param id the car model id.
     * @return the car model
     * @throws NotFoundException if there is not car model with selected id
     */
    @Override
    public CarModel getCarModel(final int id) throws NotFoundException {
        LOGGER.debug("method getCarModel with parameter: [{}]", id);
        try {
            return carModelDao.getCarModel(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("there is not car model with id = " + id);
        }
    }
}
