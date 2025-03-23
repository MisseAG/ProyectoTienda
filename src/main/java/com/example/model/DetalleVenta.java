package com.example.model;

public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double subTotal;

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subTotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubTotal() { return subTotal; }

}
