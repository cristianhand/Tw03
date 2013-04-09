<%-- 
    Document   : CloseSession
    Created on : Mar 26, 2013, 4:46:55 PM
    Author     : hand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Do you really want close Session?</h1>
        <form method="POST" action="/Tw03/logout">
            <div>
                <input name="LogoutYes" type="submit" value="Yes">
                <input name="LogoutNo" type="submit" value="No">
            </div>
        </form>
    </body>
</html>
