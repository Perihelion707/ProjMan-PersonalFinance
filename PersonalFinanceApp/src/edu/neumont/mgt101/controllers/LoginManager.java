package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.User;

import java.util.ArrayList;

public class LoginManager {
    private final String LOGIN_SAVE_PATH = "";

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
}
