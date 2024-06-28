package com.GTU.InventorySystem.model;

import com.GTU.InventorySystem.model.devices.*;

/**
 * Interface Device that has the methods to get the category, name, price, quantity, set the price and set the quantity of the device
 * Time Complexity: O(1) because it only has the methods to get the category, name, price, quantity, set the price and set the quantity of the device
 * @see Headphones
 * @see Laptop
 * @see Smartphone
 * @see Smartwatch
 * @see TV
 * @author Melih Sapmaz
 */
public interface Device {

    /**
     * Method to get the category of the device
     * @return the category of the device
     * Time Complexity: O(1) because it only returns the category of the device
     */
    String getCategory();

    /**
     * Method to get the name of the device
     * @return the name of the device
     * Time Complexity: O(1) because it only returns the name of the device
     */
    String getName();

    /**
     * Method to get the price of the device
     * @return the price of the device
     * Time Complexity: O(1) because it only returns the price of the device
     */
    double getPrice();

    /**
     * Method to get the quantity of the device
     * @return the quantity of the device
     * Time Complexity: O(1) because it only returns the quantity of the device
     */
    int getQuantity();

    /**
     * Method to get the total price of the device
     * @param price the quantity of the device
     * Time Complexity: O(1) because it only returns the total price of the device
     */
    void setPrice(double price);

    /**
     * Method to set the quantity of the device
     * @param quantity the quantity of the device
     * Time Complexity: O(1) because it only sets the quantity of the device
     */
    void setQuantity(int quantity);


}
