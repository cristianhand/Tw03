/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.persistence.Tuple; Esta es la TUPLA Descargada por Maven
import utilities.TupleClass;

/**
 *
 * @author meme
 */
public class LoginAcces {
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    MySqlConnection mysqlConnection = MySqlConnection.getInstance();
    
    
    public TupleClass getPasswordId (String username) throws Exception{
        TupleClass t = new TupleClass("", 0);
        String query = "select u.Password, u.UserId"
                    + " from user u"
                    + " where u.Username = ?";
        try {
            MySqlConnection.loadDriver();
            mysqlConnection.openConnect();
            pstmt = mysqlConnection.getConnection().prepareStatement(query);
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            rs.next();
            //System.out.println("Esta es la password " + rs.getString(1));
            //System.out.println("Esta es el Id " + rs.getInt(2));
            t.setStr(rs.getString("password"));
            t.setI(rs.getInt("UserId"));
            //System.out.println(t.toString());
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            mysqlConnection.closeConnect();
        }
        return t;
    }
    
}
