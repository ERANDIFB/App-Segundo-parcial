package com.example.plisya;

public class MyInfo {

    private String Nombre;
    private String fecha;
    private String artista;
    private String usuario;
    private String Mail;
    private int edad;
    private int Boletar;
    private boolean Genero;
    private boolean Animal;
    private String Password;

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getBoletar() {
        return Boletar;
    }

    public void setBoletar(int boletar) {
        Boletar = boletar;
    }

    public boolean isGenero() {
        return Genero;
    }

    public void setGenero(boolean genero) {
        Genero = genero;
    }

    public boolean isAnimal() {
        return Animal;
    }

    public void setAnimal(boolean animal) {
        Animal = animal;
    }



}