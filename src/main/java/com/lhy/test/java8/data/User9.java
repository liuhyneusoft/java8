package com.lhy.test.java8.data;

/**
 * Created by Administrator on 2017/6/10.
 */
public class User9 {
    private String name;
    private int age;

    public User9() {
    }

    public User9(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User9(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
