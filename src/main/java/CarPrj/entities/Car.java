package CarPrj.entities;
import CarPrj.entities.Brand;

/**
 * Represents a car object in the showroom.
 * - Includes carID, brand (as Brand object), color, frameID, engineID.
 * - Implements Comparable to support sorting by brand name and car ID.
 * - Includes toString() and screenString() for file and console output.
 * - Getter methods like getCarID(), getFrameID(), getEngineID() support searching.
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */
public class Car implements Comparable<Car> {
    private String carID;
    private Brand brand;        // Brand is another class
    private String color;
    private String frameID;
    private String engineID;

    public Car() {} // Default constructor

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;         // carID: car identifier
        this.brand = brand;         // brand: Brand object (car manufacturer)
        this.color = color;         // color: car's color
        this.frameID = frameID;     // frameID: frame identifier
        this.engineID = engineID;   // engineID: engine identifier
    } // Parameterized constructor to initialize a Car object

    public String getCarID() { return carID; }
    public void setCarID(String carID) { this.carID = carID; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getFrameID() { return frameID; }
    public void setFrameID(String frameID) { this.frameID = frameID; }

    public String getEngineID() { return engineID; }
    public void setEngineID(String engineID) { this.engineID = engineID; }

    @Override
    public String toString() {
        return carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID;
    } // Returns a simple formatted string for display

    public String screenString() {
        return brand.toString() + ", " + carID + ", " + color + ", " + frameID + ", " + engineID;
    } // Returns a nicely formatted display string (used when printing list)

    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0) return d;
        return this.carID.compareTo(c.carID);   // If same brand, compare by carID
    } // Compare cars by brand name for sorting
}
