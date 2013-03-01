<%-- 
    Document   : index
    Created on : 06-feb-2013, 0:26:46
    Author     : meme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
    </head>
    <body>
        <h1 align="center"> Welcome </h1>        
        <form  method="POST" action="/Tw03/login">
            <h2 align="center"> 
                <%   String logParameter = request.getParameter("error");
                    if (logParameter == null) {
                    } else {
                        out.print(logParameter);
                    }
                %>
            </h2>

            <table border="0" align="center">
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="txtUserName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="txtPassword" value="" /></td>
                    </tr>
                    <tr align="center">
                        <td></td>
                        <td><input type="submit" value="Sign In" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
