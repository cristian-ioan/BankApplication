import menu.MainMenu;
import service.WrongUserNamePasswordException;

import java.io.IOException;

public class Main {

    /**
     * Starts an application that simulates a banking application.
     *
     * @throws IOException on input error
     * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
     */
    public static void main(String[] args) throws IOException, WrongUserNamePasswordException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainMenu();

    }

}