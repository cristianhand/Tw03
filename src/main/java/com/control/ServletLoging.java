/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.persistence.TicketDAO;
import com.persistence.UserDAO;
import com.entity.Ticket;
import com.entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        //response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession(true);

        User user = null;
        UserDAO ud = new UserDAO();
        TicketDAO td = new TicketDAO();
        List<Ticket> tl = null;

        try {
            user = ud.searchUserbyUserName(request.getParameter("txtUserName"), "userName");
            System.err.println("PLPLPLPLPLPL");
            if (user != null && user.getPassword() != null) {
                tl = td.getTicketList();
                sesion.setAttribute("servletTicketList", tl);
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