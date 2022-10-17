package com.emergentes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrincipalServlet", urlPatterns = {"/PrincipalServlet"})
public class PrincipalServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contador = 0;
        Cookie cukis[] = request.getCookies();
        if (cukis != null) {
            for (Cookie c : cukis) {
                if (c.getName().equals("visitas")) {
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }
        contador++;
        Cookie c = new Cookie("visitas", Integer.toString(contador));
        
        //tiempo de vida de la cookie
        c.setMaxAge(5);
        response.addCookie(c);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        out.println("<br>");
        out.println("<h1 align='center'>PRINCIPAL</h1>");     
        out.println("<br>");
        if (contador==1) {
            out.println("<p align='center' style='color:blue;'>BIENVENIDO A NUESTRO SITIO WEB </p>");
        }
        if (contador > 1) {
            out.println("<p align='center' style='color:red;'>GRACIAS POR VISITARNOS NUEVAMENTE </p>");
        }
        out.println("VISITA NUMERO:" + contador);
        out.println("<br>");
        out.println("<a href='index.jsp'>ATRAS</a>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
    }  
}