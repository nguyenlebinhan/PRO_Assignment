package CarPrj.lists;







import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import CarPrj.entities.Brand;
import java.util.List;
import java.util.Scanner;

public class BrandList {
    private final List<Brand> brandList;
    public BrandList (){
        this.brandList=new ArrayList<>();
    }
    public Boolean loadFromFile(String filename){
        File file=new File(filename);
        if(!file.exists()) {
            return false;
        }else{
            try(BufferedReader br=new BufferedReader(new FileReader(filename))){
                String line;
                while((line=br.readLine())!=null){
                    String[] mainParts=line.split(":"); //Tách phần tử cuối cùng
                    if(mainParts.length ==2){
                        String[]elements=mainParts[0].split(","); // tách phần tử còn lại chứa dấu phẩy ngăn cách
                        Brand  branch=new Brand(elements[0].trim(),elements[1].trim(),elements[2].trim(),Double.parseDouble(mainParts[1].trim()));
                        brandList.add(branch);
                    }
                }
            }catch (IOException  e){
                System.out.println("Lỗi khi đọc file: "+e.getMessage());
                return false;
            }
            return true;
        }
    }
    public boolean saveToFile(String filename){
        File file =new File(filename);
        if(!file.exists()){
            return false;
        }
        try (BufferedWriter writer= new BufferedWriter(new FileWriter(filename))){
            for(Brand brand: brandList){
                writer.write(brand.toString());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Lỗi khi gi file: "+e.getMessage());
            return false;
        }
        return true;
    }
    
    public int searchID(String BrandID) {
        for(int i=0;i<brandList.size();i++){
            if(brandList.get(i).getBrandID().equals(BrandID)){
                return i;
            }
        }
        return -1;
    }
    
public Brand getUserChoice(){
    Menu mnu= new Menu();
    return (Brand)mnu.ref_getChoice(BrandList);
}


public void addBrand(){
    Scanner sc= new Scanner(System.in);
    System.out.print("Nhập Brand ID: ");
    String brandID =sc.nextLine().trim();
    if (searchID(brandID) !=-1){
        System.out.println("Brand ID đã tồn tại!");
        return;
    }
    System.out.print("Nhập Brand Name: ");
    String brandName = sc.nextLine().trim();
    if (brandName.isEmpty()){
        System.out.println("Brand Name còn trống!");
        return;
    }
    System.out.print("Nhập Sound Brand: ");
    String soundBrand= sc.nextLine().trim();
    if (soundBrand.isEmpty()){
        System.out.println("Sound Brand còn trống!");
        return;
    }
    System.out.print("Price:");
    double Price;
    try{
        Price = Double.parseDouble(sc.nextLine().trim());
        if (Price <= 0){
            System.out.println("Price phải là số dương!");
            return;
        }
    }
    catch (NumberFormatException e){
        System.out.println("Price không hợp lệ!");
        return;
    }
    Brand br= new Brand(brandID, brandName, soundBrand,Price);
    BrandList.add(br);
    System.out.println("Thêm Brand thành công!");
    }

public void updateBrand(){
    Scanner sc= new Scanner(System.in);
    System.out.print("Nhập Brand ID cần cập nhật: ");
    String brandID= sc.nextLine().trim();
    int pos= searchID(brandID);
    if (pos < 0){
        System.out.println("Not found!");
        return;
    }
    System.out.print("Nhập Brand Name mới: ");
    String brandName = sc.nextLine().trim();
    if (brandName.isEmpty()){
        System.out.println("Brand Name còn trống!");
        return;
    }
    System.out.print("Nhập Price mới: ");
    double Price;
    try{
        Price = Double.parseDouble(sc.nextLine().trim());
        if (Price <= 0){
            System.out.println("Price phải là số dương!");
            return;
        }
    } catch (NumberFormatException e){
        System.out.println("Price không hợp lệ!");
        return;
    }

    Brand br= BrandList.get(pos);
    br.setBrandName(brandName);
    br.setSoundBrand(soundBrand);
    br.setPrice(Price);
    System.out.println("Cập nhật thành công!");
}

    
    public void listBranch(){
        brandList.forEach(System.out::println);
    }

}

