package ru.nc.webshop1.entity;

public class Image extends Entity<Integer>{
    
    private int id;
    private String path;
    private Bicycle bicycle;

    public Image() {
    }

    public Image(String path) {
        this.path = path;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", path=" + path + '}';
    }
        
}
