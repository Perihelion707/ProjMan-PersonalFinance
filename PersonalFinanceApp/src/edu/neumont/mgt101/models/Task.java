package edu.neumont.mgt101.models;

public class Task {
    String taskName;
    String taskDescription;
    boolean isCompleted;

    Task (
            String taskName,
            String taskDescription
    ){
        setTaskName(taskName);
        setTaskDescription(taskDescription);
        setCompleted(false);
    }

    public String getTaskName() {
        return taskName;
    }

    private void setTaskName(String taskName) {
        if (taskName == null || taskName.isEmpty()) {return;}
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    private void setTaskDescription(String taskDescription) {
        if (taskName == null || taskName.isEmpty()) {taskName = "This task has no description";}
        this.taskDescription = taskDescription;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
