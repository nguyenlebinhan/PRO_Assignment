 package CarPrj.main;
import java.util.*;
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
    private static final Scanner sc = new Scanner(System.in);  // use a shared Scanner

    /**
     * Displays a list of options and prompts the user to select one.
     * Ensures that the input is a valid integer within the correct range.
     *
     * @param options the list of options
     * @return the user's choice as an integer (1-based index)
     */
    public int int_getChoice(ArrayList<?> options) {
        int choice = -1;

        while (true) {
            // Display menu options
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Please select an option (1.." + options.size() + "): ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.size()) {
                    break; // valid choice
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }

    /**
     * Calls int_getChoice and returns the selected object from the list.
     *
     * @param options the list of options
     * @return the object selected by the user
     */
    public <T> T ref_getChoice(ArrayList<T> options) {
        int choice = int_getChoice(options);
        return options.get(choice - 1); // Return the selected element
    }
}
