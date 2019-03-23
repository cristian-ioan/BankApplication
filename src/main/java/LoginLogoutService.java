import constant.Constants;
import service.IOServices;
import user.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginLogoutService {

    private IOServices ioServices = new IOServices();
    private User user;
    private String userNameOfAuthenticatedUser;
    private Map<String, String> userPasswordMap = new LinkedHashMap<>();

    public LoginLogoutService() throws IOException {
        initializeMapUserPassword();
    }

    public void run() {
        if (user == null) {
            LoginUser();
        } else {
            LogoutUser();
        }
        run();
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

    private void LoginUser() {
        ioServices.showLoginConsole();
        int option = ioServices.readInteger();

        switch (option) {
            case 1:
                System.out.print("Enter username: ");
                String username = ioServices.readLine();
                System.out.print("Enter password: ");
                String password = ioServices.readLine();
                user = new User(username, password);
                userNameOfAuthenticatedUser = verifyLogin(user);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Not a valid option");
                LoginUser();
                break;
        }

        if (userNameOfAuthenticatedUser != null) {
            System.out.println("Welcome " + userNameOfAuthenticatedUser + " !");
            userNameOfAuthenticatedUser = null;
        } else {
            System.out.println("Wrong username/password");
            LoginUser();
        }
    }

    private void LogoutUser() {
        ioServices.showLogoutConsole();
        int option = ioServices.readInteger();

        switch (option) {
            case 1:
                logout();
                user = null;
                System.out.println("Successfully logout");
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Not a valid option");
                LogoutUser();
                break;
        }
    }

    public String verifyLogin(User user) {
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




