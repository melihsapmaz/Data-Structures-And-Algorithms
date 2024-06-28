package com.GTU.InventorySystem.model.devices;

import com.GTU.InventorySystem.model.Device;

/**
 * TV class that implements the Device interface
 * This class is used to create TV objects
 * TV objects are used to store information about TV products
 * TV objects have a name, price, and quantity
 * TV objects can return their category, name, price, and quantity
 * TV objects can set their price and quantity
 * @see Device
 * @author Melih Sapmaz
 */
public class TV implements Device {
    /**
     * The name of the TV
     */
    private final String name;
    /**
     * The price of the TV
     */
    private double price;
    /**
     * The quantity of the TV
     */
    private int quantity;

    /**
     * Constructor for TV class
     * @param name The name of the TV
     * @param price The price of the TV
     * @param quantity The quantity of the TV
     */
    public TV(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the category of the TV
     * @return The category of the TV
     */
    @Override
    public String getCategory() {
        return "TV";
    }

    /**
     * Returns the name of the TV
     * @return The name of the TV
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the TV
     * @return The price of the TV
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the TV
     * @return The quantity of the TV
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the price of the TV
     * @param price The price of the TV
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the TV
     * @param quantity The quantity of the TV
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
