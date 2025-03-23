package com.example.model;

import java.util.ArrayList;

public class Venta {
    private String fechaVenta;
    private double montoTotal;
    private Cliente cliente;
    private ArrayList<DetalleVenta> listaDetallesVenta;

    public Venta(String fechaVenta, Cliente cliente) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.listaDetallesVenta = new ArrayList<>();
        this.montoTotal = 0;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        listaDetallesVenta.add(detalle);
    }

    public boolean validarStockDisponible() {
        for (DetalleVenta detalle : listaDetallesVenta) {
            if (!detalle.getProducto().tieneStockSuficiente(detalle.getCantidad())) {
                return false;
            }
        }
        return true;
    }

    public void aplicarVenta() {
        for (DetalleVenta detalle : listaDetallesVenta) {
            detalle.getProducto().reducirStock(detalle.getCantidad());
        }
        calcularMontoTotal();
    }

    public void calcularMontoTotal() {
        montoTotal = 0;
        for (DetalleVenta detalle : listaDetallesVenta) {
            montoTotal += detalle.getSubTotal();
        }
    }
    //getters
    public String getFechaVenta() { 
        return fechaVenta; 
    }
    public Cliente getCliente() { 
        return cliente; 
    }
    public ArrayList<DetalleVenta> getListaDetallesVenta(){
        return listaDetallesVenta; 
    }
    public double getMontoTotal() { 
        return montoTotal; 
        }
    
}
