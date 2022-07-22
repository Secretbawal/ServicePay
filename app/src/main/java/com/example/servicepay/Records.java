package com.example.servicepay;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Records implements Serializable {
    @Exclude
    private String key;
    private String fullname;
    private String mobilenumber;
    private String emailaddress;

    public Records(){ }
    public Records(String fullname, String mobilenumber, String emailaddress) {
        this.fullname = fullname;
        this.mobilenumber = mobilenumber;
        this.emailaddress = emailaddress;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
