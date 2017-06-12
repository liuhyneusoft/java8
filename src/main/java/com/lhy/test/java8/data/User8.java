package com.lhy.test.java8.data;

/**
 * Created by Administrator on 2017/6/10.
 */
public class User8 {
    private String name;
    private String age;

    public User8() {
    }

    public User8(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public User8(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
