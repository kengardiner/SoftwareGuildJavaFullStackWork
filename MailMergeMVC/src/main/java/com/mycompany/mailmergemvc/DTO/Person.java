/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailmergemvc.DTO;

/**
 *
 * @author n0252282
 */
public class Person {

    private String email;
    private String name;

    public Person(String email, String name) {
        //shadowing - when parameter name matches instance name of variable
        this.email = email;
        this.name = name;
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
}
