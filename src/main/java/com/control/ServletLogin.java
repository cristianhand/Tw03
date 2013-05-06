/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.mycompany.secoundwebservice.Ticket;
import com.mycompany.secoundwebservice.TicketWebService_Service;
import com.mycompany.secoundwebservice.User;
import com.mycompany.secoundwebservice.UserWebService_Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import webservices.WebService01_Service;

/**
 *
 * @author meme
 */
//@WebServlet(name = "Log", urlPatterns = {"/Log"})
public class ServletLogin extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8084/WebServices/WebService01.wsdl")
    private WebService01_Service service;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SecoundWebService/TicketWebService.wsdl")
    private TicketWebService_Service service_ticket;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SecoundWebService/UserWebService.wsdl")
    private UserWebService_Service service_user;

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
        List<Ticket> tl = new ArrayList<Ticket>();
        String requestUserName = "";
        String requestUserPassword = "";
        String userTableColumName = "";

        try {
            // get user password
            requestUserName = request.getParameter("txtUserName");
            requestUserPassword = request.getParameter("txtPassword");
            userTableColumName = "userName";

            // JSP check
            if (!requestUserName.isEmpty() && !requestUserPassword.isEmpty()) {
                // get user WS
                try { // Call Web Service Operation
                    com.mycompany.secoundwebservice.UserWebService port = service_user.getUserWebServicePort();
                    user = (User) port.searchUserbyUserName(requestUserName, userTableColumName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    ex.getMessage();
                    System.err.println("Fallo en searchUserbyUserName en UserwebService");
                }

                // old call at getuser method
                //    user = ud.searchUserbyUserName(requestUserName, "userName");
                if (user != null && user.getPassword() != null
                        && requestUserPassword.equals(user.getPassword())) {

                    // get ticket list Web Service
                    try { // Call Web Service Operation
                        com.mycompany.secoundwebservice.TicketWebService port = service_ticket.getTicketWebServicePort();
                        // TODO process result here
                        java.util.List<java.lang.Object> result = port.getTicketList();
                        for (Object temp : result) {
                            Ticket t = (Ticket) temp;
                            tl.add(t); // tl cargado y listo
                        }
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                        ex.getMessage();
                        ex.printStackTrace();
                        System.out.println("Nunca olvides la madre de todas las excepciones");
                    }

                    // viejo metodo sin ws
                    //tl = td.getTicketList();
                    sesion.setAttribute("servletTicketList", tl);
                    response.sendRedirect("/Tw03/manageTicket");
                } else {
                    if (user.getPassword().equals("")) {
                        request.setAttribute("error", "LoginFail");
                        response.sendRedirect("/Tw03/login.jsp" + "?error=Incorrect "
                                + "username, please try again");
                        // Utilizando Formularios jspf
                        // <%@ include file="WEB-INF/jspf/formularioLogin.jspf" %>
                    } else {
                        response.sendRedirect("/Tw03/login.jsp" + "?error=Incorrect "
                                + "password, please try again");
                    }
                }
            } else if (requestUserName.isEmpty()) {
                response.sendRedirect("/Tw03/login.jsp" + "?error= empty "
                        + "username, please try again");
            } else {
                response.sendRedirect("/Tw03/login.jsp" + "?error= empty "
                        + "password, please try again");
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