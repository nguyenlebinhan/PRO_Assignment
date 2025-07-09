package CarPrj;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to log user action
 *
 * @author MinhPN
 * @since 2025-07-08
 * @version 1
 */
public class Logger {

    private static final String LOG_PATH = "src/main/java/CarPrj/data/log.txt";

    public static void log(String action, String result) {
        String time = new SimpleDateFormat("[dd-MM-yyyy] [HH:mm:ss]").format(new Date());
        String log = String.format("%s [ACTION=\"%s\"] [RESULT=\"%s\"]", time, action, result);
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_PATH, true))) {
            writer.println(log);
        } catch (IOException e) {
            System.err.println("[Error: " + e.getMessage() + "]");
        }
    }
}
