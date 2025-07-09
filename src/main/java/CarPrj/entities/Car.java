package CarPrj.entities;

/**
 * Represents a car object
 * - carID, brand (as Brand object), color, frameID, engineID
 * - implements Comparable for sorting by brandName and carID.
 * - toString() for file output.
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */
public class Car implements Comparable<Car> {

    // Fields
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    // Constructors
    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    // Getters and setters
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    /**
     * @return <carID, brandID, color, frameID, engineID>
     */
    @Override
    public String toString() {
        return carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID;
    }

    // Compare 
    @Override
    public int compareTo(Car car) {
        int d = this.brand.getBrandName().compareTo(car.brand.getBrandName());
        if (d != 0) {
            return d;
        }
        return this.carID.compareTo(car.carID);
    }
}
