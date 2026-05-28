package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class LoginManager {
    private final String LOGIN_LOG = "./LoginLog.txt";

    private List<User> userAccounts = FileManager.getUserAccounts();
    private User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User login(String username, String password) {

        for  (User user : userAccounts) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public void createAccount(User user) {
        FileManager.writeUserData(user, true);
    }

    public void createAccount(
            String name,
            String username,
            String password,
            double money
    ){
        User user = new User(
               name,
               money,
               username,
               password
        );
        createAccount(user);
    }

    public boolean isLoggedIn(){
        return currentUser != null;
    }

    public void logout(){
        currentUser = null;
    }

    public void writeLoginData(){
        if (currentUser == null){
            return;
        }
        FileManager.writeData(LOGIN_LOG, currentUser.getName() + " Logged in");
        //writeData(LOGIN_SAVE_PATH, " in");
    }
}