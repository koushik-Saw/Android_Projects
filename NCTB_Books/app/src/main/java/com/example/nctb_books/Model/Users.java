package com.example.nctb_books.Model;

public class Users {
    String Full_name;
    String Phone;
    String Email;
    String Address;

    public Users() {
    }

    public Users(String full_name, String phone, String email, String address) {
        Full_name = full_name;
        Phone = phone;
        Email = email;
        Address = address;
    }

    public String getFull_name() {
        return Full_name;
    }

    public void setFull_name(String full_name) {
        Full_name = full_name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
