package com.avengers.rpgame.graphics.store;

public class Item {
    private int id;
    private String nombre;
    private String description;
    private String imagePath;
    private int price;
    private int itemType;
    private int levelRequired;


    public Item(int id, String nombre, String description, String imagePath, int price, int itemType, int levelRequired) {
        this.setId(id);
        this.setNombre(nombre);
        this.setDescription(description);
        this.setImagePath(imagePath);
        this.setPrice(price);
        this.setItemType(itemType);
        this.setLevelRequired(levelRequired);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }
}
