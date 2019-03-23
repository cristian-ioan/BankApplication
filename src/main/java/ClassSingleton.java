import constant.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSingleton {

    private static ClassSingleton instance;
    private Map<String, String> userPasswordMap = new LinkedHashMap<>();

    private ClassSingleton() {
    }

    public static synchronized ClassSingleton getInstance() {
        if(instance == null) {
            instance = new ClassSingleton();
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

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(Constants.SPLIT_BY_SPACE );
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    userPasswordMap.put(key, value);
                } else {
                    System.out.println( "... ignoring line: " + line );
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
