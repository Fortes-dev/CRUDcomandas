/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author medin
 * Modelo de la tabla producto
 */
public class Producto {
    
    /**
     * Atributos que coinciden con las columnas de la tabla producto
     */
    private int id;
    private String nombre;
    private double precio;

    /**
     * Constructor vacio
     */
    public Producto() {
    }

    /**
     * Constructor con parametros
     * @param id
     * @param nombre
     * @param precio 
     */
    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método toString para mostar un producto por consola.
     * @return 
     */
    @Override
    public String toString() {
        return "id["+id+"] "+nombre+" - precio: "+precio;
    }
    
    
    
}
