package com.luna.application.entity;

import java.io.Serializable;

public class UserDO implements Serializable {

    private String  userName;

    private String  password;

    private boolean remember;

    private String  gender;

    public String getGender() {
        return gender;
    }

    public UserDO(String userName, String password, String gender) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserDO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}