package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.User;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LoginManager {
    private final String LOGIN_SAVE_PATH = "./LoginData.txt";
    private final String LOGIN_LOG = "./LoginLog.txt";

    private ArrayList<User> userAccounts;
    private User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void login(String username, String password) {

    }

    public boolean isLoggedIn(){
        return true;
    }

    public void writeLoginData(){
        if (currentUser == null){
            return;
        }
        FileManager.writeData(LOGIN_LOG, currentUser.name + " Logged in");
        //writeData(LOGIN_SAVE_PATH, " in");
    }


    public void parseLoginData() {

    }

    public void testWriteReadData(){
        writeLoginData();
        FileManager.readFile(LOGIN_SAVE_PATH);
        System.out.println("\n");
        FileManager.readFile(LOGIN_LOG);
    }
}
