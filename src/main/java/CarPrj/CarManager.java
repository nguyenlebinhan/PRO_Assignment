package CarPrj;

import CarPrj.entities.Menu;
import CarPrj.entities.*;
import java.io.IOException;
import java.util.*;

/**
 * Main controller class to manage the car showroom. - Loads brand and car data from files (brands.txt and cars.txt). - Displays a menu with 12 options. - Handles user interactions and delegates tasks to BrandList and CarList.
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */
public class CarManager {

    private static final String BRANDS_PATH = "src/main/java/CarPrj/data/brands.txt";
    private static final String CARS_PATH = "src/main/java/CarPrj/data/cars.txt";
    private static final BrandList brandList = new BrandList();
    private static final CarList carList = new CarList(brandList);
    private static final Menu menu = new Menu();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Load files
        try {
            boolean brandLoaded = brandList.loadFromFile(BRANDS_PATH);
            boolean carLoaded = carList.loadFromFile(CARS_PATH);

            if (!brandLoaded || !carLoaded) {
                System.out.println("[Failed to load files. Exiting...]");
                return;
            }
        } catch (Exception e) {
            System.out.println("[Error: " + e.getMessage() + "]");
            return;
        }

        // Menu options
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add new brand");
        ops.add("Search a brand by its ID");
        ops.add("Update a brand");
        ops.add("Save brands to file");
        ops.add("List all cars");
        ops.add("List cars by a part of brand name");
        ops.add("Add new car");
        ops.add("Remove a car by its ID");
        ops.add("Update a car by its ID");
        ops.add("Save cars to file");
        ops.add("Exit");

        // Switch choices
        int choice;
        do {
            choice = menu.int_getChoice(ops);
            System.out.println("============================================================================================================");
            switch (choice) {
                case 1:
                    brandList.printTable();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    String searchID;
                    do {
                        System.out.print("- Enter ID to search: ");
                        searchID = sc.nextLine().trim();
                        if (searchID.isEmpty()) {
                            System.out.println("[ID cannot be blank]");
                        } else {
                            break;
                        }
                    } while (true);
                    int pos = brandList.searchID(searchID);
                    if (pos < 0) {
                        System.out.println("Not found!");
                    } else {
                        System.out.println(brandList.get(pos));
                    }
                    break;
                case 4:
                    if (brandList.updateBrand()) {
                        System.out.println("[Brand updated]");
                    }
                    break;
                case 5:
                    if (brandList.saveToFile(BRANDS_PATH)) {
                        System.out.println("[Saved to file]");
                    }
                    break;
                case 6:
                    carList.printTable();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    if (carList.removeCar()) {
                        System.out.println("[Car removed]");
                    }
                    break;
                case 10:
                    if (carList.updateCar()) {
                        System.out.println("[Car updated]");
                    }
                    break;
                case 11:
                    if (carList.saveToFile(CARS_PATH)) {
                        System.out.println("[Saved to file]");
                    }
                    break;
                case 12:
                    do {
                        System.out.print("Do you want to save all changes? - [y/n]: ");
                        String c = sc.nextLine().trim().toLowerCase();
                        switch (c) {
                            case "y":
                                if (carList.saveToFile(CARS_PATH) && brandList.saveToFile(BRANDS_PATH)) {
                                    System.out.println("[Saved to file]");
                                }
                                return;
                            case "n":
                                System.out.println("[Exiting the program...]");
                                return;
                            default:
                                System.out.println("[Invalid choice]");
                        }
                    } while (true);
                default:
                    System.out.println("[Invalid option. Try again]");
            }
            System.out.println("============================================================================================================");
        } while (choice != 12);
    }
}
