package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileReader {

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public List<String> readFromFile(String filePath) throws IOException {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader( filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            LOG.warning("File not found: " + e.getMessage());
        }
        return lines;
    }

}
