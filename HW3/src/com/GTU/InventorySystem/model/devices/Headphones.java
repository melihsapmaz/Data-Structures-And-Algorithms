package com.GTU.InventorySystem.model.devices;

import com.GTU.InventorySystem.model.Device;

/**
 * Headphones class that implements the Device interface
 * Time Complexity: O(1) for all methods because they only return the instance variables
 * @see Device
 * @author Melih Sapmaz
 */
public class Headphones implements Device{

    /**
     * The name of the headphones
     */
    private final String name;
    /**
     * The price of the headphones
     */
    private double price;
    /**
     * The quantity of the headphones
     */
    private int quantity;

    /**
     * Constructor for the Headphones class
     * @param name the name of the headphones
     * @param price the price of the headphones
     * @param quantity the quantity of the headphones
     * Time Complexity: O(1) because it only initializes the instance variables
     */
    public Headphones(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    /**
     * Returns the category of the device
     * @return the category of the device
     * Time Complexity: O(1) because it only returns a string
     */
    @Override
    public String getCategory() {
        return "Headphones";
    }

    /**
     * Returns the name of the device
     * @return the name of the device
     * Time Complexity: O(1) because it only returns a string
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the device
     * @return the price of the device
     * Time Complexity: O(1) because it only returns a double
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the device
     * @return the quantity of the device
     * Time Complexity: O(1) because it only returns an integer
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the price of the device
     * @param price the new price of the device
     * Time Complexity: O(1) because it only sets the price
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the device
     * @param quantity the new quantity of the device
     * Time Complexity: O(1) because it only sets the quantity
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
