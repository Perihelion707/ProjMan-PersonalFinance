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
import java.util.Scanner;

public class LoginManager {
    private final String LOGIN_SAVE_PATH = "./LoginData.txt";

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

        File file = new File(LOGIN_SAVE_PATH);
        if (Files.exists(Paths.get(LOGIN_SAVE_PATH))){
            System.out.println("Login data has been found successfully.");
        }

        try{
            if (file.createNewFile()){
                System.out.println("Login data has been created successfully.");
            }else{
                System.out.println("Login data has been not been created.");
            }
        } catch(IOException e){
            e.printStackTrace();
            return;
        }

        try {
            FileWriter fw = new FileWriter(LOGIN_SAVE_PATH, true);
            fw.write("\nim just a little guy and this is a little test.");
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String readFile(){
        File file = new File(LOGIN_SAVE_PATH);
        StringBuilder content = new StringBuilder();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                content.append(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return content.toString();
    }
}
