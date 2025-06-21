package CarPrj.main;
import java.util.*;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class to display menu options and get user choices.
 * - int_getChoice(): gets user input as an integer.
 * - ref_getChoice(): gets the object chosen by user from a list.
 * - Used for both brand and car selection menus.
 * 
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */

public class Menu {
    /**
     * Displays the list of options and asks the user to choose one.
     *
     * @param options the list of options
     * @param <E> the type of elements in the list
     * @return the index of the user's choice (from 1 to options.size())
     */
    public int int_getChoice(ArrayList<?> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).toString());
        }
        System.out.print("Please choose an option 1.." + options.size() + ": ");
        Scanner sc = new Scanner(System.in);

        // check for invalid input
        while (!sc.hasNextInt()) {
            System.out.print("Invalid option. Try again: ");
            sc.next(); 
        }
        
        return sc.nextInt();
    }

    /**
     * Calls int_getChoice and returns the selected object from the list.
     * 
     * @param options the list of options
     * @return the object selected by the user
     */
    public <T> T ref_getChoice(ArrayList options) {
        int choice;
        do {
            choice = int_getChoice(options);
        } while (choice < 1 || choice > options.size());
        return options.get(choice - 1); // Return the selected element (by index)
    }
}
