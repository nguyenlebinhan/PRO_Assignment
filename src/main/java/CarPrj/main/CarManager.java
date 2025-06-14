/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package CarPrj.main;

/**
 *
 * @author ADMIN
 */
import CarPrj.lists.BrandList;

public class CarManager {
    
    public static final String FILE_PATH = "C:\\Users\\ADMIN\\Desktop\\PRO_Assignment\\src\\main\\java\\CarPrj\\data\\brands.txt";

    public static void main(String[] args) {
        System.out.println("Hello World!");
        BrandList brandList=new BrandList();
        
        brandList.loadFromFile(FILE_PATH);
        brandList.listBranch();
        System.out.println(brandList.searchID("B5-X20"));
    }
}
