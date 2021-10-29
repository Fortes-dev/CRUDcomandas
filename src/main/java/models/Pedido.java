/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 
 * @author medin 
 * Modelo de la tabla pedidos
 */
public class Pedido {

    /**
     * Atributos que coinciden con las columnas de la tabla pedidos
     */
    private int id;
    private int product_id;
    private Date fecha;
    private double precio;
    private String pendiente;
    private String recogido;

    /**
     * Constructor vacio
     */
    public Pedido() {
    }

    /**
     * Constructor con parámetros
     *
     * @param id
     * @param product_id
     * @param fecha
     * @param precio
     * @param pendiente
     * @param recogido
     */
    public Pedido(int id, int product_id, Date fecha, double precio, String pendiente, String recogido) {
        this.id = id;
        this.product_id = product_id;
        this.fecha = fecha;
        this.precio = precio;
        this.pendiente = pendiente;
        this.recogido = recogido;
    }

    /**
     * Getters y setters de los campos/atributos
     * @return 
     */
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String isPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String isRecogido() {
        return recogido;
    }

    public void setRecogido(String recogido) {
        this.recogido = recogido;
    }

    /**
     * Método toString para mostar un pedido por consola.
     * @return 
     */
    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", product_id=" + product_id + ", fecha=" + fecha + ", precio=" + precio + ", pendiente=" + pendiente + ", recogido=" + recogido + '}';
    }

}
