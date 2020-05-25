package com.example.hiot_cloud.new_test.mvptest.model;

import java.io.Serializable;

public class Users implements Serializable {
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
