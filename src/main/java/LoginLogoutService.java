import service.IOServices;
import user.User;

import java.io.IOException;
import java.util.Optional;

public class LoginLogoutService {

    private IOServices ioServices;
    private User user;
    private Optional<User> userNameOfAuthenticatedUser;
    private ClassSingleton classSingleton = ClassSingleton.getInstance();

    public LoginLogoutService() throws IOException {
        ioServices = new IOServices();
        classSingleton.initializeMapUserPassword();
    }

    public void run() {
        if (user == null) {
            LoginUser();
        } else {
            LogoutUser();
        }
        run();
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

        if (userNameOfAuthenticatedUser.isPresent()) {
            System.out.println("Welcome " + userNameOfAuthenticatedUser.get().getUsername() + " !");
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

    public Optional<User> verifyLogin(User user) {
        for (String s : classSingleton.getUserPasswordMap().keySet()) {
            if (s.equals(user.getUsername()) &&
                    classSingleton.getUserPasswordMap().get(s).equals(user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void logout() {
        System.out.println(user.getUsername() + " you are successfully logged out!");
        user = null;
    }

}




