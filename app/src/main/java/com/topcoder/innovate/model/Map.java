package com.topcoder.innovate.model;

import java.io.Serializable;

/**
 * Created by 王志杰 on 2017/8/6.
 */
@SuppressWarnings("serial")
public class Map implements Serializable {
    /**
     * <p>
     * The map city.
     * </p>
     */
    private String city;

    /**
     * <p>
     * The map name.
     * </p>
     */
    private String name;
    /**
     * <p>
     * The map latitude.
     * </p>
     */
    private double latitude;
    /**
     * <p>
     * The map longitude.
     * </p>
     */
    private double longitude;
    /**
     * <p>
     * The map address.
     * </p>
     */
    private String address;

    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getName() {
        return name;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param name - new value for a namesake field.
     */
    public void setName(String name) {
        this.name=name;
    }
    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getCity() {
        return city;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param city - new value for a namesake field.
     */
    public void setCity(String city) {
        this.city=city;
    }


    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public String getAddress() {
        return address;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param address - new value for a namesake field.
     */
    public void setAddress(String address) {
        this.address=address;
    }
    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return value of a namesake field.
     */
    public double getLatitude() {
        return latitude;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param latitude - new value for a namesake field.
     */
    public void setLatitude(double latitude) {
        this.latitude=latitude;
    }
    /**
     * <p>
     * Simple getter for a namesake field.
     * </p>
     *
     * @return longitude - value of a namesake field.
     */
    public double getLongitude() {
        return longitude;
    }
    /**
     * <p>
     * Simple setter for a namesake field.
     * </p>
     *
     * @param  - new value for a namesake field.
     */
    public void setLongitude(double longitude) {
        this.longitude=longitude;
    }
}

