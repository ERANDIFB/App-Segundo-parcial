package com.example.juntohtml;

import java.io.Serializable;

public class MyInfo1 implements Serializable {
    private String name;
    private String contra;
    private int image;
    private String red;

    public MyInfo1()
    {
    }

    public String getContra() {

        return contra;
    }

    public void setContra(String contra) {

        this.contra = contra;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getImage() {

        return image;
    }

    public void setImage(int image) {

        this.image = image;
    }

    public String getRed() {

        return red;
    }

    public void setRed(String red) {

        this.red = red;
    }
}