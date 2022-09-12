package com.webappdemo.www.entity;

public enum Roles {

    Normal("Normal User");

    public final String DESCRIPTION;

    Roles(String description) {
        this.DESCRIPTION = description;
    }
}
