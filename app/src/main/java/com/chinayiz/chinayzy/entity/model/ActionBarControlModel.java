package com.chinayiz.chinayzy.entity.model;

/**
 * author  by  Canrom7 .
 * CreateDate 2017/3/2 13:54
 * Class ActionBarControlModel  控制ActionBar Model
 */
public class ActionBarControlModel {
    public int showAndHide=-1;
    public int back,more,edit,car;
    public String title;

    public ActionBarControlModel(int back, int car, int edit, int more, String title) {
        this.back = back;
        this.car = car;
        this.edit = edit;
        this.more = more;
        this.title = title;
    }
    public ActionBarControlModel(int showAndHide) {
        this.showAndHide = showAndHide;
    }
    public ActionBarControlModel(int showAndHide, String title, int back, int car, int edit,int more ) {
        this.showAndHide = showAndHide;
        this.title = title;
        this.more = more;
        this.edit = edit;
        this.car = car;
        this.back = back;
    }
}
