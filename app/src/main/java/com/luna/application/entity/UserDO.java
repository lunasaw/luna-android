package com.luna.application.entity;

import java.io.Serializable;

public class UserDO implements Serializable {

    private int     id;

    private String  userName;

    private String  password;

    private boolean remember = false;

    private String  gender;

    public UserDO() {
    }

    public UserDO(String userName) {
        this.userName = userName;
    }

    public UserDO(int id, String userName, String password, String gender) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDO{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", remember=" + remember +
            ", gender='" + gender + '\'' +
            '}';
    }
}