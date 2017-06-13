package ru.nc.webshop1.entity;

public class Bicycle extends Entity<Integer>{
    private int id;
    private String model;
    private String type;
    private double weight;
    private double wheeldiam;
    private double price;
    private Stock stock;
    private Image image;

    public Bicycle() {
    }

    public Bicycle(String model, String type, double weight, double wheeldiam, double price) {
        this.model = model;
        this.type = type;
        this.weight = weight;
        this.wheeldiam = wheeldiam;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public double getWheeldiam() {
        return wheeldiam;
    }

    public double getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    public Image getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWheeldiam(double wheeldiam) {
        this.wheeldiam = wheeldiam;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Bicycle{" + "id=" + id + ", model=" + model + ", type=" + type + ", weight=" + weight + ", wheeldiam=" + wheeldiam + ", price=" + price + '}';
    }
}
