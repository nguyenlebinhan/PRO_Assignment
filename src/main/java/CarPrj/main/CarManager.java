 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package CarPrj.main;
import java.util.ArrayList;
import java.util.List;
import CarPrj.lists.BrandList;
import CarPrj.lists.CarList;

/**
 * Main controller class to manage the car showroom.
 * - Loads brand and car data from files (brands.txt and cars.txt).
 * - Displays a menu with 11 functions, such as listing, adding, updating, removing, saving cars/brands.
 * - Handles user interactions and delegates tasks to BrandList and CarList.
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */


public class CarManager {
    
    public static void main(String[] args) {
        // 1. Create a list of menu options
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all Brands");
        ops.add("Add new Brand");
        ops.add("Search a Brand by ID");
        ops.add("Update a Brand");
        ops.add("Save Brands to file");
        ops.add("List all Cars");
        ops.add("List Cars by Brand Name");
        ops.add("Add new Car");
        ops.add("Remove a Car by ID");
        ops.add("Update a Car by ID");
        ops.add("Save Cars to file");
        ops.add("Exit");
        
        // 2. Create an empty BrandList
        BrandList brandList = new BrandList();

        // 3. Create a CarList with brandList
        CarList carList = new CarList(brandList);
        
        // 4. Create a menu
        Menu menu = new Menu();

        // 5. Load data from file and check for exceptions
        try {
            brandList.loadFromFile("brands.txt");
            carList.loadFromFile("cars.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
     
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("Enter brand ID to search: ");
                    String searchID = sc.nextLine();
                    int pos = brandList.searchID(searchID);
                    if (pos < 0) System.out.println("Not found!");
                    else System.out.println(brandList.get(pos));
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("cars.txt");
                    break;
                case 12:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 12);
        sc.close();
    }
}
