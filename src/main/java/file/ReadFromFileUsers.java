package file;

import constant.Constant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ReadFromFileUsers {

    private static ReadFromFileUsers instance;
    private Map<String, String> userPasswordMap = new LinkedHashMap<>();
    private final static Logger loggerReadFromFileUsers = Logger.getLogger(Logger.class.getName());

    private ReadFromFileUsers() {
    }

    public static synchronized ReadFromFileUsers getInstance() {
        if(instance == null) {
            instance = new ReadFromFileUsers();
        }
        return instance;
    }

    public Map<String, String> getUserPasswordMap() {
        return userPasswordMap;
    }

    public void setUserPasswordMap(Map<String, String> userPasswordMap) {
        this.userPasswordMap = userPasswordMap;
    }

    public void initializeMapUserPassword() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(Constant.FILE_PATH_USERS ))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split( Constant.SPLIT_BY_SPACE );
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    userPasswordMap.put(key, value);
                } else {
                    loggerReadFromFileUsers.warning("... ignoring line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            loggerReadFromFileUsers.warning("File not found!");
        }
    }
}
