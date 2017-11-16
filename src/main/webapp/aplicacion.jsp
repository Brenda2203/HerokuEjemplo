<%@page import="model.Trabajador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    Trabajador usuario = (Trabajador)sesion.getAttribute("usuarioT");
    if( usuario == null){
      response.sendRedirect("index.jsp");
    }else{ 

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sesion</h1>
        <a href="Login">Cerrar Sesion</>
    </body>
</html>
<%}%>
