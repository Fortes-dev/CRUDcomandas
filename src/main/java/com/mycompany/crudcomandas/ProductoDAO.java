/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudcomandas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author medin
 */
public class ProductoDAO {

    private static Connection con;

    static {
        String url = "jdbc:mysql://localhost:3306/comandas?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static final String LISTA_QUERY = "SELECT * FROM producto";
    static final String GET_ID = "SELECT * FROM producto WHERE id = ?";
    static final String DELETE_ID = "DELETE FROM producto WHERE id = ?";
    static final String INSERT_PRODUCTO = "INSERT INTO producto (nombre, precio) VALUES (?, ?)";
    
}
