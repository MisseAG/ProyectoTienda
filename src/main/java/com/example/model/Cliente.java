package com.example.model;

public class Cliente {
    private String nombres;
    private String direccion;
    private String telefono;
    private String email;
    private String identificacion;

    public Cliente(String nombres, String direccion, String telefono, String email, String identificacion){
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.identificacion = identificacion;
    }

    //getters

    public String getIdentificacion(){
        return identificacion;
    }
    public String getNombres(){
        return nombres;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getEmail(){
        return email;
    }
    public String getDireccion(){
        return direccion;
    }
    //setters

    public void setNombres(String nombres){
        this.nombres = nombres;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void setIdentificacion(String identificacion){
        this.identificacion = identificacion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }



}
