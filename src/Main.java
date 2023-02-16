import Menus.Menu;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Menu menu = new Menu();
        menu.displayMenu1();
    }
}

