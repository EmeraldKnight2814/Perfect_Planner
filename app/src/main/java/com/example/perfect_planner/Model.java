package com.example.perfect_planner;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {
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

    public void setAsgmtList(ArrayList<Asgmt> assignments) {
    }


    public static class Asgmt {
        private String asgmt;
        private String cat;
        private String date;

        public Asgmt(String asgmt, String date, String cat) {
            this.asgmt = asgmt;
            this.date = date;
            this.cat = cat;
        }

        public String getAsgmt() {
            return this.asgmt;
        }
        public void setAsgmt(String asgmt) {
            this.asgmt = asgmt;
        }
        public String getDate() {
            return this.date;
        }
        public void setDate(String date) {
            this.date = date;
        }
        public String getCat(){ return this.cat; }
        public void setCat(String cat){ this.cat = cat; }
    }

    public void clear() {
        asgmtList.clear();
    }

    public void removeItem(int position) {
        DelModel.getModel().getAsgmtList().add(asgmtList.get(position));
        asgmtList.remove(position);
    }

}
