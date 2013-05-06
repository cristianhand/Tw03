/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.mycompany.secoundwebservice.Ticket;
import com.mycompany.secoundwebservice.TicketWebService_Service;
import com.mycompany.secoundwebservice.UserWebService_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.hibernate.HibernateException;

/**
 *
 * @author hand
 */
public class ServletSearchTicket extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SecoundWebService/UserWebService.wsdl")
    private UserWebService_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SecoundWebService/TicketWebService.wsdl")
    private TicketWebService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String idT = request.getParameter("ticketSearch");

        if (session == null) {
            // Not created yet. Now do so yourself.
            response.sendRedirect("/Tw03/login.jsp");
        } // la sesion no es nula
        else if (idT != null) {
            session.setAttribute("currentTk", null);
            if (!idT.isEmpty()) {
                Ticket tk = null;
                //TicketDAO tD = new TicketDAO();
                int ticketId = 0;
                try {
                    ticketId = Integer.parseInt(request.getParameter("ticketSearch"));
                    try { // Call Web Service Operation
                        com.mycompany.secoundwebservice.TicketWebService port = service.getTicketWebServicePort();
                        // TODO initialize WS operation arguments here
                        //int ticketId = 0;
                        // TODO process result here
                        // Consulta con WS
                        tk = (Ticket) port.searchTicketId(ticketId);
                        //out.println("Result = "+result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        ex.getMessage();
                        System.err.println("Fallo en searchTicketId en TicketwebService");
                        // TODO handle custom exceptions here
                    }
                    // Anterior metodo de consulta sin WS
                    //tk = (Ticket) tD.searchTicketId(ticketId);
                    if (tk != null) {
                        session.setAttribute("currentTk", tk);
                        response.sendRedirect("/Tw03/searchTicket.jsp");
                    } else {
                        response.sendRedirect("/Tw03/searchTicket.jsp" + "?searchTicketError"
                                + "=Ticket not found");
                    }
                } catch (NumberFormatException fe) {
                    fe.printStackTrace();
                    fe.getMessage();
                    response.sendRedirect("/Tw03/searchTicket.jsp" + "?searchTicketError"
                            + "=Incorrect parameter");
                } catch (HibernateException he) {
                    he.printStackTrace();
                    he.getMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
            if (idT.isEmpty()) {
                response.sendRedirect("/Tw03/searchTicket.jsp" + "?searchTicketError"
                        + "=Empty parameter");
            }
        }
        if (idT == null) {
            session.setAttribute("currentTk", null);
            response.sendRedirect("/Tw03/searchTicket.jsp");
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
