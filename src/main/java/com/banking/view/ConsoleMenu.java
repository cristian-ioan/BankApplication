package com.banking.view;

public class ConsoleMenu {

    private static ConsoleMenu instance;
    private ConsoleMenu(){}

    public static synchronized ConsoleMenu getInstance(){
        if (instance == null){
            instance = new ConsoleMenu();
        }
        return instance;
    }

    /**
     * Displays login console.
     */
    public void showLoginConsole() {
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Select menu option: ");
    }

    /**
     * Displays Account/Logout console.
     */
    public void showAccountConsole() {
        System.out.println();
        System.out.println("1. Account");
        System.out.println("2. Logout");
        System.out.print("Select menu option: ");
    }

    /**
     * Displays console for actions than logged user can do for his bank accounts.
     */
    public void showUserBankAccountConsole(){
        System.out.println();
        System.out.println("1. Create a new bank account");
        System.out.println("2. Display details about bank accounts");
        System.out.println("3. Transfer money between accounts");
        System.out.print("Select menu option: ");
    }

}