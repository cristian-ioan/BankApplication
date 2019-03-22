import java.io.IOException;

public class Main {

    private LoginLogoutService loginLogoutService;
    private IOServices ioServices;
    private User user;
    private String userNameOfAuthenticatedUser;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

    public Main() throws IOException {
        loginLogoutService = new LoginLogoutService();
        loginLogoutService.initializeMapUserPassword();
        ioServices = new IOServices();
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
                System.out.print( "Enter username: " );
                String username = ioServices.readLine();
                System.out.print( "Enter password: " );
                String password = ioServices.readLine();
                user = new User(username, password);
                userNameOfAuthenticatedUser = loginLogoutService.login(user);
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
                loginLogoutService.logout();
                user = null;
                System.out.println( "Successfully logout" );
                break;
            case 0:
                System.exit( 0 );
                break;
            default:
                System.out.println( "Not a valid option" );
                LogoutUser();
                break;
        }
    }

}
