package com.zeed.kafka.entity;

public class User {

    private String name;

    private int age;

    private boolean isMatured = false;

    public User(String name, int age, boolean isMatured) {
        this.name = name;
        this.age = age;
        this.isMatured = isMatured;
    }

    public User() {
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

    public boolean isMatured() {
        return isMatured;
    }

    public void setMatured(boolean matured) {
        isMatured = matured;
    }
}
