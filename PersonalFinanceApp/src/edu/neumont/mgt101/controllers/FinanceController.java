package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.FinancialGoal;
import edu.neumont.mgt101.models.Transaction;
import edu.neumont.mgt101.models.TransactionType;
import edu.neumont.mgt101.models.User;
import edu.neumont.mgt101.view.TUI;

public class FinanceController
{
    private User currUser;

    public void run(){}
    public void login(){}
    public int mainMenu(){
        TUI.println("1. Deposit Money " +
                "\n2. Withdraw Money " +
                "\n3. Add Finance " +
                "\n4. Add Transaction ");
        return TUI.inputMenuOption("What would you like to do?",1,4);
    }
    /**
     * Asks the User for Financial Goal Parameters and then adds that goal to the user
     */
    public void addGoal()
    {
        while (true)
        {
            try {
                TUI.println("What is the max amount of money you are allowing yourself to spend during this goal");
                String goalMoney = TUI.inputString();
                int goalAmount = Integer.parseInt(goalMoney);
                TUI.println("What do you want this goal to be named");
                String goalName = TUI.inputString();
                TUI.println("How many days should this goal last? \n");
                String goalLength = TUI.inputString();
                int goalDays = Integer.parseInt(goalLength);

                FinancialGoal userGoal = new FinancialGoal(goalAmount, goalName, goalDays, false);
                currUser.financialGoals.add(userGoal);
                break;

            }
            catch (Exception e)
            {
                TUI.println(e.getMessage());
            }
        }
    }
    public void changeIncome(){}
    public void changeExpenses(){}
    public void addTransaction()
    {
        while(true)
        {
            try
            {
                TUI.println("What Type is this Transaction? (D/W)");
                String transactionString = TUI.inputString();
                TransactionType transactionType;
                if (transactionString.equalsIgnoreCase("D"))
                {
                    transactionType = TransactionType.DEPOSIT;
                }
                else if (transactionString.equalsIgnoreCase("W"))
                {
                    transactionType = TransactionType.WITHDRAWAL;
                }
                else
                {
                    TUI.println("Invalid Transaction");
                    continue;
                }
                TUI.println("What is the transaction name?");
                String transactionName = TUI.inputString();
                TUI.println("What was the amount of money involved with the transaction?");
                String moneyString = TUI.inputString();
                float money = Float.parseFloat(moneyString);
                TUI.println("Would you like to add a description to the this transaction?");
                String description = TUI.inputString();
                if (description.equalsIgnoreCase("Y"))
                {
                    TUI.println("What is the description?");
                    description = TUI.inputString();
                    Transaction transaction = new Transaction(transactionType, transactionName, description, money);
                    currUser.transactions.add(transaction);
                    TUI.println("Transaction stored");
                    break;
                }
                else
                {
                    Transaction transaction = new Transaction(transactionType, transactionName, money);
                    currUser.transactions.add(transaction);
                    TUI.println("Transaction stored");
                    break;
                }
            }
            catch (Exception e)
            {
                TUI.println("Invalid input type");
            }
        }
    }
    public void saveAndLogout(){}
}
