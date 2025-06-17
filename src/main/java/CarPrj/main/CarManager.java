 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package CarPrj.main;
import java.util.ArrayList;
import java.util.List;
import CarPrj.lists.BrandList;
import CarPrj.lists.CarList;

/**
 *
 * @author ADMIN
 */


public class CarManager {
    
     public static void main(String[] args) {
        // 1. Tạo danh sách các lựa chọn
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
        
         // 2. Tạo BrandList rỗng và load từ file
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");
        
         // 3. Tạo CarList với brandList và load từ file
        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");
        
         // 4. Tạo menu
        Menu menu = new Menu();
        int choice;
        
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
                    Scanner sc = new Scanner(System.in);
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
        } while (choice > 0 && choice < 12);
     }
}