package ru.nc.webshop1.entity;

public class ShopCart extends Entity<Integer> {

    private int id;
    private int userid;
    private int itemid;
    private int amount;

    public ShopCart() {
    }

    public ShopCart(int userid, int itemid, int amount) {
        this.userid = userid;
        this.itemid = itemid;
        this.amount = amount;
    }    
    
    @Override
    public Integer getId() {
        return id; 
    }

    public int getUserid() {
        return userid;
    }
    
    public int getItemid() {
        return itemid;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return "ShopCart{" + "id=" + id + ", userid=" + userid + ", itemid=" + itemid + ", amount=" + amount + '}';
    }
}
