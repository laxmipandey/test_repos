package edu.gatech.cs2340.todo.model;

public class Todo {
    
    private String title;
    private String task;

    public Todo(String title, String task) {
        this.title = title;
        this.task = task;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String  getTitle() {
        return title;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String  getTask() {
        return task;
    }
}
