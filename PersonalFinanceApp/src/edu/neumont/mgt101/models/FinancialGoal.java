package edu.neumont.mgt101.models;

public class FinancialGoal
{
    private int moneyTarget;
    private String goalName;
    private int daysLeft;
    private boolean isComplete;

    //region Getters and Setters
    public int getMoneyTarget() {
        return moneyTarget;
    }

    public void setMoneyTarget(int moneyTarget) {
        if (moneyTarget < 0)
        {
            throw new IllegalArgumentException("Money Target Must be a positive integer");
        }
        this.moneyTarget = moneyTarget;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        if (goalName.isBlank())
        {
            throw new IllegalArgumentException("Goal Name must not be empty");
        }
        this.goalName = goalName;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        if (daysLeft < 0)
        {
            throw new IllegalArgumentException("Days Left Must be a positive integer");
        }
        this.daysLeft = daysLeft;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean complete) {
        isComplete = complete;
    }
    //endregion


    public FinancialGoal(int moneyTarget, String goalName, int daysLeft, boolean isComplete) {
        setMoneyTarget(moneyTarget);
        setGoalName(goalName);
        setDaysLeft(daysLeft);
        setIsComplete(isComplete);
    }

    @Override
    public String toString() {
        return "Max Spend Amount: " + moneyTarget +
                ", Goal Name: " + goalName + '\'' +
                ", Days lefts: " + daysLeft;
    }
}

