/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import persistence.GenericDAO;
import persistence.TicketDAO;
import entity.Ticket;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Tuple;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.TupleClass; // SIN USO ACTUAL

/**
 *
 * @author meme
 */
//@WebServlet(name = "Log", urlPatterns = {"/Log"})
public class ServletLoging extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.err.println("Estamos en ServletLogin");
        System.out.println("Estamos en ServletLogin");
        LoginAcces loginAcces = new LoginAcces();
        ManageTicket manageTicket = new ManageTicket();
        HttpSession sesion = request.getSession(true);
        ArrayList<Ticket> tList = new ArrayList<Ticket>();
        TupleClass tPassId = new TupleClass();
        TupleClass t = new TupleClass();

        //Ticket ticket = new Ticket();
        //TicketDAO td = new TicketDAO();
        //List<Ticket> ticketList = null;
        
        User user = null;
        GenericDAO gDao = new GenericDAO();
        List<Object> objectList = null;

        try {
            // falta un DAO para usuarios
            user = (User) gDao.getObjectByString(request.getParameter("txtUserName"), "userName", User.class);
            System.out.println(user.userToString());
            if (user != null && user.getPassword() != null) {
                objectList = gDao.getObjectList("Ticket");
                sesion.setAttribute("servletTicketList", objectList);
                response.sendRedirect("/Tw03/home.jsp");
            } else {
                if (user.getPassword().equals("")) {
                    request.setAttribute("error", "LoginFail");
                    response.sendRedirect("/Tw03/login.jsp" + "?error=Incorrect "
                            + "username, please try again");
                    // Utilizando Formularios jspf
                    // <%@ include file="WEB-INF/jspf/formulariologin.jspf" %>
                } else {
                    response.sendRedirect("/Tw03/login.jsp" + "?error=Incorrect "
                            + "password, please try again");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }





    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

