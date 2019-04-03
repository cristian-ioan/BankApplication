package file;

import utils.FileUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class FileWriter {

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    public void writeStringToFile(String line) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new java.io.FileWriter(FileUtils.FILE_PATH_ACCOUNTS, true))) {
            bufferedWriter.write(line);
        } catch (FileNotFoundException e){
            LOG.warning("File not found: " + e.getMessage());
        }
    }

}