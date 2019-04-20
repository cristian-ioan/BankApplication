package file;

import utils.FileUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class FileWriter {

    private final static Logger LOG = Logger.getLogger(Logger.class.getName());

    /**
     * Writes a line into a txt file.
     *
     * @param line the line that is written into the file
     * @throws IOException on output error
     * @param LOG logger
     */
    public void writeStringToFile(String line) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new java.io.FileWriter(FileUtils.FILE_PATH_ACCOUNTS, true))) {
            bufferedWriter.write(line);
        } catch (FileNotFoundException e){
            LOG.warning("File not found: " + e.getMessage());
        }
    }

}