package com.example.model;

import java.util.ArrayList;


public class Tienda {
    private String nombre;
    private String nit;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Producto> listaProducto;
    private ArrayList<Venta> listaVenta;

    public Tienda(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        this.listaCliente = new ArrayList<>();
        this.listaProducto = new ArrayList<>();
        this.listaVenta = new ArrayList<>();
    }
    


    //CRUD para Cliente
    public boolean agregarCliente(Cliente cliente) {
        for (Cliente cAux : listaCliente) {
            if (cAux.getIdentificacion().equals(cliente.getIdentificacion())) {
                return false; // Cliente ya registrado
            }
        }
        listaCliente.add(cliente);
        return true;
    }

    public boolean eliminarCliente(String id) {
        Cliente cliente = buscarCliente(id); 
        if (cliente != null) {
            listaCliente.remove(cliente);
            return true;
        }
        return false;
    }
    
    public ArrayList<Cliente> obtenerClientes() {
        return listaCliente;
    }
    
    public Cliente buscarCliente(String idCliente){
        Cliente cliente = null;
                for (Cliente cAux : obtenerClientes()) {
                    if (cAux.getIdentificacion().equals(idCliente)) {
                        cliente = cAux;
                        break;
                    }
                }
        return cliente;
    }

    //CRUD para Producto
    public boolean agregarProducto(Producto producto) {
        for (Producto productoAux : listaProducto) {
            if (productoAux.getCodigo().equals(producto.getCodigo())) {
                return false; // No se agrega producto duplicado
            }
        }
        listaProducto.add(producto);
        return true;
    }
    
    public boolean eliminarProducto(String codigo) {
        Producto producto = buscarProducto(codigo); 
        if (producto != null) {
            listaProducto.remove(producto);
            return true;
        }
        return false;
    }
    
    public ArrayList<Producto> obtenerProductos() {
        return listaProducto;
    }
    public Producto buscarProducto(String codigoProducto){
        Producto producto = null;
                    for (Producto pAux : obtenerProductos()) {
                        if (pAux.getCodigo().equals(codigoProducto)) {
                            producto = pAux;
                            break;
                        }
                    }
        return producto;
    }

    // CRUD para Venta
    public boolean agregarVenta(Venta venta) {
        if (!venta.validarStockDisponible()) {
            return false; // No hay suficiente stock
        }
    
        venta.aplicarVenta(); // Reduce stock y calcula el monto total
        listaVenta.add(venta);
        return true;
    }

    
    public boolean eliminarVenta(String fecha) {
        Venta venta = buscarVenta(fecha);
        if (venta != null) {
            listaVenta.remove(venta);
            return true;
        }
        return false;
    }
    public Venta buscarVenta(String fecha){
        Venta venta = null;
                    for (Venta vAux : obtenerVentas()) {
                        if (vAux.getFechaVenta().equals(fecha)) {
                            venta = vAux;
                            break;
                        }
                    }
        return venta;
    }
    public ArrayList<Venta> obtenerVentas() {
        return listaVenta;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }

    public void setNit(String nit){
        this.nit = nit;
    }
    public String getNit(){
        return nit;
    }

}
