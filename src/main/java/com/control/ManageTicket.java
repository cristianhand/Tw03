/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import Entity.Ticket;
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author meme
 */
public class ManageTicket {
    
    MySqlConnection mysqlConnection = MySqlConnection.getInstance();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    //List <Ticket> TicketList = new List <Ticket>();
    
    
    ArrayList<Ticket> TicketList = new ArrayList<Ticket>();
    public ArrayList getTicketList(int userid){
        
        String query = "select t.TicketId, t.Description, t.AssignedTo, "
                + "t.Status, t.Severity, t.Attachments, t.OpenDate, t.CloseDate"
                    + " from ticket t, user u"
                    + " where  t.AssignedTo = u.UserId and  u.UserId = ? ";
        System.err.println(query);
        try {
            mysqlConnection.openConnect();
            pstmt = mysqlConnection.getConnection().prepareStatement(query);
            pstmt.setInt(1,userid);
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()){
                    Ticket ticket = new Ticket();
                    ticket.setTicketId(rs.getInt("TicketId"));
                    if (rs.getString("Description")!=null)
                        ticket.setDescription(rs.getString("Description"));
                    ticket.setAssignedTo(rs.getInt("AssignedTo"));
                    ticket.setStatus(rs.getInt("Status"));
                    ticket.setSeverity(rs.getInt("Severity"));
                    ticket.setAttachments(rs.getInt("Attachments"));
                    ticket.setOpenDate(rs.getDate("OpenDate"));
                    ticket.setCloseDate(rs.getDate("CloseDate"));
                    TicketList.add(ticket);
            }
        }
        // madre de todas las excepciones!
        catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage(); // Aca me va a decir cual es la excepcion
        }
        finally {
            mysqlConnection.closeConnect();
        }
        return TicketList;
    }
    
    public int getId (String usermane){
        int ret = 0;
        String query = "select t.TicketId, t.Description, t.AssignedTo, "
                + "t.Status, t.Severity, t.Attachments, t.OpenDate, t.CloseDate"
                    + " from ticket t, user u"
                    + " where  t.AssignedTo = u.UserId and  u.UserId = ? ";
        
        
        return ret;
    }
    
    
}
