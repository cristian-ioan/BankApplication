package menu;

import model.User;

public class ConsoleMenu {

    private static ConsoleMenu instance;
    private ConsoleMenu(){}

    public static synchronized ConsoleMenu getInstance(){
        if (instance == null){
            instance = new ConsoleMenu();
        }
        return instance;
    }

    public void showLoginConsole() {
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Select menu option: ");
    }

    public void showAccountConsole() {
        System.out.println();
        System.out.println("1. Account");
        System.out.println("2. Logout");
        System.out.print("Select menu option: ");
    }

    public void showUserBankAccountConsole(User user){
        System.out.println();
        System.out.println("1. Create a new bank account for: " + user.getUserName());
        System.out.println("2. Display details about bank accounts for: " + user.getUserName());
        System.out.println("3. Transfer money between accounts for: " + user.getUserName());
        System.out.print("Select menu option: ");
    }

}