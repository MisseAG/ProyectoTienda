package com.example.model;

public class Producto {
    private String nombre;
    private String codigo;
    private Double precio;
    private String categoria;
    private int stock;

    public Producto(String nombre, String codigo, double precio, String categoria, int stock) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
    }

    //getters
    public String getNombre(){
        return nombre;
    }
    public String getCodigo(){ 
        return codigo; 
    }
    public double getPrecio(){ 
        return precio; 
    }
    public int getStock(){
        return stock; 
    }
    public String getCategoria(){
        return categoria;
    }

    //Método para reducir Stock Param Cantidad
    public boolean tieneStockSuficiente(int cantidad) {
        return stock >= cantidad;
    }

    public void reducirStock(int cantidad) {
        if (tieneStockSuficiente(cantidad)) {
            stock -= cantidad;
        }
    }
    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
