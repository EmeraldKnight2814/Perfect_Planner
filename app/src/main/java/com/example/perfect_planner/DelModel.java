package com.example.perfect_planner;

import java.util.ArrayList;

public class DelModel {
    private static DelModel theModel = null;
    private ArrayList<Model.Asgmt> asgmtList;

    private DelModel() {
        asgmtList = new ArrayList<Model.Asgmt>();
    }

    public static DelModel getModel() {
        if (theModel == null) {
            theModel = new DelModel();
        }
        return theModel;
    }

    public ArrayList<Model.Asgmt> getAsgmtList() {
        return asgmtList;
    }

    public void clear() {
        asgmtList.clear();
    }

    public void removeItem(int position) {
        asgmtList.remove(position);
    }

    public void restoreItem(int position){
        Model.getModel().getAsgmtList().add(asgmtList.get(position));
        asgmtList.remove(position);
    }

    // Restore all the assignments in the trash can
    public void restoreAll(){
        for(int i = 0; i < asgmtList.size(); i++){
            Model.getModel().getAsgmtList().add(asgmtList.get(i));
        }
        asgmtList.clear();
    }
}
