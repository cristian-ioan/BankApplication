package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Creates a list of strings from a txt file.
 *
 * @param LOG logger
 *
 * @author Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
public class FileReader {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Reads from a text file and creates a list of strings corresponding to the file that was read.
     *
     * @param lines list of strings
     * @throws IOException on input error
     *
     * @return the list of strings
     */
    public List<String> readFromFile(String filePath) throws IOException {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
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