import menu.MainMenu;
import service.WrongUserNamePasswordException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, WrongUserNamePasswordException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainMenu();

    }

}