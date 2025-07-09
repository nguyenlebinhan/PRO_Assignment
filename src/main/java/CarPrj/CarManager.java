package CarPrj;

import CarPrj.entities.*;
import java.util.*;

/**
 * Main controller class to manage the car showroom.
 * - loads brand and car data from files (brands.txt and cars.txt).
 * - displays a menu with 12 options.
 * - Handles user interactions and delegates tasks to BrandList and CarList.
 * Update: Logging controls
 *
 * @author Le Minh Quan
 * @since 08-07-2025
 * @version 3
 */
public class CarManager {

    private static final String BRANDS_PATH = "src/main/java/CarPrj/data/brands.txt";
    private static final String CARS_PATH = "src/main/java/CarPrj/data/cars.txt";
    private static final BrandList brandList = new BrandList();
    private static final CarList carList = new CarList(brandList);
    private static final Menu menu = new Menu();
    private static final Scanner scanner = new Scanner(System.in);

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
        ops.add("Add a new brand");
        ops.add("Search a brand by its ID");
        ops.add("Update a brand");
        ops.add("Save brands to file");
        ops.add("List all cars");
        ops.add("List cars by a part of brand name");
        ops.add("Add a new car");
        ops.add("Remove a car by its ID");
        ops.add("Update a car by its ID");
        ops.add("Save cars to file");
        ops.add("Exit");

        // Process choices
        System.out.println("[MINH TRANG BMW SHOWROOM]");
        int choice;
        do {
            choice = menu.int_getChoice(ops);
            String action = (choice > 0 && choice <= ops.size()) ? ops.get(choice - 1) : "Invalid Option";
            System.out.println("============================================================================================================");
            switch (choice) {
                case 1:
                    // List brands
                    brandList.listBrands();
                    Logger.log(action, "brand_list_displayed");
                    break;
                case 2:
                    // Add brand
                    Brand newBrand = brandList.addBrand();
                    if (newBrand != null) {
                        Logger.log(action, "brand_added: <" + newBrand.toString() + ">");
                    } else {
                        Logger.log(action, "failed: user canceled or invalid input");
                    }
                    break;
                case 3:
                    // Search brand
                    System.out.print("- Enter ID to search: ");
                    String searchID = scanner.nextLine().trim();
                    if (searchID.isEmpty()) {
                        System.out.println("[ID cannot be blank]");
                        break;
                    }

                    int pos = brandList.searchID(searchID);
                    if (pos < 0) {
                        System.out.println("[Not found]");
                        Logger.log(action, "brand_not_found: " + searchID);
                    } else {
                        brandList.get(pos).brandInfo();
                        Logger.log(action, "brand_found: <" + brandList.get(pos).toString() + ">");
                    }
                    break;
                case 4:
                    // Update brand
                    Brand updatedBrand = brandList.updateBrand();
                    if (updatedBrand != null) {
                        System.out.println("[Brand updated]");
                        Logger.log(action, "brand_updated: <" + updatedBrand.toString() + ">");
                    } else {
                        Logger.log(action, "failed: brand not found or update cancelled");
                    }
                    break;
                case 5:
                    // Save to file
                    if (brandList.saveToFile(BRANDS_PATH)) {
                        System.out.println("[Saved to file]");
                        Logger.log(action, "saved_to: " + BRANDS_PATH);
                    } else {
                        Logger.log(action, "failed_to_save: " + BRANDS_PATH);
                    }
                    break;
                case 6:
                    // List cars
                    carList.listCars();
                    Logger.log(action, "car_list_displayed");
                    break;
                case 7:
                    // Print cars based on brand name
                    carList.printBasedBrandName();
                    Logger.log(action, "car_list_filtered_and_displayed");
                    break;
                case 8:
                    // Add car
                    Car newCar = carList.addCar();
                    if (newCar != null) {
                        Logger.log(action, "car_added: <" + newCar.toString() + ">");
                    } else {
                        Logger.log(action, "failed: user cancelled or invalid input");
                    }
                    break;
                case 9:
                    // Remove car
                    Car removedCar = carList.removeCar();
                    if (removedCar != null) {
                        System.out.println("[Car removed]");
                        Logger.log(action, "car_removed: <" + removedCar.toString() + ">");
                    } else {
                        Logger.log(action, "failed: car not found or action cancelled");
                    }
                    break;
                case 10:
                    // Update car
                    Car updatedCar = carList.updateCar();
                    if (updatedCar != null) {
                        System.out.println("[Car updated]");
                        Logger.log(action, "car_updated: <" + updatedCar.toString() + ">");
                    } else {
                        Logger.log(action, "failed: car not found or update cancelled");
                    }
                    break;
                case 11:
                    // Save to file
                    if (carList.saveToFile(CARS_PATH)) {
                        System.out.println("[Saved to file]");
                        Logger.log(action, "saved_to: " + CARS_PATH);
                    } else {
                        Logger.log(action, "failed_to_save: " + CARS_PATH);
                    }
                    break;
                case 12:
                    // Exit
                    do {
                        System.out.print("Do you want to save all changes? - [y/n]: ");
                        String c = scanner.nextLine().trim().toLowerCase();
                        switch (c) {
                            case "y":
                                boolean brandsSaved = brandList.saveToFile(BRANDS_PATH);
                                boolean carsSaved = carList.saveToFile(CARS_PATH);
                                if (brandsSaved && carsSaved) {
                                    System.out.println("[Saved to file]");
                                    Logger.log(action, "saved_all_changes");
                                } else {
                                    Logger.log(action, "failed_to_save_changes");
                                }
                                return;
                            case "n":
                                System.out.println("[Exiting program...]");
                                Logger.log(action, "exitted_without_saving");
                                return;
                            default:
                                System.out.println("[Invalid choice]");
                        }
                    } while (true);
                default:
                    System.out.println("[Invalid option. Try again]");
                    Logger.log(action, "invalid_menu_choice: " + choice);
            }
            System.out.println("============================================================================================================");
        } while (choice != 12);
    }
}
