package com.GTU.InventorySystem.model;

import com.GTU.InventorySystem.model.devices.*;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to represent the inventory of an electronics shop.
 * The inventory is a list of devices categorized into 5 categories: TV, Laptop, Smart Phone, Headphones, Smart Watch.
 * The inventory list is a linked list of array lists.
 * Each array list represents a category of devices.
 * Each device is an object of the Device class or its subclasses.
 * The inventory class has methods to add, remove, update, display, find minimum price device, sort, calculate total value, restock and export the inventory.
 * @author Melih Sapmaz
 */
public class Inventory {

    /**
     * The inventory list is a linked list of ArrayLists of devices.
     */
    private final LinkedList<ArrayList<Device>> inventory;

    /**
     * Constructor to initialize the inventory list with a fixed size of 5.
     * Time complexity = O(1), because the constructor initializes the inventory list with a fixed size of 5.
     */
    public Inventory() {
        inventory = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            inventory.add(new ArrayList<>());
        }
    }

    /**
     * Method to get the index of the category in the inventory list.
     * @param category The category of the device
     *                 Valid categories are: TV, Laptop, Smart Phone, Headphones, Smart Watch
     *                 If the category is invalid, the method returns -1
     *                 If the category is valid, the method returns the index of the category in the inventory list
     * @return The index of the category in the inventory list
     * Time complexity = O(1), because the method returns the index of the category in the inventory list.
     */
    private int getCategoryIndex(String category) {
        return switch(category) {
            case "TV" -> 0;
            case "Laptop" -> 1;
            case "Smart Phone" -> 2;
            case "Headphones" -> 3;
            case "Smart Watch" -> 4;
            default -> -1;
        };
    }

    /**
     * Method to add a device to the inventory list based on the category.
     * @param category The category of the device
     *                 Valid categories are: TV, Laptop, Smart Phone, Headphones, Smart Watch
     *                 If the category is invalid, the method prints an error message
     *                 If the category is valid, the method adds the device to the inventory list based on the category
     * @param name The name of the device
     * @param price The price of the device
     * @param quantity The quantity of the device
     * Time complexity = O(1), because the method adds a device to the inventory list based on the category.
     */
    public void addDevice(String category, String name, double price, int quantity) {
        if(getCategoryIndex(category) == -1) {
            System.out.println("Invalid category. Please enter a valid category.");
            return;
        }
        Device device = switch(category) {
            case "TV" -> new TV(name, price, quantity);
            case "Laptop" -> new Laptop(name, price, quantity);
            case "Smart Phone" -> new Smartphone(name, price, quantity);
            case "Headphones" -> new Headphones(name, price, quantity);
            case "Smart Watch" -> new Smartwatch(name, price, quantity);
            default -> throw new IllegalArgumentException("Invalid category");
        };
        inventory.get(getCategoryIndex(category)).add(device);
        System.out.println(device.getName() + ", " + device.getCategory() + ", " + device.getPrice() + "$, " + device.getQuantity() + " amount added...");
    }

    /**
     * Method to remove a device from the inventory list based on the category and name.
     * @param category The category of the device
     *                 Valid categories are: TV, Laptop, Smart Phone, Headphones, Smart Watch
     *                 If the category is invalid, the method prints an error message
     *                 If the category is valid, the method adds the device to the inventory list based on the category
     * @param name The name of the device
     * Time complexity = O(n), because the method searches for the device in the inventory list based on the category and name.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */
    public void removeDevice(String category, String name) {
        if(getCategoryIndex(category) == -1) {
            System.out.println("Invalid category. Please enter a valid category.");
            return;
        }
        ArrayList<Device> devices = inventory.get(getCategoryIndex(category));
        for(Device device : devices) {
            if(device.getName().equals(name)) {
                devices.remove(device);
                System.out.println(device.getName() + " removed...");
                return;
            }
        }
        System.out.println("Device not found...");
    }

    /**
     * Method to update the price and quantity of a device in the inventory list based on the name.
     * @param name The name of the device
     * @param price The new price of the device
     * @param quantity The new quantity of the device
     * Time complexity = O(n), because the method searches for the device in the inventory list based on the name.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */
    public void updateDevice(String name, double price, int quantity) {
        for(ArrayList<Device> devices : inventory) {
            for(Device device : devices) {
                if(device.getName().equals(name)) {
                    if(price == -1 && quantity == -1) {
                        System.out.println("No details provided to update...");
                        return;
                    }
                    if(price != -1){
                        device.setPrice(price);
                    }
                    if(quantity != -1){
                        device.setQuantity(quantity);
                    }
                    System.out.print(device.getName() + " details updated: ");
                    if(price != -1 && quantity == -1)
                        System.out.print("Price " + price + "$");
                    else if(price == -1)
                        System.out.print("Quantity " + quantity);
                    else
                        System.out.print("Price " + price + "$, Quantity " + quantity);
                    System.out.println();
                    return;
                }
            }
        }
        System.out.println("Device not found...");
    }

    /**
     * Method to display the devices in the inventory list.
     * Time complexity = O(n), because the method iterates over all the devices in the inventory list.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */
    public void displayDevices() {
        int i = 0;
        System.out.println("Device List:");
        for(ArrayList<Device> devices : inventory) {
            for(Device device : devices) {
                System.out.println(i + ". " + "Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
                i++;
            }
        }
    }

    /**
     * Method to identify the device with the minimum price in the inventory list.
     * Time complexity = O(n), because the method iterates over all the devices in the inventory list.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */

    public void identifyDeviceWithMinPrice() {
        double minPrice = Double.MAX_VALUE;
        Device minPriceDevice = null;
        for(ArrayList<Device> devices : inventory) {
            for(Device device : devices) {
                if(device.getPrice() < minPrice) {
                    minPrice = device.getPrice();
                    minPriceDevice = device;
                }
            }
        }
        if(minPriceDevice == null) {
            System.out.println("No devices in inventory");
            return;
        }
        System.out.println("The cheapest device is:");
        System.out.println("Category: " + minPriceDevice.getCategory() + ", Name: " + minPriceDevice.getName() + ", Price: " + minPriceDevice.getPrice() + "$, Quantity: " + minPriceDevice.getQuantity());
    }

    /**
     * Method to sort the devices in the inventory list based on the price.
     * Time complexity = O(n log n), because the method sorts all the devices in the inventory list based on the price.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     * The sorting algorithm used is TimSort which has an average time complexity of O(n log n).
     * n log n is greater than n, so the time complexity is O(n log n).
     */
    public void sortDevicesByPrice() {
        ArrayList<Device> allDevices = new ArrayList<>();
        for(ArrayList<Device> devices : inventory) {
            allDevices.addAll(devices);
        }
        allDevices.sort((device1, device2) -> {
            if(device1.getPrice() < device2.getPrice())
                return -1;
            else if(device1.getPrice() > device2.getPrice())
                return 1;
            return 0;
        });
        int i = 0;
        System.out.println("Devices sorted by price:");
        for(Device device : allDevices) {
            System.out.println(i + ". " + "Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
            i++;
        }
    }

    /**
     * Method to calculate the total value of the inventory.
     * Time complexity = O(n), because the method iterates over all the devices in the inventory list.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */
    public void calculateTotalValue() {
        double totalValue = 0;
        for(ArrayList<Device> devices : inventory) {
            for(Device device : devices) {
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        System.out.println("Total inventory value: $" + totalValue);
    }

    /**
     * Method to restock a device in the inventory list based on the name.
     * @param name The name of the device
     * @param quantity The quantity to restock
     * @param option The restocking option (ADD or REMOVE)
     * Time complexity = O(n), because the method searches for the device in the inventory list based on the name.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */
    public void restockDevice(String name, int quantity, RestockOption option) {
        for(ArrayList<Device> devices : inventory) {
            for(Device device : devices) {
                if(device.getName().equals(name)) {
                    if(option == RestockOption.ADD) {
                        device.setQuantity(device.getQuantity() + quantity);
                    } else if(option == RestockOption.REMOVE) {
                        device.setQuantity(device.getQuantity() - quantity);
                    }
                    System.out.println(device.getName() + " restocked. New quantity: " + device.getQuantity());
                    return;
                }
            }
        }
        System.out.println("Device not found...");
    }

    /**
     * Method to export the inventory to a file.
     * @param file The file to which the inventory will be exported
     * Time complexity = O(n), because the method iterates over all the devices in the inventory list.
     * Number of categories is fixed to 5 and the number of devices in each category is variable which is n.
     */
    public void exportInventoryToFile(File file) {

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println("Electronics Shop Inventory Report");
            writer.println("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("--------------------");
            writer.println("| No. | Category | Name | Price | Quantity |");
            writer.println("----------------------------------------");

            int i = 1;
            int totalQuantity = 0;
            double totalValue = 0;
            for(ArrayList<Device> devices : inventory) {
                for(Device device : devices) {
                    writer.println("| " + i + " | " + device.getCategory() + " | " + device.getName() + " | " + device.getPrice() + "$ | " + device.getQuantity() + " |");
                    totalQuantity += device.getQuantity();
                    totalValue += device.getPrice() * device.getQuantity();
                    i++;
                }
            }

            writer.println("----------------------------------------");
            writer.println();
            writer.println("Summary:");
            writer.println("-Total number of Devices: " + totalQuantity);
            writer.println("-Total inventory value: $" + totalValue);
            writer.println();
            writer.println("End of Report");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

}
