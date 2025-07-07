package CarPrj.entities;

import java.io.*;
import java.util.*;

public class BrandList extends ArrayList<Brand> {

    // Hai
    public Boolean loadFromFile(String fileName) {
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

    public boolean saveToFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.print("[file does not exist]");
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

    public int searchID(String brandID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(brandID)) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        if (this.isEmpty()) {
            System.out.println("[no brands available]");
            return null;
        }

        Menu menu = new Menu();
        return menu.ref_getChoice(this); // uses toString() to display options
    }

    // An
    public void addBrand() {
        Scanner sc = new Scanner(System.in);
        String brandID, brandName, soundBrand;
        double price;

        System.out.println("[ADD NEW BRAND]");

        // Brand ID
        do {
            System.out.print("- Enter brand ID: ");
            brandID = sc.nextLine().trim();
            if (searchID(brandID) >= 0) {
                System.out.println("[brand ID already exists]");
                brandID = null;
            } else if (brandID.isEmpty()) {
                System.out.println("[brand ID cannot be empty]");
            }
        } while (brandID == null || brandID.isEmpty());

        // Brand name
        do {
            System.out.print("- Enter brand name: ");
            brandName = sc.nextLine().trim();
            if (brandName.isEmpty()) {
                System.out.println("[brand name cannot be empty]");
            }
        } while (brandName.isEmpty());

        // Sound Brand
        do {
            System.out.print("- Enter sound brand: ");
            soundBrand = sc.nextLine().trim();
            if (soundBrand.isEmpty()) {
                System.out.println("[sound brand cannot be empty]");
            }
        } while (soundBrand.isEmpty());

        // Price
        do {
            System.out.print("- Enter price: ");
            try {
                price = Double.parseDouble(sc.nextLine().trim());
                if (price <= 0) {
                    System.out.println("[price must be positive]");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[Error: " + e.getMessage() + "]");
            }
        } while (true);

        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("[Brand added successfully]");
    }

    public boolean updateBrand() {
        try {
            if (this.isEmpty()) {
                System.out.println("[no brands to update]");
                return false;
            }

            String id;
            Scanner sc = new Scanner(System.in);
            do {
                System.out.print("- Enter brand ID to update: ");
                id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("[ID cannot be blank]");
                } else {
                    break;
                }
            } while (true);

            int pos = searchID(id);
            if (pos < 0) {
                System.out.println("[Brand not found]");
                return false;
            }

            Brand old = this.get(pos);
            System.out.println("Updating: [" + old.toString() + "]");

            // Brand name
            String newName;
            do {
                System.out.print("- Enter brand name: ");
                newName = sc.nextLine().trim();
                if (newName.isEmpty()) {
                    System.out.println("[brand name cannot be empty]");
                }
            } while (newName.isEmpty());

            // Sound Brand
            String newSound;
            do {
                System.out.print("- Enter sound brand: ");
                newSound = sc.nextLine().trim();
                if (newSound.isEmpty()) {
                    System.out.println("[sound brand cannot be empty]");
                }
            } while (newSound.isEmpty());

            // Price
            double newPrice;
            while (true) {
                System.out.print("- Enter new price: ");
                String priceInput = sc.nextLine().trim();
                try {
                    newPrice = Double.parseDouble(priceInput);
                    if (newPrice > 0) {
                        break;
                    } else {
                        System.out.println("[price must be positive]");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[Error: " + e.getMessage() + "]");
                }
            }

            // Replace old object
            this.set(pos, new Brand(old.getBrandID(), newName, newSound, newPrice));
            return true;

        } catch (Exception e) {
            System.out.println("[Error: " + e.getMessage() + "]");
            return false;
        }
    }

    public void listBrand() {
        for (Brand brand : this) {
            System.out.println(brand);
        }
    }

    public void printTable() {
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
