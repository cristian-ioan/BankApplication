import menu.MainMenu;
import service.WrongUserNamePasswordException;

import java.io.IOException;

/**
 * This class starts an application that simulates a banking application.
 *
 * @throws IOException on input error
 * @throws WrongUserNamePasswordException a custom exception that throws an error message for a wrong user/password
 *
 * @author  Cristian-Lucian IOAN
 * @version 1.0
 * @since   2019-03-21
 */
public class Main {

    public static void main(String[] args) throws IOException, WrongUserNamePasswordException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainMenu();

    }

}