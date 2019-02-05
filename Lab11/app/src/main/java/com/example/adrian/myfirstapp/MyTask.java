package com.example.adrian.myfirstapp;

import java.util.Date;

public class MyTask {

    private String name;
    private String description;
    private Date deadline;

    public MyTask() {
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public MyTask(String name, String description, Date deadline) {
        this.description = description;
        this.name = name;
        this.deadline = deadline;
    }
}
