package com.system.entity;

import java.io.Serializable;

public class Gamer implements Serializable{
    private Long id=-1L;
    private String password;
    private String email;
    private String name;
    private String gender;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Gamer [email=");
        builder.append(email);
        builder.append(", password=");
        builder.append(password);
        builder.append(", name=");
        builder.append(name);
        builder.append(", gender=");
        builder.append(gender);
        builder.append("]");
        return builder.toString();
    }


}
