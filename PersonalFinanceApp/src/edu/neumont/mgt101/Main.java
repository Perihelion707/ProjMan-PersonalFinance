package edu.neumont.mgt101;


import edu.neumont.mgt101.controllers.FileManager;
import edu.neumont.mgt101.controllers.FinanceController;
import edu.neumont.mgt101.controllers.LoginManager;

import java.io.FilterOutputStream;

public class Main {
    public static void main() {
        //todo: Remove after testing is over
        System.out.println(FileManager.readFile("Users"));
        new FinanceController().run();

    }
}
