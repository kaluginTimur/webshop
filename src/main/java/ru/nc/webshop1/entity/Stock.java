package ru.nc.webshop1.entity;

public class Stock extends Entity<Integer>{
    
    private int id;
    private int amount;
    private Bicycle bicycle;

    public Stock() {
    }

    public Stock(int amount) {
        this.amount = amount;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", amount=" + amount + '}';
    }
    
}