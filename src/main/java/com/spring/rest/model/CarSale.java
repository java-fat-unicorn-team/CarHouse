package com.spring.rest.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Car sale model.
 */
public class CarSale {
    /**
     * car sale id.
     */
    private int carSaleId;
    /**
     * car price.
     */
    private BigDecimal price;
    /**
     * date when announcement was added.
     */
    private Date date;
    /**
     * user who added this announcement.
     */
    private User user;
    /**
     * car characteristics.
     */
    private CarCharacteristics carCharacteristics;

    /**
     * Instantiates a new Car sale.
     */
    public CarSale() {
    }

    /**
     * Instantiates a new Car sale.
     *
     * @param carSaleId          the car sale id
     * @param price              the price
     * @param date               the date
     * @param user               the user
     * @param carCharacteristics the carCharacteristics
     */
    public CarSale(final int carSaleId, final BigDecimal price,
                   final Date date, final User user,
                   final CarCharacteristics carCharacteristics) {
        this.carSaleId = carSaleId;
        this.price = price;
        this.date = date;
        this.user = user;
        this.carCharacteristics = carCharacteristics;
    }

    /**
     * Gets carCharacteristics sale id.
     *
     * @return the carCharacteristics sale id
     */
    public int getCarSaleId() {
        return carSaleId;
    }

    /**
     * Sets carCharacteristics sale id.
     *
     * @param carSaleId the carCharacteristics sale id
     */
    public void setCarSaleId(final int carSaleId) {
        this.carSaleId = carSaleId;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Gets carCharacteristics.
     *
     * @return the carCharacteristics
     */
    public CarCharacteristics getCarCharacteristics() {
        return carCharacteristics;
    }

    /**
     * Sets carCharacteristics.
     *
     * @param carCharacteristics the carCharacteristics
     */
    public void setCarCharacteristics(
            final CarCharacteristics carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }

    @Override
    public final String toString() {
        return "carSaleId=" + carSaleId
                + ", price=" + price
                + ", date=" + date
                + ", " + user
                + ", " + carCharacteristics;
    }
}
