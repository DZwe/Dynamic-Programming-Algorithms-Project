import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOpener {

    public static List grabFile() {

        // Accept file path and work some magic.

        // Prompt User for Input
        System.out.println("Please enter the full path for test File.\n");

        // String for filePath
        String filePath;

        // User Input
        Scanner scannerInstance = new Scanner(System.in);
        if (scannerInstance == null) {
            System.err.println("No scanner available, Error.");
            System.exit(1);
        }
        filePath = scannerInstance.nextLine();

        // Error check on filePath string.
        if (filePath == null) {
            System.err.println("No path specified. Error");
            System.exit(1);
        }

        // Get File Path from string and attempt to read lines.
        Path file = Paths.get(filePath);
        List<String> myFileStrings = new ArrayList<>();
        try {
            myFileStrings = Files.readAllLines(file);
        } catch (IOException ie) {
            System.err.println("Unable to read file " + ie);
        }

        return myFileStrings;
    }
}
