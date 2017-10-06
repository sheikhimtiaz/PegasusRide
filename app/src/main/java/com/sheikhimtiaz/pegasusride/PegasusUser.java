package com.sheikhimtiaz.pegasusride;

/**
 * Created by sheikhimtiaz on 11/5/2016.
 */

public class PegasusUser {
    private String name;
    private String age;
    private String phoneNumber;
    private String hometown;

    public PegasusUser(){

    }

    public PegasusUser(String name, String age, String phoneNumber, String hometown){
        this.name=name;
        this.age=age;
        this.phoneNumber=phoneNumber;
        this.hometown=hometown;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHometown() {
        return hometown;
    }
}
