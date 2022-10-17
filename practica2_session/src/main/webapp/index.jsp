<%@page import="com.emergentes.Pendientes"%>
<%@page import="java.util.ArrayList"%>
<%
    if (session.getAttribute("tareas") == null) {
        ArrayList<Pendientes> lisaux = new ArrayList<Pendientes>();
        session.setAttribute("tareas", lisaux);
    }
    ArrayList<Pendientes> tareas = (ArrayList<Pendientes>) session.getAttribute("tareas");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">Registrar Tareas</h1>
        <form action="MainServlet" method="POST">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="" size="2"></td>
                </tr>           
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value=""></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Agregar"></td>
                </tr>
            </table>               
        </form>
        
        
        <a href="MainServlet?op=vaciar">Vaciar Tareas</a>

        <h2 align="center">Tareas Pendientes</h2>
        <table border="1" align="center">
            <tr>
                <th>ID</th>
                <th>TAREAS</th>
                <th>COMPLETADO</th>
                <th></th>
            </tr>   
            <%
                if (tareas != null) {
                    for (Pendientes p : tareas) {
            %>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getTarea()%></td>                 
                <td><a href="MainServlet?op=check&id=<%= p.getId() %>"><input type="checkbox" name="" <%= (p.isCompletado())? "checked":"" %>/>check</a> </td>      
                <td><a href="MainServlet?op=eliminar&id=<%= p.getId()%>">X</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>