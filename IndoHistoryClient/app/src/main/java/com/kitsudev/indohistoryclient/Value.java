package com.kitsudev.indohistoryclient;

import java.util.List;

public class Value {

    String value;
    String message;
    String uid;
    String admin;
    String user;
    String nama;
    List<Result> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
    public List<Result> getResult() {
        return result;
    }
    public String getUid() {
        return uid;
    }
    public String getAdmin() {
        return admin;
    }
    public String getUser() {
        return user;
    }
    public String getNama() {
        return nama;
    }
}
