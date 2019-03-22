import java.util.Scanner;

public class IOServices {

    private Scanner option;

    public IOServices() {
        option = new Scanner(System.in);
    }

    public int readInteger() {
        return option.nextInt();
    }

    public String readLine() {
        return option.next();
    }

    public void showLoginConsole() {
        System.out.println("1. Login");
        System.out.println("0. Exit");
        System.out.print("Select menu option: ");
    }

    public void showLogoutConsole() {
        System.out.println("1. Logout");
        System.out.println("0. Exit");
        System.out.print("Select menu option: ");
    }

}
