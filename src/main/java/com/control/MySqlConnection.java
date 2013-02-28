/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author meme
 */
public final class MySqlConnection {
       
    //Paso 3) Definir variables de objetos Java SQL
    private Connection conn = null;
    
    //Paso 4) Configurar parametros de conexion
    private final String user = "root";
    private final String password = "Mysqlpass1234";
    private final String url = "jdbc:mysql://localhost:3306/ticketdb01";
    

    /////////////////////// BEGIN SINGLETON CLASS /////////////////////////////
    private static final MySqlConnection INSTANCE = new MySqlConnection();

    private MySqlConnection(){
        if (INSTANCE!=null){
            throw new IllegalStateException("Already instantied");
        }
    }
        
    public static MySqlConnection getInstance(){
        return INSTANCE;
    }
    /////////////////////// FINAL SINGLETON CLASS /////////////////////////////    

    // Driver's charger
    //Paso 2) Codigo para cargar el Driver de la Base de Datos
    public static void loadDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        }catch(Exception ex){
            System.out.println("Error Exception loading Driver:" + ex);
        }
    }
    
    //Paso 5) Abrir conexión a BD
    public boolean openConnect (){
        boolean ret=false;
        try{
            conn = DriverManager.getConnection(url, user, password);
            ret=true;
        }catch(Exception ex){
            System.err.println("Error DriverManager.getConnection(): " + ex);
        }    
        return ret;
    }

    // Close connection
    public boolean closeConnect()
    {
        boolean ret=false;
        if (conn != null){
            try {
                conn.close();
                ret=true;
            } catch (SQLException ex) {
                Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
        
    public Connection getConnection(){
        return conn;
    }
    
}