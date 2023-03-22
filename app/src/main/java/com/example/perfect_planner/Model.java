package com.example.perfect_planner;

import java.util.ArrayList;

public class Model {
    private static Model theModel = null;
    private ArrayList<Asgmt> asgmtList;

    private Model() {
        asgmtList = new ArrayList<Asgmt>();
    }

    public static Model getModel() {
        if (theModel == null) {
            theModel = new Model();
        }
        return theModel;
    }

    public ArrayList<Asgmt> getAsgmtList() {
        return asgmtList;
    }
    
    public static class Asgmt {
        private String asgmt;
        private int date;

        public Asgmt(String asgmt, int date) {
            this.asgmt = asgmt;
            this.date = date;
        }

        public String getAsgmt() {
            return asgmt;
        }
        public void setAsgmt(String asgmt) {
            this.asgmt = asgmt;
        }
        public int getDate() {
            return date;
        }
        public void setDate(int date) {
            this.date = date;
        }
    }

    public void clear() {
        asgmtList.clear();
    }

}
