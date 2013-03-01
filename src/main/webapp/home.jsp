<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : home
    Created on : 06-feb-2013, 1:00:44
    Author     : meme
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Ticket"%>
<%@page import="com.control.ManageTicket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body style="background-color: lightslategrey">

        <!--  width: max-content; height: 50px  -->
        <div id="header" style="text-align: center; font-size: 40px">
            Ticket Mangment
        </div>

        <div id="leftPanel" style="float: left; width: 170px; height: 100px; border: 1px solid"> 
            <input type="submit" value="Search"/>
            
            <input type="text" name="txtSearch" value="" maxlength="20">
            <div style="margin: 20px"> 
                
            </div> 
        </div>


        <div id="centerPanel" style="width: available; height: 100px; border: 1px solid; 
             margin-left: 171px"> 
            <span style="alignment-adjust: central"> 
                Listado de Tickets 
            </span> 
            <table>
                <tr>
                    <td> TicketId </td>
                    <td> Description </td>
                    <td> AssignedTo </td>
                    <td> Status </td>
                    <td> Severity </td>
                    <td> Attachments </td>
                    <td width="80"> OpenDate </td>
                    <td width="80"> CloseDate </td>
                </tr>
                <c:forEach var="current" items="${sessionScope.servletTicketList}">
                    <tr>
                        <td><c:out value="${current.ticketId}"/></td>
                    <td><c:out value="${current.description}"/></td>
                    <td><c:out value="${current.assignedTo}"/></td>
                    <td><c:out value="${current.status}"/></td>
                    <td><c:out value="${current.severity}"/></td>
                    <td><c:out value="${current.attachments}"/></td>
                    <td width="80"><c:out value="${current.openDate}"/></td>
                    <td width="80"><c:out value="${current.closeDate}"/></td>
                </c:forEach>
                </tr>    
            </table>
        </div>





        <!--
        
                <div id="pantalla">
                    <div id="bodyPanel" style=" border: 1px solid black; background-color: lightslategrey">
                        <div style="height: 100px">
                            <span id="panelDerecho" style="height: 50px; border: 1px solid blue" > right </span>
                            <span id="panelDerecho2" style="height: 50px; border: 1px solid blueviolet" > right2 </span>
                        </div>
        
                        <span id="panelCentral"> center </span>
                        <span id="panelIzquierdo"> left </span>
                    </div>
                </div>
        
        
        
        
        
        
                <table width="100%" cellspacing="1px" border="2px">
                    <tr>
                        <td>
                            <table title="Banner" border="0" align="center">
                                <tr>
                                    <td> Pagina principal para la administracion de tickets </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td width="120px" valign="top">
                            <table title="Menu" border="0" width="120px">
                                <tr><td> Search </td></tr>
                                <tr><td>List of Tickets</td></tr>
                                <tr><td>Vínculo 2</td></tr>
                                <tr><td>Vínculo 3</td></tr>
                            </table>
                        </td>
                    </tr>
                    <td>
                        <table title="Contenido" border="0" width="400px">
                            <tr>
                                <td>
                                    Acá va el contenido
                                </td>
                            </tr>
        
                            <tr>
                                <td> TicketId </td>
                                <td> Description </td>
                                <td> AssignedTo </td>
                                <td> Status </td>
                                <td> Severity </td>
                                <td> Attachments </td>
                                <td width="80"> OpenDate </td>
                                <td width="80"> CloseDate </td>
                            </tr>
                            <c:forEach var="current" items="${sessionScope.servletTicketList}">
                                <tr>
                                    <td><c:out value="${current.ticketId}"/></td>
                                <td><c:out value="${current.description}"/></td>
                                <td><c:out value="${current.assignedTo}"/></td>
                                <td><c:out value="${current.status}"/></td>
                                <td><c:out value="${current.severity}"/></td>
                                <td><c:out value="${current.attachments}"/></td>
                                <td width="80"><c:out value="${current.openDate}"/></td>
                                <td width="80"><c:out value="${current.closeDate}"/></td>
                                </tr>
                            </c:forEach>
        <%-- 
<%
    ArrayList<Ticket> TList = new ArrayList<Ticket>();
    HttpSession sesion = request.getSession();
    if (session.getAttribute("servletTicketList") != null) {
        TList = (ArrayList<Ticket>)sesion.getAttribute("servletTicketList");
        Iterator it = TList.iterator();
        if (it.hasNext()) {
            out.println("La lista tiene elementos");
        }
        while (it.hasNext()) {
            Ticket tkt = new Ticket();
            tkt = (Ticket) it.next();
            out.println("<tr>");
            out.println("<td>" + tkt.getTicketId() + "</td>");
            out.println("<td>" + tkt.getDescription() + "</td>");
            out.println("<td>" + tkt.getAssignedTo() + "</td>");
            out.println("<td>" + tkt.getStatus() + "</td>");
            out.println("<td>" + tkt.getSeverity() + "</td>");
            out.println("<td>" + tkt.getAttachments() + "</td>");
            out.println("<td>" + tkt.getOpenDate() + "</td>");
            out.println("<td>" + tkt.getCloseDate() + "</td>");
            out.println("</tr>");
        }
    } else {
        out.println("No hay nada en el sesion");
    }
%>
        --%>
    </table>
</td>
</tr>
</table>
        -->


    </body>
</html>
