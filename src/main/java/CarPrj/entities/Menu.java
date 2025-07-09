package CarPrj.entities;

import java.util.*;

/**
 * - int_getChoice(): gets user input as an integer
 * - ref_getChoice(): gets the object chosen by user from a list
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */
public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    // Method to display options and prompts the user to select one
    public <E> int int_getChoice(ArrayList<E> options) {
        // Check if list if empty/ null
        if (options == null || options.isEmpty()) {
            System.out.println("[Options list cannot be null or empty]");
            return -1;
        }

        // Display options
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        // Prompt user input
        int response;
        while (true) {
            System.out.print("- Please select an option 1.." + options.size() + ": ");
            String input = sc.nextLine().trim();

            // Validate input
            if (input.isEmpty()) {
                System.out.println("[Input cannot be empty. Please try again]");
                continue;
            }
            try {
                response = Integer.parseInt(input);
                if (response >= 1 && response <= options.size()) {
                    return response;
                } else {
                    System.out.println("[Invalid response. Please enter a number between 1 and " + options.size() + "]");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error: " + e.getMessage().toLowerCase() + "]");
            }
        }
    }

    // Displays a list of objects and returns the selected object based on user input
    public <T> T ref_getChoice(ArrayList<T> options) {
        int response;
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > options.size());
        return options.get(response - 1);
    }

}
