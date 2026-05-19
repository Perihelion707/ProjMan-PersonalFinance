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

    public void writeData(String path, String data){
        File file = new File(path);
        if (Files.exists(Paths.get(path)) == false){
            //System.out.println("Login data has been found successfully.");
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write("\n");
            fw.write(data);
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void writeLoginData(){
        if (currentUser == null){
            return;
        }
        writeData(LOGIN_LOG, currentUser.name + " Logged in");
        //writeData(LOGIN_SAVE_PATH, " in");

    }

    public String readFile(String path){
        File file = new File(path);
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

    public void parseLoginData() {

    }

    public void testWriteReadData(){
        writeLoginData();
        readFile(LOGIN_SAVE_PATH);
        System.out.println("\n");
        readFile(LOGIN_LOG);
    }
}
