package com.GTU.InventorySystem.main;

import com.GTU.InventorySystem.model.RestockOption;
import com.GTU.InventorySystem.service.DeviceService;
import com.GTU.InventorySystem.model.Inventory;
import java.io.File;
import java.util.Scanner;
/**
 * Main class to test the Inventory class
 * Time complexity = O(n log n) (for sorting) it is the worst case time complexity
 * @author Melih Sapmaz
 */
public class Main {
    /**
     * Constructs a new Main object.
     * This constructor is the entry point of the program.
     */
    public Main() {
    }

    /**
     * Main method to test the Inventory class
     * Time complexity = O(n log n) (for sorting) it is the worst case time complexity
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        DeviceService deviceService = new DeviceService(inventory);


        inventory.addDevice("TV", "Samsung TV", 500.0, 10);
        inventory.addDevice("Laptop", "Dell Laptop", 800.0, 5);
        inventory.addDevice("Smart Phone", "iPhone", 1000.0, 3);
        inventory.addDevice("Headphones", "Sony Headphones", 100.0, 20);
        inventory.addDevice("Smart Watch", "Apple Watch", 400.0, 8);
        inventory.addDevice("TV", "LG TV", 600.0, 15);
        inventory.addDevice("Laptop", "HP Laptop", 700.0, 7);
        inventory.addDevice("Smart Phone", "Samsung Galaxy", 900.0, 4);
        inventory.addDevice("Headphones", "Bose Headphones", 150.0, 10);
        inventory.addDevice("Smart Watch", "Samsung Watch", 300.0, 12);


        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Please select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total value of the inventory");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch(Exception e) {
                System.out.println("Invalid option. Please enter a valid option.");
                continue;
            }

            switch(option) {
                case 1:
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter device name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price;
                    try {
                        price = Double.parseDouble(scanner.nextLine());
                    } catch(Exception e) {
                        System.out.println("Invalid price. Please enter a valid price.");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(scanner.nextLine());
                    } catch(Exception e) {
                        System.out.println("Invalid quantity. Please enter a valid quantity.");
                        break;
                    }
                    if(price < 0 || quantity < 0) {
                        System.out.println("Price and quantity cannot be negative.");
                        break;
                    }
                    if(category.equals("TV") || category.equals("Laptop") || category.equals("Smart Phone") || category.equals("Headphones") || category.equals("Smart Watch")) {
                        deviceService.addDevice(category, name, price, quantity);
                    } else {
                        System.out.println("Invalid category. Please enter a valid category.");
                    }
                    break;
                case 2:
                    System.out.print("Enter category name: ");
                    category = scanner.nextLine();
                    System.out.print("Enter device name: ");
                    name = scanner.nextLine();
                    if(category.equals("TV") || category.equals("Laptop") || category.equals("Smart Phone") || category.equals("Headphones") || category.equals("Smart Watch")) {
                        deviceService.removeDevice(category, name);
                    } else {
                        System.out.println("Invalid category. Please enter a valid category.");
                    }
                    deviceService.removeDevice(category, name);
                    break;
                case 3:
                    System.out.print("Enter the name of the device to update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new price (leave blank to keep current price): ");
                    double newPrice;
                    try {
                        String priceStr = scanner.nextLine();
                        newPrice = priceStr.isEmpty() ? -1 : Double.parseDouble(priceStr);

                    } catch(Exception e) {
                        System.out.println("Invalid price. Please enter a valid price.");
                        break;
                    }
                    int newQuantity;
                    System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                    try {
                        String quantityStr = scanner.nextLine();
                        newQuantity = quantityStr.isEmpty() ? -1 : Integer.parseInt(quantityStr);
                    } catch(Exception e) {
                        System.out.println("Invalid quantity. Please enter a valid quantity.");
                        break;
                    }
                    deviceService.updateDevice(name, newPrice, newQuantity);
                    break;
                case 4:
                    deviceService.displayDevices();
                    break;
                case 5:
                    deviceService.identifyDeviceWithMinPrice();
                    break;
                case 6:
                    deviceService.sortDevicesByPrice();
                    break;
                case 7:
                    deviceService.calculateTotalValue();
                    break;
                case 8:
                    System.out.print("Enter the name of the device to restock: ");
                    name = scanner.nextLine();
                    System.out.print("Do you want to add or remove stock? (Add/Remove): ");
                    String action = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    try {
                        quantity = Integer.parseInt(scanner.nextLine());
                        if(quantity < 0) {
                            System.out.println("Quantity cannot be negative.");
                            break;
                        }
                    } catch(Exception e) {
                        System.out.println("Invalid quantity. Please enter a valid quantity.");
                        break;
                    }
                    if(action.equals("Add")) {
                        deviceService.restockDevice(name, quantity, RestockOption.ADD);
                    } else if(action.equals("Remove")) {
                        deviceService.restockDevice(name, quantity, RestockOption.REMOVE);
                    } else {
                        System.out.println("Invalid action. Please enter Add or Remove.");
                    }
                    break;
                case 9:
                    File file = new File("inventory.txt");
                    deviceService.exportInventoryToFile(file);
                    break;
                case 0:
                    //Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
            }
        }while(true);
    }
}