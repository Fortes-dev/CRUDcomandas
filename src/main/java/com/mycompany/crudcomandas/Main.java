/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudcomandas;

import java.util.ArrayList;
import java.util.Scanner;
import models.Pedido;
import models.Producto;

/**
 *
 * @author medin
 */
public class Main {

    static java.util.Date utilDate = new java.util.Date();
    static long lnMilisegundos = utilDate.getTime();
    private static final java.sql.Date DATE = new java.sql.Date(lnMilisegundos);

    private static PedidoDAO dao = new PedidoDAO();

    private static Producto p = new Producto();
    private static Pedido ped = new Pedido();

    public static void main(String[] args) {
        menu();
    }

    /**
     * Método void para mostrar un menu de manera recursiva
     */
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        System.out.println("\n----------------------------------------\nBienvenido a su app de Comandas");
        System.out.println("\nSeleccione la acción que desea realizar: \n----------------------------------------");
        System.out.println("1. Crear un nuevo pedido.\n"
                + "2. Eliminar un pedido existente.\n"
                + "3. Marcar pedido como recogido.\n"
                + "4. Mostrar pedidos pendientes de hoy.\n"
                + "5. Mostrar carta.\n"
                + "6. Salir.\n----------------------------------------");

        opcion = sc.nextInt();

        switch (opcion) {

            case 1 -> {

                System.out.println("Seleccione el id del producto que desea pedir: ");

                dao.listCarta().forEach(e -> System.out.println(e));

                opcion = sc.nextInt();

                p = dao.seleccionarProducto(opcion);

                System.out.println("\nHa seleccionado el producto:\n" + p);

                String printCrear = (dao.crearPedido(p) > 0) ? "Su pedido se ha realizado con éxito" : "Ha ocurrido un error";

                System.out.println(printCrear);

                menu();

            }

            case 2 -> {

                System.out.println("Selecciona el id del pedido que desea eliminar:\n");

                dao.listPedidos(DATE).forEach(e -> System.out.println(e));

                opcion = sc.nextInt();

                ped = dao.seleccionarPedido(opcion);

                String printEliminar = (dao.eliminarPedido(opcion)) ? "Se ha borrado el pedido: " + ped : "Ha ocurrido un error";

                System.out.println(printEliminar);

                menu();
                
            }

            case 3 -> {

                System.out.println("Selecciona el id del pedido que desea recoger:\n");

                dao.listPedidos(DATE).forEach(e -> System.out.println(e));

                opcion = sc.nextInt();

                ped = dao.seleccionarPedido(opcion);

                String printRecogido = (dao.recogerPedido(opcion)) ? "Se ha recogido el pedido: " + ped : "Ha ocurrido un error";

                System.out.println(printRecogido);

                menu();
                
            }

            case 4 -> {
                
                System.out.println("Estos son los pedidos pendientes de hoy:\n");

                dao.listPedidos(DATE).forEach(e -> System.out.println(e));

                System.out.println("\n----------------------------------------");

                menu();
                
            }

            case 5 -> {

                dao.listCarta().forEach(e -> System.out.println(e));

                System.out.println("\n----------------------------------------");

                menu();
                
            }

            case 6 -> {

                System.out.println("https://github.com/Fortes-dev/CRUDcomandas.git");

                System.exit(0);
                
            }

            default -> {

                System.out.println("INTRODUZCA UN VALOR CORRECTO DEL MENU, POR FAVOR.");

                menu();
                
            }
        }
    }
}
