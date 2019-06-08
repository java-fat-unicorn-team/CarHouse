package com.spring.rest.model;

import java.math.BigDecimal;
import java.util.Date;

public class CarSale {
    private int carSaleId;
    private BigDecimal price;
    private Date date;
    private User user;
    private Car car;

    public CarSale() {
    }

    public CarSale(int carSaleId, BigDecimal price, Date date, User user, Car car) {
        this.carSaleId = carSaleId;
        this.price = price;
        this.date = date;
        this.user = user;
        this.car = car;
    }

    public int getCarSaleId() {
        return carSaleId;
    }

    public void setCarSaleId(int carSaleId) {
        this.carSaleId = carSaleId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal pPrice) {
        this.price = pPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date pDate) {
        this.date = pDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User pUser) {
        this.user = pUser;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(final Car pCar) {
        this.car = pCar;
    }

    @Override
    public String toString() {
        return "carSaleId=" + carSaleId +
                ", price=" + price +
                ", date=" + date +
                ", " + user +
                ", " + car;
    }
}
