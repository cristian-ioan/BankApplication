import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginLogoutService {

    public static final String filePath = "D:/userpassword.txt";
    private Map<String, String> userPasswordMap = new LinkedHashMap<String, String>();

    public void initializeMapUserPassword() {

        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

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

//            System.out.println( "The map is: " );
//            for (String key : userPasswordMap.keySet()) {
//                System.out.println( key + " : " + userPasswordMap.get(key));
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String login(String username, String password) {

        for (String s : userPasswordMap.keySet()) {
            if (s.equals(username) && userPasswordMap.get(s).equals(password)) {
                return username;
            }
        }
        return null;
    }

    public void logout() {
        // do nothing
    }

}




