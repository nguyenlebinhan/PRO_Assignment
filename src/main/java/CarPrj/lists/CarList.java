package CarPrj.lists;

import CarPrj.entities.Car;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * 1 function to save data to file 'car.txt'.
 * 3 functions to search for a car by ID, frame ID, and engine ID.
 * NOTE: create getCarID(), getFrameID(), getEngineID() in Car.java
 * @author Nguyen Huu Thanh Vinh
 * @since 2025-06-22
 * @version 1.1
 */
public class CarList extends ArrayList<Car> {

    /**
     * Saves the list of Car objects to 'car.txt'
     * Each Car's information is written in a single line using toString().
     *
     * @param file The path of 'car.txt'.
     * @return true if the list is successfully saved; false if an error occurs or the list is empty.
     */
    BrandList brandList;
    public CarList(BrandList bList) {
        this.brandList = bList;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Car car : this) {
            sb.append(car.toString()).append("\n");
        }
        return sb.toString();
    }
    public String screenString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : this) {
            sb.append(car.screenString()).append("\n");
        }
        return sb.toString();
    }
    public boolean loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            this.clear();       // Clear the list before loading new data
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if ((parts.length != 5)) continue; // Skip lines with incorrect format

                String carID = parts[0].trim();
                String brandID = parts[1].trim();
                String color = parts[2].trim();
                String frameID = parts[3].trim();
                String engineID = parts[4].trim();

                int pos = brandList.searchID(brandID);
                if (pos == -1) continue; // Skip if brandID not found
                Brand b = brandList.get(pos);

                Car car = new Car(carID, b, color, frameID, engineID);
                this.add(car); // Add the car to the list
            }
            return true; // Successfully loaded
        } catch(IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
            return false; // Error occurred while loading
        }
    }




    public boolean saveToFile(String file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            //  Check is list is empty
            if (this.isEmpty()) {
                System.out.println("Warning: Car list is empty.");
                return false;
            }
            for (Car car : this) {
                writer.println(car.toString()); // returns <carID, brand.brandID, color, frameID, engineID>
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Searches for a Car by ID (case-insensitive).
     *
     * @param carID The ID of the car.
     * @return The index of the car in the list; if not found return -1.
     */
    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equalsIgnoreCase(carID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Searches for a Car by frame ID (case-insensitive).
     *  
     * @param frameID The frame ID of the car.
     * @return The index of the car in the list; if not found return -1.
     */
    public int searchFrame(String frameID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equalsIgnoreCase(frameID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Searches for a Car by engine ID (case-insensitive).
     *
     * @param engineID The engine ID of the car.
     * @return The index of the car in the list; if not found return -1.
     */
    public int searchEngine(String engineID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equalsIgnoreCase(engineID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        Scanner sc = new Scanner(System.in);

        String carID;
        do {
            System.out.print("Enter car ID: ");
            carID = sc.nextLine().trim();
        } while (searchID(carID) >= 0);
        
        Brand b = brandList.getUserChoice();
        if (b == null) {
            System.out.println("No brand selected. Aborting...");
            return;
        }

        String color;
        do {
            System.out.print("Enter car color: ");
            color = sc.nextLine().trim();
        } while (color.isEmpty()); // Ensure color is not empty
        
        String frameID;
        do {
            System.out.print("Enter frame ID (F0000): ");
            frameID = sc.nextLine().trim();
        } while (!frameID.matches("F\\d{4}") || searchFrame(frameID) >= 0); // Ensure frame ID matches F0000 format

        String engineID;
        do {
            System.out.print("Enter engine ID (E0000): ");
            engineID = sc.nextLine().trim();
        } while (!engineID.matches("E\\d{4}") || searchEngine(engineID) >= 0); // Ensure engine ID matches E0000 format

        Car car = new Car(carID, b, color, frameID, engineID);
        this.add(car); // Add the new car to the list  
        System.out.println("Car added successfully: ");
    }

}
