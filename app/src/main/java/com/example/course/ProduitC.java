package com.example.course;

import java.util.ArrayList;

public class ProduitC {

    //Props
    private String nom;
    private double prix;
    private int img;



    //Constructor
    public ProduitC(String _nom,double _prix,int _img){
        nom = _nom;
        prix=_prix;
        img=_img;
    }

    //getters ans setters


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
