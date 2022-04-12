package com.example.khatabook;


public class Contact2Model {
    String name,phone,date;

    public Contact2Model()
    {

    }
    public Contact2Model(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
