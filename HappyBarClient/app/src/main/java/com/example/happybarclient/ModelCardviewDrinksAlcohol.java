package com.example.happybarclient;

public class ModelCardviewDrinksAlcohol {

    String nameCardview, precio_cardview, image_cardview;

    public ModelCardviewDrinksAlcohol(String nameCardview, String precio_cardview, String image_cardview) {
        this.nameCardview = nameCardview;
        this.precio_cardview = precio_cardview;
        this.image_cardview = image_cardview;
    }

    public String getNameCardview() {
        return nameCardview;
    }

    public void setNameCardview(String nameCardview) {
        this.nameCardview = nameCardview;
    }

    public String getPrecio_cardview() {
        return precio_cardview;
    }

    public void setPrecio_cardview(String precio_cardview) {
        this.precio_cardview = precio_cardview;
    }

    public String getImage_cardview() {
        return image_cardview;
    }

    public void setImage_cardview(String image_cardview) {
        this.image_cardview = image_cardview;
    }
}
