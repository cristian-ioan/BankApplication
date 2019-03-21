public class Main {

    private LoginLogoutService loginLogoutService;
    private IOUtils ioUtils;
    private String user;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public Main() {
        loginLogoutService = new LoginLogoutService();
        loginLogoutService.initializeMapUserPassword();
        ioUtils = new IOUtils();
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

        ioUtils.showLoginConsole();
        int option = ioUtils.readInteger();

        switch (option) {
            case 1:
                System.out.print( "Enter username: " );
                String username = ioUtils.readLine();
                System.out.print( "Enter password: " );
                String password = ioUtils.readLine();
                user = loginLogoutService.login(username, password);
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Not a valid option");
                LoginUser();
                break;
        }

        if (user != null) {
            System.out.println("Welcome " + user + " !");
        } else {
            System.out.println("Wrong username/password");
            LoginUser();
        }
    }

    private void LogoutUser() {

        ioUtils.showLogoutConsole();
        int option = ioUtils.readInteger();

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
