package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.User;
import edu.neumont.mgt101.view.TUI;

public class FinanceController
{
    private User currUser;

    public void run(){

    }
    public void login(){}
    public int mainMenu(){
        TUI.println("1. Deposit Money " +
                "\n2. Withdraw Money " +
                "\n3. Add Finance " +
                "\n4. Add Transaction ");
        int userChoice = TUI.inputMenuOption("What would you like to do?");
        return userChoice;
    }
    public void addGoal(){}
    public void changeIncome(){}
    public void changeExpenses(){}
    public void addTransaction(){}
    public void saveAndLogout(){}
}
