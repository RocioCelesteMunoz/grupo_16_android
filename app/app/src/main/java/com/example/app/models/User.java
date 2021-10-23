package com.example.app.models;

public class User {

    private String name;
    private String lastName;
    private int dni;
    private String email;
    private String password;
    private int comission;
    private int group;

    public User(String name, String lastName, int dni, String email, String password, int comission, int group) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.comission = comission;
        this.group = group;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getComission() {
        return comission;
    }

    public void setComission(int comission) {
        this.comission = comission;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
