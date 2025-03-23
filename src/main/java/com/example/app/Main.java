package com.example.app;

import javax.swing.JOptionPane;

import com.example.model.Cliente;
import com.example.model.DetalleVenta;
import com.example.model.Producto;
import com.example.model.Tienda;
import com.example.model.Venta;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Mi Tienda", "123456-7");

        while (true) {
            String opcion = JOptionPane.showInputDialog(
                "Menú:\n" +
                "1. Agregar Producto\n" +
                "2. Agregar Cliente\n" +
                "3. Realizar Venta\n" +
                "4. Mostrar Productos\n" +
                "5. Mostrar Ventas\n" +
                "6. Mostrar Clientes\n"+
                "7. Eliminar\n"+
                "8. Actualizar\n"+
                "9. Salir"
            );

            switch (opcion) {
                //Agregar Producto
                case "1":
                    //pedir datos
                    String nombre = JOptionPane.showInputDialog("Nombre del producto:");
                    String codigo = JOptionPane.showInputDialog("Código del producto:");
                    double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
                    String categoria = JOptionPane.showInputDialog("Categoría del producto:");
                    int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock del producto:"));
                    //Crea nueva instancia
                    Producto nuevoProducto = new Producto(nombre, codigo, precio, categoria, stock);
                    //validacion de codigo existente
                    if (tienda.agregarProducto(nuevoProducto)) {
                        JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Producto con código existente");
                    }
                    break;
                //Agregar Cliente
                case "2":
                    //pedir datos al usuario
                    String nombres = JOptionPane.showInputDialog("Nombre del cliente:");
                    String direccion = JOptionPane.showInputDialog("Dirección del cliente:");
                    String telefono = JOptionPane.showInputDialog("Teléfono del cliente:");
                    String email = JOptionPane.showInputDialog("Email del cliente:");
                    String identificacion = JOptionPane.showInputDialog("Identificación del cliente:");
                    //Crear instancia
                    Cliente nuevoCliente = new Cliente(nombres, direccion, telefono, email, identificacion);
                    //Agregar a la lista y validacion
                    if (tienda.agregarCliente(nuevoCliente)){
                        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error: Identificacion ya registrada.");
                    }
                    break;

                //Realizar Venta    
                case "3":
                String fecha = JOptionPane.showInputDialog("Fecha de la venta (dd/mm/aaaa):");
                String idCliente = JOptionPane.showInputDialog("Identificación del cliente:");
                
                // Buscar cliente
                Cliente cliente = tienda.buscarCliente(idCliente);
                
                if (cliente == null) {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    break;
                }
            
                Venta venta = new Venta(fecha, cliente);
            
                while (true) {
                    String codigoProducto = JOptionPane.showInputDialog("Código del producto (o 'fin' para terminar):");
                    if (codigoProducto.equalsIgnoreCase("fin")) break;
            
                    // Buscar producto
                    Producto producto = tienda.buscarProducto(codigoProducto);
                    
            
                    if (producto == null) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                        continue;
                    }
            
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a comprar:"));
            
                    if (producto.getStock() < cantidad) {
                        JOptionPane.showMessageDialog(null, "Stock insuficiente.");
                        continue;
                    }
            
                    DetalleVenta detalle = new DetalleVenta(producto, cantidad);
                    venta.agregarDetalle(detalle);
                }
            
                if (tienda.agregarVenta(venta)) {
                    JOptionPane.showMessageDialog(null, "Venta realizada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al realizar la venta.");
                }
                break;
                
                //Mostrar Productos
                case "4":
                    StringBuilder productos = new StringBuilder("Productos:\n");
                    for (Producto pAux : tienda.obtenerProductos()) {
                        productos.append(pAux.getCodigo()).append(" - ").append(pAux.getNombre()).append(" - Stock: ").append(pAux.getStock()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, productos.toString());
                    break;
                //Mostrar Ventas
                case "5":
                    StringBuilder ventas = new StringBuilder("Ventas:\n");
                    for (Venta vAux : tienda.obtenerVentas()) {
                        ventas.append("Fecha: ").append(vAux.getFechaVenta()).append(" - Cliente: ").append(vAux.getCliente().getIdentificacion()).append(" - Monto: ").append(vAux.getMontoTotal()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, ventas.toString());
                    break;
                //Mostrar Clientes
                case "6":
                    StringBuilder clientes = new StringBuilder("Clientes:\n");
                    for (Cliente cAux: tienda.obtenerClientes()) {
                        clientes.append("Nombre: ").append(cAux.getNombres()).append(" - Identificacion: ").append(cAux.getIdentificacion()).append(" - Email: ").append(cAux.getEmail()).append(" - Teléfono: ").append(cAux.getTelefono()).append("\n");

                    }
                    JOptionPane.showMessageDialog(null, clientes.toString());
                    break;
                //Eliminar (cliente, producto o venta)    
                case"7":
                String tipoEliminar = JOptionPane.showInputDialog("¿Qué desea eliminar? (producto, cliente, venta):").toLowerCase();
            
                switch (tipoEliminar) {
                    case "producto":
                        String codigoEliminar = JOptionPane.showInputDialog("Ingrese el código del producto a eliminar:");
                        if (tienda.eliminarProducto(codigoEliminar)) {
                            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Producto no encontrado.");
                        }
                        break;
                    
                    case "cliente":
                        String idEliminar = JOptionPane.showInputDialog("Ingrese la identificación del cliente a eliminar:");
                        if (tienda.eliminarCliente(idEliminar)) {
                            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Cliente no encontrado.");
                        }
                        break;
                    
                    case "venta":
                        String fechaEliminar = JOptionPane.showInputDialog("Ingrese la fecha de la venta a eliminar (dd/mm/aaaa):");
                        if (tienda.eliminarVenta(fechaEliminar)) {
                            JOptionPane.showMessageDialog(null, "Venta eliminada correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Venta no encontrada.");
                        }
                        break;
                    
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
                break;
                //Actualizar
                case "8":
            String tipoActualizar = JOptionPane.showInputDialog("¿Qué desea actualizar? (producto, cliente):").toLowerCase();
            
            switch (tipoActualizar) {
                case "producto":
                    String codigoActualizar = JOptionPane.showInputDialog("Ingrese el código del producto a actualizar:");
                    Producto productoActualizar = tienda.buscarProducto(codigoActualizar);
                    
                    if (productoActualizar != null) {
                        productoActualizar.setNombre(JOptionPane.showInputDialog("Nuevo nombre del producto:", productoActualizar.getNombre()));
                        productoActualizar.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio del producto:", productoActualizar.getPrecio())));
                        productoActualizar.setCategoria(JOptionPane.showInputDialog("Nueva categoría del producto:", productoActualizar.getCategoria()));
                        productoActualizar.setStock(Integer.parseInt(JOptionPane.showInputDialog("Nuevo stock del producto:", productoActualizar.getStock())));
                        JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Producto no encontrado.");
                    }
                    break;
                
                case "cliente":
                    String idActualizar = JOptionPane.showInputDialog("Ingrese la identificación del cliente a actualizar:");
                    Cliente clienteActualizar = tienda.buscarCliente(idActualizar);
                    
                    if (clienteActualizar != null) {
                        clienteActualizar.setNombres(JOptionPane.showInputDialog("Nuevo nombre del cliente:", clienteActualizar.getNombres()));
                        clienteActualizar.setDireccion(JOptionPane.showInputDialog("Nueva dirección del cliente:", clienteActualizar.getDireccion()));
                        clienteActualizar.setTelefono(JOptionPane.showInputDialog("Nuevo teléfono del cliente:", clienteActualizar.getTelefono()));
                        clienteActualizar.setEmail(JOptionPane.showInputDialog("Nuevo email del cliente:", clienteActualizar.getEmail()));
                        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Cliente no encontrado.");
                    }
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
            break;

                case "9":
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    System.exit(0);
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }   
    }
    

}


