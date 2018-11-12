package com.example.waitingagreement.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by kubyhuang on 2018/11/12.
 */

public class NoteBook extends BmobObject {

    private String n_title;
    private String n_content;
    private String n_creator;
    private String n_id;
    public NoteBook() {
        this.setTableName("notebook");
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    public String getN_creator() {
        return n_creator;
    }

    public void setN_creator(String n_creator) {
        this.n_creator = n_creator;
    }

    public String getN_id() {
        return n_id;
    }

    public void setN_id(String n_id) {
        this.n_id = n_id;
    }
}
