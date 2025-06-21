package CarPrj.entities;

/**
 * Represents a BMW brand.
 * - Contains brandID, brandName, soundBrand, and price.
 * - Used for associating with each car.
 * - Provides toString() method for display and file saving.
 * 
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */

public class Brand {
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    // Default constructor
    public Brand() {
        this("", "", "", 0);
    }

    // Parameterized constructor
    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

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

    // Returns brand info in the format: <ID, Name, Sound:Price>
    @Override
    public String toString() {
        return "<" + brandID + ", " + brandName + ", " + soundBrand + ":" + price + ">";
    }
}

