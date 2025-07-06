package CarPrj.entities;

import java.io.*;
import java.util.*;
import CarPrj.Menu;

public class CarList extends ArrayList<Car> {

    private final Menu menu = new Menu();
    private final Scanner sc = new Scanner(System.in);
    private final BrandList brandList;

    // Vinh
    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : this) {
            sb.append(car.toString()).append("\n");
        }
        return sb.toString();
    }

    public String screenString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : this) {
            sb.append(String.format("[%s]\n[%s, %s, %s, %s]\n",
                    car.getBrand().toString(),
                    car.getCarID(),
                    car.getColor(),
                    car.getFrameID(),
                    car.getEngineID()));
        }
        return sb.toString();
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

    // Minh
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

    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equalsIgnoreCase(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String frameID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equalsIgnoreCase(frameID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String engineID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equalsIgnoreCase(engineID)) {
                return i;
            }
        }
        return -1;
    }

    // Quan
    public void addCar() {
        String carID, color, frameID, engineID;
        System.out.println("[ADD NEW CAR]");

        // Car ID
        while (true) {
            System.out.print("- Enter car ID: ");
            carID = sc.nextLine().trim().toUpperCase();
            if (carID.isEmpty()) {
                System.out.println("[Car ID cannot be blank]");
            } else if (searchID(carID) >= 0) {
                System.out.println("[Car ID is duplicated]");
            } else {
                break;
            }
        }

        // Brand
        System.out.println("- Select car's brand:");
        Brand brand = menu.ref_getChoice(brandList);

        // Color
        while (true) {
            System.out.print("- Enter color: ");
            color = sc.nextLine().trim().toLowerCase();
            if (color.isEmpty()) {
                System.out.println("[Color cannot be blank]");
            } else {
                break;
            }
        }

        // Frame ID
        while (true) {
            System.out.print("- Enter frame ID: ");
            frameID = sc.nextLine().trim().toUpperCase();
            if (!frameID.matches("^F\\d{4}$")) {
                System.out.println("[Format error: must be 'F' followed by 4 digits]");
            } else if (searchFrame(frameID) >= 0) {
                System.out.println("[The frame ID is duplicated]");
            } else {
                break;
            }
        }

        // Engine ID
        while (true) {
            System.out.print("- Enter engine ID: ");
            engineID = sc.nextLine().trim().toUpperCase();
            if (!engineID.matches("^E\\d{4}$")) {
                System.out.println("[Format error: must be 'E' followed by 4 digits]");
            } else if (searchEngine(engineID) >= 0) {
                System.out.println("[The engine ID is duplicated]");
            } else {
                break;
            }
        }

        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("[Car added successfully]");
    }

    public boolean updateCar() {
        String updatedID, frameID, color, engineID;
        System.out.print("- Enter ID of car to update: ");
        updatedID = sc.nextLine().toUpperCase();
        int pos = searchID(updatedID);
        if (pos < 0) {
            System.out.println("[ID not found]");
            return false;
        }

        //create a menu
        System.out.println("Available brands: " + brandList.size());
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
        Brand b = (Brand) menu.ref_getChoice(brandList);

        // Color
        do {
            System.out.print("- Enter new color:");
            color = sc.nextLine().toLowerCase().trim();
            if (color.isEmpty()) {
                System.out.println("[Color cannot be blank!. Try again]");
            }
        } while (color.isEmpty());

        // FrameID
        do {
            System.out.print("- Enter new frame ID: ");
            frameID = sc.nextLine().toUpperCase().trim();
            if (!frameID.matches("^F\\d{4}$")) {
                System.out.println("[Format error: must be 'F' followed by 4 digits]");
            } else if (searchFrame(frameID) >= 0) {
                System.out.println("[The frame ID is duplicated]");
            } else {
                break;
            }
        } while (true);

        // EngineID
        do {
            System.out.print("- Enter new engine ID: ");
            engineID = sc.nextLine().toUpperCase().trim();
            if (!engineID.matches("^E\\d{4}$")) {
                System.out.println("[Format error: must be 'E' followed by 4 digits]");
            } else if (searchEngine(engineID) >= 0) {
                System.out.println("[The engine ID is duplicated]");
            } else {
                break;
            }
        } while (true);

        get(pos).setBrand(b);
        get(pos).setColor(color);
        get(pos).setFrameID(frameID);
        get(pos).setEngineID(engineID);
        return true;
    }

    public boolean removeCar() {
        System.out.print("- Enter ID of car to remove: ");
        String carID = sc.nextLine().trim().toUpperCase();

        int pos = searchID(carID);
        if (pos < 0) {
            System.out.println("[Car ID not found]");
            return false;
        }

        this.remove(pos);
        return true;
    }

    public void listCars() {
        if (this.isEmpty()) {
            System.out.println("[No cars to display]");
            return;
        }

        Collections.sort(this);
        for (Car car : this) {
            System.out.println(car.screenString());
        }
    }

    public void printBasedBrandName() {
        System.out.print("- Enter part of brand name to search: ");
        String aPartOfBrandName = sc.nextLine().trim();
        int count = 0;

        for (Car car : this) {
            if (car.getBrand().getBrandName().toLowerCase().contains(aPartOfBrandName.toLowerCase())) {
                System.out.println(car.screenString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("[No car is detected]");
        }
    }
}
