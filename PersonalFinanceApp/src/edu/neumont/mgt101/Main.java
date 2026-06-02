package edu.neumont.mgt101;


import edu.neumont.mgt101.controllers.FileManager;
import edu.neumont.mgt101.controllers.FinanceController;
import edu.neumont.mgt101.controllers.LoginManager;

import java.io.FilterOutputStream;

public class Main {
    public static void main() {
        new FinanceController().run();

    }
}
