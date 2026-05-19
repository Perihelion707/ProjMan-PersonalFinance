package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.FinancialGoal;
import edu.neumont.mgt101.models.User;
import edu.neumont.mgt101.view.TUI;

public class FinanceController
{
    private User currUser;

    public void run(){}
    public void login(){}
    public void mainMenu(){}
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
    public void addTransaction(){}
    public void saveAndLogout(){}
}
