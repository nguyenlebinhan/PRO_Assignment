package CarPrj.entities;

import java.util.*;

/**
 * Utility class to display menu options and get user responses. - int_getChoice(): gets user input as an integer. - ref_getChoice(): gets the object chosen by user from a list. - Used for both brand and car selection menus.
 *
 * @author Le Minh Quan
 * @since 17-06-2025
 * @version 1
 */
public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Displays a list of options and prompts the user to select one. Ensures that the input is a valid integer within the correct range.
     *
     * @param options the list of options to display
     * @return the user's selected option index (1-based)
     */
    public int int_getChoice(ArrayList<?> options) {
        if (options == null || options.isEmpty()) {
            System.out.println("[Options list cannot be null or empty]");
            return -1;
        }

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        int response;
        while (true) {
            System.out.print("Please select an option 1-" + options.size() + ": ");
            String input = sc.nextLine().trim();

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

    /**
     * Displays a list of objects and returns the selected object based on user input.
     *
     * @param <T> the type of elements in the list
     * @param options the list of objects to choose from
     * @return the object selected by the user
     */
    public <T> T ref_getChoice(ArrayList<T> options) {
        int response;
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > options.size());
        return options.get(response - 1);
    }

}
