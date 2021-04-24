package com.example.keuangan.paketku;

public class User {
    public String username;
    public String name;
    public String surname;
    public String email;
    public String phone;
    public String addr;

    public User(){
        this.username = "";
        this.name = "";
        this.surname = "";
        this.email = "";
        this.phone = "";
        this.addr = "";

    }

    public User(String username, String name, String surname, String email, String phone, String addr){
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.addr = addr;
    }

    public String getUsername(){return  username;}
    public String getName(){return  name;}
    public String getSurname(){return  surname;}
    public String getEmail(){return  email;}
    public String getPhone(){return  phone;}
    public String getAddr(){return  addr;}
}
