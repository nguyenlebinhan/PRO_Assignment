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
    
    public int searchID(String branchID) {
        for(int i=0;i<brandList.size();i++){
            if(brandList.get(i).getBrandID().equals(branchID)){
                return i;
            }
        }
        return -1;
    }
    
    public Brand getUserChoice(){
        
    }
    
    
    public void addBranch(){
        

    }
    
    public void UpdateBrand(){
        
    }
    
    
    public void listBranch(){
        brandList.forEach(System.out::println);
    }

}

