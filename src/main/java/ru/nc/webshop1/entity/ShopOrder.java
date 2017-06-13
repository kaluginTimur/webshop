package ru.nc.webshop1.entity;

public class ShopOrder extends Entity<Integer>{
    
    private int id;
    private int userid;
    private String items;
    private double price;
    private int status; //-1 - на удалении, 0 - в обработке, 1 - сборка, 2 - в пути, 3 - доставлен

    public ShopOrder() {
    }

    public ShopOrder(int userid, String items, double price, int status) {
        this.userid = userid;
        this.items = items;
        this.price = price;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getItems() {
        return items;
    }

    public double getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userid=" + userid + ", items=" + items + ", price=" + price + ", status=" + status + '}';
    }
    
}

