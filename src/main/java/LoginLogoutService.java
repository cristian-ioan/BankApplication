import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginLogoutService {

    private static final String filePath = "D:/userpassword.txt";
    private Map<String, String> userPasswordMap = new LinkedHashMap<String, String>();

    public void initializeMapUserPassword() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split( "\\s" );
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

    public String login(User user) {
        for (String s : userPasswordMap.keySet()) {
            if (s.equals(user.getUsername()) && userPasswordMap.get(s).equals(user.getPassword())) {
                return user.getUsername();
            }
        }
        return null;
    }

    public void logout() {
        // do nothing
    }

}




