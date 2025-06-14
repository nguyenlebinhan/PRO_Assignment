package CarPrj.lists;

import CarPrj.entities.Car;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 1 function to save data to file 'car.txt'.
 * 3 functions to search for a car by ID, frame ID, and engine ID.
 * NOTE: create getCarID(), getFrameID(), getEngineID() in Car.java
 * @author MinhPN
 * @since 2025-06-13
 * @version 1
 */
public class CarList extends ArrayList<Car> {

    /**
     * Saves the list of Car objects to 'car.txt'
     * Each Car's information is written in a single line using toString().
     *
     * @param file The path of 'car.txt'.
     * @return true if the list is successfully saved; false if an error occurs or the list is empty.
     */
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

}
