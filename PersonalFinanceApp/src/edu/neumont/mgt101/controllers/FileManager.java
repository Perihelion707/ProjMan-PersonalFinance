package edu.neumont.mgt101.controllers;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.neumont.mgt101.models.FinancialGoal;
import edu.neumont.mgt101.models.Transaction;
import edu.neumont.mgt101.models.TransactionType;
import edu.neumont.mgt101.models.User;

import javax.naming.Name;

public class FileManager {
    static final private int ENCRYPTION_KEY = 632146;
    static final private String USER_SAVE_FILE = "Users";

    public static void writeUserData(User user){
        writeUserData(user, true);
    }

    public static void writeUserData(User user, boolean append){
        StringBuilder sb = new StringBuilder();
        sb.append(user.getName());
        sb.append(',');
        sb.append(user.getMoney());
        sb.append(',');
        sb.append(user.getUserName());
        sb.append(',');
        sb.append(user.getPassword());
        sb.append(',');
        sb.append('[');
        if (!user.financialGoals.isEmpty()){
            for (FinancialGoal goal : user.financialGoals) {
                if (goal == null)
                    continue;
                sb.append(goal.getGoalName());
                sb.append('-');
                sb.append(goal.getMoneyTarget());
                sb.append('-');
                sb.append(goal.getDaysLeft());
                sb.append('-');
                sb.append(goal.getIsComplete());
                sb.append('>');
            }
        }
        sb.append(']');
        sb.append(',');
        sb.append('[');
        if (!user.transactions.isEmpty()) {

            for (Transaction transaction : user.transactions) {
                if (transaction == null)
                    continue;
                sb.append(transaction.getTransactionName());
                sb.append('-');
                sb.append(transaction.getTransactionDescription());
                sb.append('-');
                sb.append(transaction.getTransactionType());
                sb.append('-');
                sb.append(transaction.getMoneyAmount());
            }
        }
        sb.append(']');
        writeData(USER_SAVE_FILE, sb.toString(), append);
    }

    public static void writeData(String fileName, String data){
        writeData(fileName, data, true);
    }

    public static void writeData(String path, String data,  Boolean append) {
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
            FileWriter fw = new FileWriter(path, append);
            fw.write(encryptData(data));
            fw.write(encryptData("<\n"));
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String readFile(String path){
        File file = new File(path);
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        StringBuilder content = new StringBuilder();
        try (Scanner reader = new Scanner(file)) {

            while (reader.hasNextLine()) {
                String line = decryptData(reader.nextLine());
                content.append(line);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return content.toString();
    }

    private static FinancialGoal parseFinancialGoal(String finGoalStr){
        if (finGoalStr.isEmpty())
            return null;
        finGoalStr = finGoalStr.replace(">", "");
        String[] attributes = finGoalStr.split("-");
        if (attributes.length !=4)
            return null;
        String goalName = attributes[0];
        int goalMoneyTarget;
        int goalDaysLeft;
        try{
            goalMoneyTarget = Integer.parseInt(attributes[1]);
            goalDaysLeft = Integer.parseInt(attributes[2]);
        }catch (NumberFormatException e){
            return null;
        }
        boolean goalIsComplete = Boolean.parseBoolean(attributes[3]);

        return new FinancialGoal(
                goalMoneyTarget,
                goalName,
                goalDaysLeft,
                goalIsComplete
        );
    }

    private static Transaction parseTransaction(String transactionStr){

        if (transactionStr.isEmpty())
            return null;
        transactionStr = transactionStr.replace(">", "");
        String[] attributes = transactionStr.split("-");
        if (attributes.length != 4)
            return null;
        String transactionName = attributes[0];
        String transactionDescription = attributes[1];
        TransactionType transactionType;
        double transactionMoneyAmount;
        try{
            transactionMoneyAmount = Double.parseDouble(attributes[3]);
            transactionType = TransactionType.valueOf(attributes[2]);
        }catch (IllegalArgumentException e){
            return null;
        }

        return new Transaction(
                transactionType,
                transactionName,
                transactionDescription,
                transactionMoneyAmount
        );
    }

    private static User parseUserFromSave(String save) {
        String[] lines = save.split(",");
        if (lines.length != 6)
            return null;
        String[] financialGoalStrings = lines[4]
                .replace("[", "")
                .replace("]", "")
                .split(">");

        String[] transactionStrings = lines[5]
                .replace("[", "")
                .replace("]", "")
                .split(",");

        String name = lines[0];
        String userName = lines[2];
        String password = lines[3];
        double money;
        try{
            money = Double.parseDouble(lines[1]);
        }catch (NumberFormatException e){
            money = 0;
        }
        ArrayList<FinancialGoal> financialGoals = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        for(String financialGoal : financialGoalStrings){
            financialGoals.add(parseFinancialGoal(financialGoal));
        }
        for (String transaction : transactionStrings) {
            transactions.add(parseTransaction(transaction));
        }
        User newUser = new User(
                name,
                money,
                userName,
                password
        );
        newUser.financialGoals = financialGoals;
        newUser.transactions = transactions;

        return newUser;
    }

    public static List<User> getUserAccounts(){
        String save = readFile(USER_SAVE_FILE);
        //System.out.println("SAVE:" + save);
        String[] lines = save.split("%");
        if (lines.length < 1)
            return null;
        List<User> userAccounts = new ArrayList<>();
        for (String line : lines) {
            User user = parseUserFromSave(line);
            if (user == null)
                continue;
            userAccounts.add(user);
        }

        return userAccounts;
    }

    public static void saveUserAccounts(List<User> userAccounts){
        writeData(USER_SAVE_FILE, "", false);
        for (User user : userAccounts){
            if (user == null)
                continue;
            writeUserData(user, true);
        }
    }

    //todo: after testing the encyption and decryption make everything static
    private static String encryptData(String data){
        String encryptedData = "";
        for (int charIndex = 0; charIndex < data.length(); charIndex++){
            char currentChar = data.charAt(charIndex);
            if (currentChar != '\n')
                currentChar = (char)((int) currentChar + ENCRYPTION_KEY);
                //currentChar = (char)((int) currentChar);
            encryptedData += currentChar;
        }
        return encryptedData;
    }
    private static String decryptData(String data){
        String encryptedData = "";
        for (int charIndex = 0; charIndex < data.length(); charIndex++){
            char currentChar = data.charAt(charIndex);
            if (currentChar != '\n')
                //currentChar = (char)((int) currentChar);
                currentChar = (char)((int) currentChar - ENCRYPTION_KEY);

            encryptedData += currentChar;
        }
        return encryptedData;
    }
}
