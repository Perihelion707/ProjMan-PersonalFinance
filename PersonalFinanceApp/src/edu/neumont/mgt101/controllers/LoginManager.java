package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.User;

import java.util.ArrayList;


public class LoginManager {
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
        FileManager.writeData(LOGIN_LOG, currentUser.getName() + " Logged in");
        //writeData(LOGIN_SAVE_PATH, " in");
    }


    public void parseLoginData() {

    }
}
