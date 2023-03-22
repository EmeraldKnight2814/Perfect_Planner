package com.example.perfect_planner;

import java.util.ArrayList;

public class Model {
    private static Model theModel = null;
    private ArrayList<Task> taskList;

    private Model() {
        taskList = new ArrayList<Task>();
    }

    public static Model getModel() {
        if (theModel == null) {
            theModel = new Model();
        }
        return theModel;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void newPage(int position) {
        //add a code to open a new activity layout
    }

    public static class Task {
        private String assignment;
        private int date;

        public Task(String assignment, int date) {
            this.assignment = assignment;
            this.date = date;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getCount() {
            return count;
        }
        public void setCount(int count) {
            this.count = count;
        }
    }

    public void clear() {
        taskList.clear();
    }

    public void zeroCount() {
        for (Task task : taskList) {
            task.setCount(0);
        }
    }
}
