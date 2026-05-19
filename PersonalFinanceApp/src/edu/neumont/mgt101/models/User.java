package edu.neumont.mgt101.models;

import java.util.ArrayList;

public class User {
    private String name;
    private String userName;
    private String password;
    private double money;
    public ArrayList<FinancialGoal> financialGoals;
    public ArrayList<Transaction> transactions;


    public User(String name, double money, String userName, String password) {
        setName(name);
        setMoney(money);
        setUserName(userName);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isBlank()) {
            this.name = name;
        } else {
            this.name = "UNKNOWN";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName != null && !userName.isBlank()) {
            this.userName = userName;
        } else {
            this.userName = "UNKNOWN";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null && !password.isBlank()) {
            this.password = password;
        } else {
            this.password = "UNKNOWN";
        }
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
