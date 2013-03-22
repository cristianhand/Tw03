<%-- 
    Document   : SearchTicket
    Created on : Mar 22, 2013, 11:22:07 AM
    Author     : hand
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Esta es la pagina para realizar la busqueda de Tickets</h1>

        <form metod="POST" action="/Tw03/SearchTicket">
            <div>
                <p> Ingresar el numero de ticket a buscar  </p>
                <input type="text" name="ticketSearch" maxlength="20">
            </div>

            <div> 
                <input type="submit" value="Search">
            </div>
        </form>

        <div> 
            <table>
                <tr> 
                    <!-- <td> <c:out value="${sessionScope.currentTk}"/> </td> -->
                </tr>
                <tr>
                    <td><c:out value="${sessionScope.currentTk.ticketId}"/></td>
                <td><c:out value="${sessionScope.currentTk.description}"/></td>
                <td><c:out value="${sessionScope.currentTk.assignedTo}"/></td>
                <td><c:out value="${sessionScope.currentTk.status}"/></td>
                <td><c:out value="${sessionScope.currentTk.severity}"/></td>
                <td><c:out value="${sessionScope.currentTk.attachments}"/></td>
                <td width="80"><c:out value="${sessionScope.currentTk.openDate}"/></td>
                <td width="80"><c:out value="${sessionScope.currentTk.closeDate}"/></td>
                </tr>
            </table>
        </div>
    </body>
</html>
