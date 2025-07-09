package CarPrj.entities;

/**
 * Represents a car brand 
 * - each brand has an ID, name, sound system brand, and price. 
 * - getters, setters, and toString() for display and file output.
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */
public class Brand {

    // Fields
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    // Constructors
    public Brand() {
    }

    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    // Getters and Setters
    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
    @return <brandID, brandName, soundBrand:price>
     */
    @Override
    public String toString() {
        return brandID + ", " + brandName + ", " + soundBrand + ":" + price;
    }
    
    // Print brand info
    public void brandInfo() {
        System.out.println("Brand ID: " + brandID);
        System.out.println("Brand name: " + brandName);
        System.out.println("Sound brand: " + soundBrand);
        System.out.println("Price: " + price);
    }
}
