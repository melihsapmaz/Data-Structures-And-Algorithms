package com.GTU.InventorySystem.service;

import com.GTU.InventorySystem.model.RestockOption;
import com.GTU.InventorySystem.model.Inventory;

import java.io.File;

/**
 * Service class for the device operations
 * @author Melih Sapmaz
 */
public class DeviceService {
    /**
     * The inventory object
     */
    private final Inventory inventory;

    /**
     * Constructor for the DeviceService class
     * @param inventory The inventory object
     */
    public DeviceService(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Adds a device to the inventory
     * @param category The category of the device
     * @param name The name of the device
     * @param price The price of the device
     * @param quantity The quantity of the device
     */
    public void addDevice(String category, String name, double price, int quantity) {
        inventory.addDevice(category, name, price, quantity);
    }

    /**
     * Removes a device from the inventory
     * @param category The category of the device
     * @param name The name of the device
     */
    public void removeDevice(String category, String name) {
        inventory.removeDevice(category, name);
    }

    /**
     * Updates a device in the inventory
     * @param name The name of the device
     * @param price The price of the device
     * @param quantity The quantity of the device
     */
    public void updateDevice(String name, double price, int quantity) {
        inventory.updateDevice(name, price, quantity);
    }

    /**
     * Displays the devices in the inventory
     */
    public void displayDevices() {
        inventory.displayDevices();
    }

    /**
     * Identifies the device with the minimum price
     */
    public void identifyDeviceWithMinPrice() {
        inventory.identifyDeviceWithMinPrice();
    }

    /**
     * Identifies the device with the maximum price
     */
    public void sortDevicesByPrice() {
        inventory.sortDevicesByPrice();
    }

    /**
     * Calculates the total value of the inventory
     */
    public void calculateTotalValue() {
        inventory.calculateTotalValue();
    }

    /**
     * Restocks a device in the inventory
     * @param name The name of the device
     * @param quantity The quantity of the device
     * @param option The restocking option
     */
    public void restockDevice(String name, int quantity, RestockOption option) {
        inventory.restockDevice(name, quantity, option);
    }

    /**
     * Exports the inventory to a file
     * @param file The file to export the inventory
     */
    public void exportInventoryToFile(File file) {
        inventory.exportInventoryToFile(file);
    }
}
