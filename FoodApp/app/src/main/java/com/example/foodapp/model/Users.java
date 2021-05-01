package com.example.foodapp.model;

public class Users {
    private String Name;
    private String Address;
    private String Phone;
    private String Emaill;
    private String Profile_pic;

    public Users() {
    }

    public Users(String name, String address, String phone, String emaill, String profile_pic) {
        Name = name;
        Address = address;
        Phone = phone;
        Emaill = emaill;
        Profile_pic = profile_pic;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
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
}
