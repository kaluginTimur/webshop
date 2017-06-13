package ru.nc.webshop1.entity;

public class Customer extends Entity<Integer>{
    private int id;
    private String name;
    private String pass;
    private int role = 1;

    public Customer() {
    }

    public Customer(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", pass=" + pass + ", role=" + role + '}';
    }
}

