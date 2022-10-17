package com.emergentes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        if (op.equals("vaciar")) {
            HttpSession ses = request.getSession();
            ses.invalidate();
            response.sendRedirect("index.jsp");
        }

        if (op.equals("eliminar")) {
            int pos = -1;
            int buscado = -1;
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ArrayList<Pendientes> tareas = (ArrayList<Pendientes>) ses.getAttribute("tareas");
            for (Pendientes p : tareas) {
                pos++;

                if (p.getId() == id) {
                    buscado = pos;
                }
            }
            tareas.remove(buscado);
            response.sendRedirect("index.jsp");
        }

        if (op.equals("check")) {
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ArrayList<Pendientes> tareas = (ArrayList<Pendientes>) ses.getAttribute("tareas");
            for (Pendientes p : tareas) {

                if (p.getId() == id) {
                    if (p.isCompletado()) {
                        p.setCompletado(false);
                    } else {
                        p.setCompletado(true);
                    }

                }
            }
            response.sendRedirect("index.jsp");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tarea = request.getParameter("tarea");

        Pendientes pen = new Pendientes();

        pen.setId(id);
        pen.setTarea(tarea);

        HttpSession ses = request.getSession();
        ArrayList<Pendientes> tareas = (ArrayList<Pendientes>) ses.getAttribute("tareas");

        tareas.add(pen);

        response.sendRedirect("index.jsp");
    }

}
