package com.GTU.InventorySystem.model.devices;

import com.GTU.InventorySystem.model.Device;

/**
 * Laptop class implements Device interface
 * It has name, price and quantity as attributes
 * It has constructor to initialize the attributes
 * It has getter and setter methods for the attributes
 * @see Device
 * @author Melih Sapmaz
 */
public class Laptop implements Device {
    /**
     * The name of the laptop
     */
    private final String name;
    /**
     * The price of the laptop
     */
    private double price;
    /**
     * The quantity of the laptop
     */
    private int quantity;

    /**
     * Constructor for the Laptop class
     * @param name the name of the laptop
     * @param price the price of the laptop
     * @param quantity the quantity of the laptop
     */
    public Laptop(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the device
     * @return the category of the device
     */
    @Override
    public String getCategory() {
        return "Laptop";
    }

    /**
     * Returns the name of the device
     * @return the name of the device
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the device
     * @return the price of the device
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the device
     * @return the quantity of the device
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the price of the laptop
     * @param price the price of the device
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the device
     * @param quantity the quantity of the device
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
