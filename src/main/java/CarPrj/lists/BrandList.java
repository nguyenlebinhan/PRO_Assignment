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

/**
 * 1 function to save data to file 'brands.txt'.
 * NOTE: create LoadFromFile(), SaveToFile(), SearchId(),ListBrand() in BrandList.java
 * @author AnNLB
 * @since 2025-06-13
 * @version 1
 */

public class BrandList {
    private final List<Brand> brandList;
    public BrandList (){
        this.brandList=new ArrayList<>();
    }
    public Boolean loadFromFile(String filename){
        File file=new File(filename);
        //check whether the file exists
        if(!file.exists()) {
            return false;
        }else{

            //using BufferedReader to read the file line by line
            //using try-catch to handle exceptions
            try(BufferedReader br=new BufferedReader(new FileReader(filename))){
                String line;
                while((line=br.readLine())!=null){
                    String[] mainParts=line.split(":");//split the line into two parts using ':' as the delimiter
                    if(mainParts.length ==2){
                        String[]elements=mainParts[0].split(","); //split the first part into three elements using ',' as the delimiter
                        //create a new Brand object and add it to the brandList
                        Brand  brand=new Brand(elements[0].trim(),elements[1].trim(),elements[2].trim(),Double.parseDouble(mainParts[1].trim()));
                        brandList.add(brand);
                    }
                }
            }catch (IOException  e){
                System.out.println("Lỗi khi đọc file: "+e.getMessage());
                return false;
            }
            return true;
        }
    }

    // Function to save the brand list to a file
    public boolean saveToFile(String filename){
        File file =new File(filename);

        // Check if the file exists, if not, return false
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
    

    // Function to search for a brand by its ID
    // Returns the index of the brand if found, otherwise returns -1
    public int searchID(String brandID) {
        for(int i=0;i<brandList.size();i++){
            if(brandList.get(i).getBrandID().equals(brandID)){
                return i;
            }
        }
        return -1;
    }
    
    public Brand getUserChoice(){
        
    }
    
    
    public void addBrand(){
        

    }
    
    public void UpdateBrand(){
        
    }
    
    

    // Function to list all brands
    // Prints each brand's details to the console
    public void listBrand(){
        brandList.forEach(System.out::println);
    }

}

