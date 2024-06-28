package com.GTU.InventorySystem.model.devices;

import com.GTU.InventorySystem.model.Device;

/**
 * Smartphone class implements Device interface
 * This class is used to create Smartphone objects
 * It has a constructor to initialize the object
 * It has getter and setter methods to access and modify the object's properties
 * It has getCategory method to return the category of the object
 * @see Device
 * @author Melih Sapmaz
 */
public class Smartphone implements Device {
    /**
     * name: name of the smartphone
     */
    private final String name;
    /**
     * price: price of the smartphone
     */
    private double price;
    /**
     * quantity: quantity of the smartphone
     */
    private int quantity;

    /**
     * Constructor to initialize the smartphone object
     * @param name name of the smartphone
     * @param price price of the smartphone
     * @param quantity quantity of the smartphone
     */
    public Smartphone(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * getCategory method to return the category of the object
     * @return category of the object
     */
    @Override
    public String getCategory() {
        return "Smart Phone";
    }

    /**
     * Getter method to get the name of the smartphone
     * @return name of the smartphone
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter method to get the price of the smartphone
     * @return price of the smartphone
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Getter method to get the quantity of the smartphone
     * @return quantity of the smartphone
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter method to set the price of the smartphone
     * @param price price of the smartphone
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter method to set the quantity of the smartphone
     * @param quantity quantity of the smartphone
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
