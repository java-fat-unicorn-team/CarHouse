package com.carhouse.service.impl;

import com.carhouse.dao.CarMakeDao;
import com.carhouse.model.CarMake;
import com.carhouse.service.CarMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class provides method to get CarMake models on service layer.
 * It is realisation of CarMakeService interface
 *
 * @author Katuranau Maksimilyan
 * @see CarMakeService
 */
@Service
public class CarMakeServiceImpl implements CarMakeService {

    private CarMakeDao carMakeDao;

    /**
     * Instantiates a new Car make service.
     *
     * @param carMakeDao the class is provided CRUD operations for fuel type model.
     */
    @Autowired
    public CarMakeServiceImpl(final CarMakeDao carMakeDao) {
        this.carMakeDao = carMakeDao;
    }

    /**
     * Gets car makes.
     *
     * @return the list of car makes
     */
    @Override
    public List<CarMake> getCarMakes() {
        return carMakeDao.getCarMakes();
    }

    /**
     * Gets car make by id.
     *
     * @param id the car make id
     * @return the car make
     */
    @Override
    public CarMake getCarMake(final int id) {
        return carMakeDao.getCarMake(id);
    }
}
