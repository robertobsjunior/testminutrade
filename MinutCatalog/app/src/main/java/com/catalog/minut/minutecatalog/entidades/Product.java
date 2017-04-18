package com.catalog.minut.minutecatalog.entidades;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by robertosampaio on 17/04/17.
 */

public class Product implements Serializable {

    @SerializedName("nome")
    private String nameProduct;

    @SerializedName("bandeiraPosto")
    private Image image;


    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
