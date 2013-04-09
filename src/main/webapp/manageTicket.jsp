<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : home
    Created on : 06-feb-2013, 1:00:44
    Author     : meme
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.Ticket"%>
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
            Ticket Management
            <form method="POST" action="/Tw03/logout.jsp">
                <div id="link" style="text-align: right; font-size: 20px">
                    <input type="submit" value="Close Session"/>
                </div>
            </form> 
        </div>

        <div id="leftPanel" style="float: left; width: 170px; height: 100px; border: 1px solid"> 
            <form metod="POST" action="/Tw03/searchTicket">
                <input type="submit" value="Search"/>
            </form>
            <div style="margin: 20px"> 

            </div> 
        </div>
        <form method="POST" action="/Tw03/manageTicket">
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
        </form>
    </body>
</html>
