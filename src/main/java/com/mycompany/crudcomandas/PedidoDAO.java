/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudcomandas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Pedido;
import models.Producto;

/**
 *
 * @author medin
 */
public class PedidoDAO {

    private static Connection con;

    java.util.Date utilDate = new java.util.Date();
    long lnMilisegundos = utilDate.getTime();
    java.sql.Date date = new java.sql.Date(lnMilisegundos);

    static {
        String url = "jdbc:mysql://localhost:3306/comandas?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static final String LISTA_QUERY = "SELECT * FROM pedidos WHERE pendiente = 'si' AND fecha = ?";
    static final String LISTA_PRODUCTO = "SELECT * FROM producto";
    static final String GET_PRODUCTO = "SELECT * FROM producto WHERE id = ?";
    static final String GET_PEDIDO = "SELECT * FROM pedidos WHERE id = ?";
    static final String DELETE_ID = "DELETE FROM pedidos WHERE id = ?";
    static final String INSERT_PEDIDO = "INSERT INTO pedidos (product_id, fecha, precio, pendiente, recogido) VALUES (?, ?, ?, ?, ?)";
    static final String MODIFY_PEDIDO = "UPDATE pedidos SET pendiente = ?, recogido = ? WHERE id = ?";

    public Integer crearPedido(Producto p) {
        try ( PreparedStatement ps = con.prepareStatement(INSERT_PEDIDO, RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getId());
            ps.setDate(2, date);
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, "si");
            ps.setString(5, "no");

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                return keys.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Pedido> listPedidos(java.sql.Date date) {

        var listaPedidos = new ArrayList<Pedido>();

        try ( PreparedStatement ps = con.prepareStatement(LISTA_QUERY)) {

            ps.setDate(1, date);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Pedido p = new Pedido();

                p.setId(result.getInt("id"));
                p.setProduct_id(result.getInt("product_id"));
                p.setFecha(result.getDate("fecha"));
                p.setPrecio(result.getDouble("precio"));
                p.setPendiente(result.getString("pendiente"));
                p.setRecogido(result.getString("recogido"));

                listaPedidos.add(p);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaPedidos;
    }

    public ArrayList<Producto> listCarta() {

        var listaCarta = new ArrayList<Producto>();

        try ( Statement st = con.createStatement()) {
            ResultSet result = st.executeQuery(LISTA_PRODUCTO);

            while (result.next()) {
                Producto p = new Producto();

                p.setId(result.getInt("id"));
                p.setNombre(result.getString("nombre"));
                p.setPrecio(result.getDouble("precio"));

                listaCarta.add(p);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaCarta;
    }

    public Producto seleccionarProducto(int id) {

        var producto = new Producto();

        try ( PreparedStatement ps = con.prepareStatement(GET_PRODUCTO)) {

            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                producto.setId(result.getInt("id"));
                producto.setNombre(result.getString("nombre"));
                producto.setPrecio(result.getDouble("precio"));
            }

            return producto;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
        
    public Pedido seleccionarPedido(int id) {

        var pedido = new Pedido();

        try ( PreparedStatement ps = con.prepareStatement(GET_PEDIDO)) {

            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                pedido.setId(result.getInt("id"));
                pedido.setProduct_id(result.getInt("product_id"));
                pedido.setFecha(result.getDate("fecha"));
                pedido.setPrecio(result.getDouble("precio"));
                pedido.setPendiente(result.getString("pendiente"));
                pedido.setRecogido(result.getString("recogido"));
            }

            return pedido;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public Boolean recogerPedido(int id) {
        try ( PreparedStatement ps = con.prepareStatement(MODIFY_PEDIDO, RETURN_GENERATED_KEYS)) {

            ps.setString(1, "no");
            ps.setString(2, "si");
            ps.setInt(3, id);

            

            

            if (ps.executeUpdate()==1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPedido(int id) {

        try ( PreparedStatement ps = con.prepareStatement(DELETE_ID)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
