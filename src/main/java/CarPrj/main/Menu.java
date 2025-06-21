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
     * Hiển thị danh sách các tùy chọn và yêu cầu người dùng nhập lựa chọn.
     *
     * options danh sách các tùy chọn
     * <E> kiểu dữ liệu của phần tử trong danh sách
     * @return chỉ số lựa chọn của người dùng (từ 1 đến options.size())
     */
    public int int_getChoice(ArrayList options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).toString());
        }

        System.out.print("Please choose an option 1.." + options.size() + ": ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt(); // Không kiểm tra ngoại lệ ở đây, nên cẩn thận nếu input không hợp lệ
    }

    /**
     * Gọi phương thức int_getChoice và trả về đối tượng được chọn trong danh sách.
     * @return đối tượng được người dùng chọn
     */
    public Object ref_getChoice(ArrayList options) {
        int choice;
        do {
            choice = int_getChoice(options);
        } while (choice < 1 || choice > options.size());
        return options.get(choice - 1); // Trả về phần tử đã chọn (theo chỉ số)
    }
}
