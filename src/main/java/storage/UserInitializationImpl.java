package storage;

import file.FileReader;
import utils.FileUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class UserInitializationImpl implements UserInitilization {

    private static UserInitializationImpl instance;
    private FileReader fileReader = new FileReader();
    private  Map<String, String> userPasswordMap = new LinkedHashMap<>();

    private static final Logger LOG = Logger.getLogger(Logger.class.getName());

    private UserInitializationImpl(){
    }

    public static synchronized UserInitializationImpl getInstance() {
        if (instance == null) {
            instance = new UserInitializationImpl();
        }
        return instance;
    }

    public Map<String, String> getUserPasswordMap() {
        return userPasswordMap;
    }

    @Override
    public void initializeMapUserPassword() throws IOException {

        List<String> listStrings = fileReader.readFromFile(FileUtils.FILE_PATH_USERS);

        for (String line : listStrings){
            String[] parts = line.split(FileUtils.SPLIT_BY_SPACE);
            if (parts.length >= FileUtils.NUMBER_OF_COLUMNS_USERS) {
                String key = parts[0];
                String value = parts[1];
                userPasswordMap.put(key, value);
            } else {
                LOG.warning("... ignoring line: " + line);
            }
        }

    }

}