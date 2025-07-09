package CarPrj.entities;

import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand> {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Update: Return Brand for Logger class
     *
     * @author HaiQH
     * @since 08-07-2025
     * @version 3
     */
    // Add new brand
    public Brand addBrand() {
        String brandID, brandName, soundBrand;
        double price;

        System.out.println("[ADD NEW BRAND]");

        // Get brand ID
        System.out.print("- Enter brand ID: ");
        brandID = sc.nextLine().trim();
        if (brandID.isEmpty()) {
            System.out.println("[Brand ID cannot be empty]");
            return null;
        }
        if (searchID(brandID) >= 0) {
            System.out.println("[Brand ID already exists]");
            return null;
        }

        // Get user input
        brandName = inputBrandName("- Enter brand name: ");
        soundBrand = inputSoundBrand("- Enter sound brand: ");
        price = inputPrice("- Enter price: ");

        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
        this.add(newBrand);
        System.out.println("[Brand added successfully]");
        return newBrand;
    }

    // Update brand
    public Brand updateBrand() {
        String newName, newSound;
        double newPrice;

        if (this.isEmpty()) {
            System.out.println("[No brands to update]");
            return null;
        }
        System.out.println("[UPDATE BRAND]");

        // Get brand ID          
        System.out.print("- Enter brand ID to update: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("[ID cannot be blank]");
            return null;
        }

        int pos = searchID(id);
        if (pos < 0) {
            System.out.println("[Brand not found]");
            return null;
        }

        Brand old = this.get(pos);
        System.out.println("Updating: [" + old.toString() + "]");

        // Get user input
        newName = inputBrandName("- Enter new brand name: ");
        newSound = inputSoundBrand("- Enter new sound brand: ");
        newPrice = inputPrice("- Enter new price: ");

        Brand updatedBrand = new Brand(old.getBrandID(), newName, newSound, newPrice);

        this.set(pos, updatedBrand);
        return updatedBrand;
    }

    // Get input for brand name
    private String inputBrandName(String msg) {
        String name;
        do {
            System.out.print(msg);
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("[Brand name cannot be empty]");
            } else {
                return name;
            }
        } while (true);
    }

    // Get input for sound brand 
    private String inputSoundBrand(String msg) {
        String sound;
        do {
            System.out.print(msg);
            sound = sc.nextLine().trim();
            if (sound.isEmpty()) {
                System.out.println("[Sound brand cannot be empty]");
            } else {
                return sound;
            }
        } while (true);
    }

    // Get input for price
    private double inputPrice(String msg) {
        double price;
        do {
            System.out.print(msg);
            try {
                price = Double.parseDouble(sc.nextLine().trim());
                if (price <= 0) {
                    System.out.println("[Price must be positive]");
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Invalid price format]");
            }
        } while (true);
    }

    /**
     *
     * @author AnNLB
     * @since 2025-07-08
     * @version 2
     */
    // Load data from brands.txt
    public boolean loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("[file does not exist]");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            this.clear();
            while ((line = br.readLine()) != null) {
                String[] mainParts = line.split(":");
                if (mainParts.length == 2) {
                    String[] elements = mainParts[0].split(",");

                    String brandID = elements[0].trim();
                    String brandName = elements[1].trim();
                    String soundBrand = elements[2].trim();
                    double price = Double.parseDouble(mainParts[1].trim());

                    this.add(new Brand(brandID, brandName, soundBrand, price));
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("[Error: " + e.getMessage() + "]");
            return false;
        }
    }

    // Save data to brands.txt
    public boolean saveToFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.print("[File does not exist]");
            return false;
        }

        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {
            for (Brand brand : this) {
                br.write(brand.toString());
                br.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("[Error: " + e.getMessage() + "]");
            return false;
        }
    }

    // Search brand ID
    public int searchID(String brandID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equalsIgnoreCase(brandID)) {
                return i;
            }
        }
        return -1;
    }

    // List brands
    public void listBrands() {
        if (this.isEmpty()) {
            System.out.println("[No brands to display]");
            return;
        }

        System.out.printf("%-15s %-35s %-30s %-10s\n", "Brand ID", "Brand name", "Sound", "Price");
        System.out.println("============================================================================================================");
        for (Brand brand : this) {
            System.out.printf("%-15s %-35s %-30s %-10.2f\n",
                    brand.getBrandID(),
                    brand.getBrandName(),
                    brand.getSoundBrand(),
                    brand.getPrice()
            );
        }
    }

}
