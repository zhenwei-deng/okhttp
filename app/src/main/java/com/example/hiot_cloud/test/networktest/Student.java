package com.example.hiot_cloud.test.networktest;

import java.io.Serializable;

/**
 * 学生类接口
 */
class Student implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * id
     */
    private int id;
    /**
     * 身高
     */
    private int height;
    /**
     * 毕业否
     */
    private boolean graduation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGraduation() {
        return graduation;
    }

    public void setGraduation(boolean graduation) {
        this.graduation = graduation;
    }
}
