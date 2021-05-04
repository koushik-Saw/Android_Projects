package com.example.safety.model;

public class Users {
    private String Name;
    private String Address;
    private String Trusted_contact_1;
    private String Emaill;
    private String Profile_pic;
    private String Trusted_contact_2;


    public Users() {
    }


    public Users(String name, String address, String trusted_contact_1, String emaill, String profile_pic, String trusted_contact_2) {
        Name = name;
        Address = address;
        Trusted_contact_1 = trusted_contact_1;
        Emaill = emaill;
        Profile_pic = profile_pic;
        Trusted_contact_2 = trusted_contact_2;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTrusted_contact_1() {
        return Trusted_contact_1;
    }

    public void setTrusted_contact_1(String trusted_contact_1) {
        Trusted_contact_1 = trusted_contact_1;
    }

    public String getEmaill() {
        return Emaill;
    }

    public void setEmaill(String emaill) {
        Emaill = emaill;
    }

    public String getProfile_pic() {
        return Profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        Profile_pic = profile_pic;
    }

    public String getTrusted_contact_2() {
        return Trusted_contact_2;
    }

    public void setTrusted_contact_2(String trusted_contact_2) {
        Trusted_contact_2 = trusted_contact_2;
    }
}

