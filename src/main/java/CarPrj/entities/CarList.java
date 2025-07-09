package CarPrj.entities;

import java.io.*;
import java.util.*;

public class CarList extends ArrayList<Car> {

    // Fields
    private final Menu menu = new Menu();
    private final Scanner sc = new Scanner(System.in);
    private final BrandList brandList;

    /**
     *
     * @author VinhNHT
     * @since 29-6-2025
     * @version 3
     */
    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }

    public boolean loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("[File does not exist]");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            this.clear();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length != 5) {
                    continue;
                }

                String carID = parts[0].trim();
                String brandID = parts[1].trim();
                String color = parts[2].trim();
                String frameID = parts[3].trim();
                String engineID = parts[4].trim();

                int pos = brandList.searchID(brandID);
                if (pos == -1) {
                    continue;
                }
                Brand brand = brandList.get(pos);
                this.add(new Car(carID, brand, color, frameID, engineID));
            }
            return true;
        } catch (IOException e) {
            System.out.println("[Error: " + e.getMessage() + "]");
            return false;
        }
    }

    private String inputColor(String msg) {
        String color;
        do {
            System.out.print(msg);
            color = sc.nextLine().trim().toLowerCase();
            if (color.isEmpty()) {
                System.out.println("[Color cannot be blank]");
            }
        } while (color.isEmpty());
        return color;
    }

    private String inputFrameID(String msg) {
        String frameID;
        do {
            System.out.print(msg);
            frameID = sc.nextLine().trim().toUpperCase();
            if (!frameID.matches("^F\\d{5}$")) {
                System.out.println("[Format error: must be 'F' followed by 5 digits]");
            } else if (searchFrame(frameID) >= 0) {
                System.out.println("[The frame ID is duplicated]");
            } else {
                return frameID;
            }
        } while (true);
    }

    private String inputEngineID(String msg) {
        String engineID;
        do {
            System.out.print(msg);
            engineID = sc.nextLine().trim().toUpperCase();
            if (!engineID.matches("^E\\d{5}$")) {
                System.out.println("[Format error: must be 'E' followed by 5 digits]");
            } else if (searchEngine(engineID) >= 0) {
                System.out.println("[The engine ID is duplicated]");
            } else {
                return engineID;
            }
        } while (true);
    }

    /**
     *
     * @author MinhPN
     * @since 2025-07-08
     * @version 2
     */
    // Method to save data to file
    public boolean saveToFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("[List is empty]");
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Car car : this) {
                writer.println(car.toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("[Error: " + e.getMessage() + "]");
            return false;
        }
    }

    // Method to search for car ID
    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equalsIgnoreCase(carID)) {
                return i;
            }
        }
        return -1;
    }

    // Method to search for frame ID
    public int searchFrame(String frameID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equalsIgnoreCase(frameID)) {
                return i;
            }
        }
        return -1;
    }

    // Method to search for engine ID
    public int searchEngine(String engineID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equalsIgnoreCase(engineID)) {
                return i;
            }
        }
        return -1;
    }

    // Method to display table header
    private void printHeader() {
        System.out.printf("%-35s %-15s %-15s %-15s %-15s\n", "Brand name", "Car ID", "Color", "Frame ID", "Engine ID");
        System.out.println("============================================================================================================");
    }

    // Method to display car info
    private void printRow(Car car) {
        System.out.printf("%-35s %-15s %-15s %-15s %-15s\n",
                car.getBrand().getBrandName(),
                car.getCarID(),
                car.getColor(),
                car.getFrameID(),
                car.getEngineID()
        );
    }

    /**
     * Update: return type Car for logging
     *
     * @author QuanVH
     * @since 08-07-2025
     * @version 2
     */
    // Add new car
    public Car addCar() {
        String carID, color, frameID, engineID;
        System.out.println("[ADD NEW CAR]");

        // Car ID
        System.out.print("- Enter car ID: ");
        carID = sc.nextLine().trim().toUpperCase();
        if (carID.isEmpty()) {
            System.out.println("[Car ID cannot be blank]");
            return null;
        } else if (searchID(carID) >= 0) {
            System.out.println("[Car ID is duplicated]");
            return null;
        }

        // Brand
        System.out.println("- Select car's brand: ");
        Brand brand = menu.ref_getChoice(brandList);

        // Get user input
        color = inputColor("- Enter color: ");
        frameID = inputFrameID("- Enter frame ID (F12345): ");
        engineID = inputEngineID("- Enter engine ID (E12345): ");

        Car newCar = new Car(carID, brand, color, frameID, engineID);
        this.add(newCar);
        System.out.println("[Car added successfully]");
        return newCar;
    }

    // Update existing car
    public Car updateCar() {
        String updatedID, frameID, color, engineID;
        System.out.print("- Enter ID of car to update: ");
        updatedID = sc.nextLine().trim();
        if (updatedID.isEmpty()) {
            System.out.println("[ID cannot be blank]");
            return null;
        }

        int pos = searchID(updatedID);
        if (pos < 0) {
            System.out.println("[ID not found]");
            return null;
        }

        System.out.println("Available brands: ");
        Brand b = menu.ref_getChoice(brandList);

        // Get user input
        color = inputColor("- Enter new color: ");
        frameID = inputFrameID("- Enter new frame ID (F12345): ");
        engineID = inputEngineID("- Enter new engine ID (E12345): ");

        Car carToUpdate = this.get(pos);
        carToUpdate.setBrand(b);
        carToUpdate.setColor(color);
        carToUpdate.setFrameID(frameID);
        carToUpdate.setEngineID(engineID);
        return carToUpdate;
    }

    // Remove existing car
    public Car removeCar() {
        System.out.print("- Enter ID of car to remove: ");
        String carID = sc.nextLine().trim();
        if (carID.isEmpty()) {
            System.out.println("[ID cannot be blank]");
            return null;
        }

        int pos = searchID(carID);
        if (pos < 0) {
            System.out.println("[Car ID not found]");
            return null;
        }

        Car removedCar = this.get(pos);
        this.remove(pos);
        return removedCar;
    }

    // List cars in table
    public void listCars() {
        if (this.isEmpty()) {
            System.out.println("[No cars to display]");
            return;
        }

        Collections.sort(this);
        printHeader();
        for (Car car : this) {
            printRow(car);
        }
    }

    // Display car based on a part of brand name
    public void printBasedBrandName() {
        System.out.print("- Enter a part of brand name to search: ");
        String aPartOfBrandName = sc.nextLine().trim();
        if (aPartOfBrandName.isEmpty()) {
            System.out.println("[Input cannot be blank]");
            return;
        }

        int count = 0;
        Collections.sort(this);

        // Find if car with part of brand name exist
        boolean found = false;
        for (Car car : this) {
            if (car.getBrand().getBrandName().toLowerCase().contains(aPartOfBrandName.toLowerCase())) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("[No car is detected]");
            return;
        }

        // If found, print cars
        System.out.println("============================================================================================================");
        printHeader();
        for (Car car : this) {
            if (car.getBrand().getBrandName().toLowerCase().contains(aPartOfBrandName.toLowerCase())) {
                printRow(car);
            }
        }
    }

}
