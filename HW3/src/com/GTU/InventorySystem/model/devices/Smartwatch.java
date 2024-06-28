package com.GTU.InventorySystem.model.devices;

import com.GTU.InventorySystem.model.Device;

/**
 * Smartwatch class that implements Device interface
 * This class is used to create Smartwatch objects
 * Smartwatch objects are used to store information about Smartwatch products
 * Smartwatch objects have a name, price, and quantity
 * Smartwatch objects can return their category, name, price, and quantity
 * Smartwatch objects can set their price and quantity
 * @see Device
 * @author Melih Sapmaz
 */
public class Smartwatch implements Device {
    /**
     * The name of the Smartwatch
     */
    private final String name;
    /**
     * The price of the Smartwatch
     */
    private double price;
    /**
     * The quantity of the Smartwatch
     */
    private int quantity;

    /**
     * Constructor for Smartwatch class
     * @param name The name of the Smartwatch
     * @param price The price of the Smartwatch
     * @param quantity The quantity of the Smartwatch
     */
    public Smartwatch(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the Smartwatch
     * @return The category of the Smartwatch
     */
    @Override
    public String getCategory() {
        return "Smart Watch";
    }

    /**
     * Returns the name of the Smartwatch
     * @return The name of the Smartwatch
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the Smartwatch
     * @return The price of the Smartwatch
     */
    @Override
    public double getPrice() {
        return price;
    }
    /**
     * Returns the quantity of the Smartwatch
     * @return The quantity of the Smartwatch
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
    /**
     * Sets the price of the Smartwatch
     * @param price The new price of the Smartwatch
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the Smartwatch
     * @param quantity The new quantity of the Smartwatch
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
