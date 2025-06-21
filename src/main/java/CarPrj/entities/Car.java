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
    private Brand brand;        //// Brand là một lớp khác
    private String color;
    private String frameID;
    private String engineID;

    public Car() {}//Constructor mặc định.

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;         //carID mã xe
        this.brand = brand;         //brand đối tượng brand (hãng xe)
        this.color = color;         //color màu xe
        this.frameID = frameID;     //frameID mã khung
        this.engineID = engineID;   //engineID mã động cơ
    } //Constructor có tham số để khởi tạo đối tượng Car.

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
    } //Trả về chuỗi định dạng đơn giản để hiển thị

    public String screenString() {
        return brand.toString() + ", " + carID + ", " + color + ", " + frameID + ", " + engineID;
    } //Trả về thông tin hiển thị đẹp mắt (dùng khi in danh sách)

    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0) return d;
        return this.carID.compareTo(c.carID);   // nếu cùng hãng, so sánh carID
    } //So sánh xe theo Brand name (tên hãng) để sắp xếp
}