package file;

import constant.Constant;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteIntoAccountsFile {

    private static final Logger loggerWriteToFileAccounts = Logger.getLogger(Logger.class.getName());

    public void writeStringToFile(String line) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constant.FILE_PATH_ACCOUNTS,
                true))) {
            bufferedWriter.write(line);
        } catch (FileNotFoundException e){
            loggerWriteToFileAccounts.warning("File not found!");
        }
    }

}
