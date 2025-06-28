package CarPrj.lists;

import CarPrj.entities.Brand;
import CarPrj.entities.Car;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.*;
import java.util.*;
import CarPrj.entities.Brand;
import CarPrj.main.Menu;
import CarPrj.lists.BrandList;

/**
 * 1 function to save data to file 'car.txt'. 3 functions to search for a car by ID, frame ID, and engine ID. NOTE: create getCarID(), getFrameID(), getEngineID() in Car.java
 *
 * @author MinhPN
 * @since 2025-06-13
 * @version 1
 */
public class CarList extends ArrayList<Car> {

    Menu menu = new Menu();
    BrandList brandlist = new BrandList();
    Scanner sc = new Scanner(System.in);
    private final BrandList brandList;

    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }

    /**
     * Saves the list of Car objects to 'car.txt' Each Car's information is written in a single line using toString().
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

    /**
     * author QuanVH add a Car to the list
     */
    public void addCar() {
        String carID, color, frameID, engineID;
        System.out.println("ADD NEW CAR");
        System.out.println("-----------------------------------");
        do {
            System.out.print("    CarID: ");
            carID = sc.nextLine().toUpperCase();
            if (searchID(carID) >= 0) {
                System.out.println("The ID is duplicated! Try again.");
            }
        } while (searchID(carID) >= 0);
        //brand menu
        System.out.println("Select car's brand");
        System.out.println("------------------------------------");

        Brand b = (Brand) menu.ref_getChoice(brandlist.getBrandList());
        do {
            System.out.print("    Color:");
            color = sc.nextLine().toLowerCase();
            if (color == null) {
                System.out.println("Color cannot be blank!. Try again.");
            }
        } while (color == null);
        do {
            System.out.print("    FrameID: ");
            frameID = sc.nextLine().toUpperCase();
            if (!frameID.matches("^F\\d{4}$")) {
                System.out.println("Format: F and 4 digits!");
            }
            if (searchFrame(frameID) >= 0) {
                System.out.println("The frameID is duplicated!");
            }
        } while (!frameID.matches("^F\\d{4}$") || searchFrame(frameID) >= 0);
        do {
            System.out.print("    EngineID: ");
            engineID = sc.nextLine().toUpperCase();
            if (!engineID.matches("^E\\d{4}$")) {
                System.out.println("Format: E and 4 digits!");
            }
            if (searchEngine(engineID) >= 0) {
                System.out.println("The egineID is duplicated!");
            }
        } while (!engineID.matches("^E\\d{4}$") || searchEngine(engineID) >= 0);
        this.add(new Car(carID, b, color, frameID, engineID));
        System.out.println("Car added sucessfully");
    }

    public void printBasedBrandName() {
        int count = 0;
        String aPartOfBrandName;
        System.out.print("Enter a part of Brand name: ");
        aPartOfBrandName = sc.nextLine();
        for (int i = 0; i < this.size(); i++) {
            Car c = this.get(i);
            if (c.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        String removedID;
        System.out.print("Enter removed CarID: ");
        removedID = sc.nextLine().toUpperCase();
        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            remove(pos);
        }
        return true;
    }

    public boolean updateCar() {
        String updatedID, frameID, color, engineID;
        System.out.print("Enter CarID you want to update: ");
        updatedID = sc.nextLine().toUpperCase();
        int pos = searchID(updatedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            //create a menu
            Brand b = (Brand) menu.ref_getChoice(brandlist.getBrandList());
            do {
                System.out.print("    Color:");
                color = sc.nextLine().toLowerCase();
                if (color == null) {
                    System.out.println("Color cannot be blank!. Try again.");
                }
            } while (color == null);
            do {
                System.out.print("    FrameID: ");
                frameID = sc.nextLine().toUpperCase();
                if (!frameID.matches("^F\\d{4}$")) {
                    System.out.println("Format: F and 4 digits!");
                }
                if (searchFrame(frameID) >= 0) {
                    System.out.println("The frameID is duplicated!");
                }
            } while (!frameID.matches("^F\\d{4}$") || searchFrame(frameID) >= 0);
            do {
                System.out.print("    EngineID: ");
                engineID = sc.nextLine().toUpperCase();
                if (!engineID.matches("^E\\d{4}$")) {
                    System.out.println("Format: E and 4 digits!");
                }
                if (searchEngine(engineID) >= 0) {
                    System.out.println("The egineID is duplicated!");
                }
            } while (!engineID.matches("^E\\d{4}$") || searchEngine(engineID) >= 0);
            get(pos).setBrand(b);
            get(pos).setColor(color);
            get(pos).setFrameID(frameID);
            get(pos).setEngineID(engineID);
        }
        System.out.println("Car updated sucessfully");
        return true;
    }

    public void listCar() {
        Collections.sort(this);
        for (int i = 0; i < this.size(); i++) {
            Car c = this.get(i);
            System.out.println(c.screenString());
        }
    }

    public void loadFromFile(String carstxt) {
        System.out.println("oke");
    }

    public void listCars() {
        System.out.println("oke");
    }
}
