import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class FileHandler {

    public static boolean fileExists(String name) {
        File file = new File(name);
        try {
            Scanner fileParser = new Scanner(file);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static void writeNewFile(String name, String text) {
        File newFile = new File(name);
        try {
            newFile.createNewFile();
            FileWriter writer = new FileWriter(newFile);
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

