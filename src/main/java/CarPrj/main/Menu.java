package CarPrj.main;
import java.util.*;


public class Menu {
    public int int_getChoice(ArrayList options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).toString());
        }
        System.out.print("Please choose an option 1.." + options.size() + ": ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public Object ref_getChoice(ArrayList options) {
        int choice;
        do {
            choice = int_getChoice(options);
        } while (choice < 1 || choice > options.size());
        return options.get(choice - 1);
    }
}
