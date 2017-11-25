/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import dao.QuejaDAO;
import dao.TrabajadorDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Queja;
import model.Trabajador;

/**
 *
 * @author Brenda
 */
public class QuejaC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("delete")) {
            QuejaDAO q = null;
            try {
                q = new QuejaDAO();
            } catch (URISyntaxException ex) {
                Logger.getLogger(QuejaC.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Queja> quejas = new ArrayList();
            try {
                quejas = q.getAllQuejas();
            } catch (SQLException ex) {
                Logger.getLogger(HorarioM.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("quejas", quejas);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/QuejaD.jsp");
            rd.forward(request, response);
        }

        TrabajadorDAO t = null;
        try {
            t = new TrabajadorDAO();
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuejaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Trabajador> trabajadores = new ArrayList();

        QuejaDAO q = null;
        try {
            q = new QuejaDAO();
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuejaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Queja> quejas = new ArrayList();
        try {
            trabajadores = t.getAllTrabajadores();
            quejas = q.getAllQuejas();
        } catch (SQLException ex) {
            Logger.getLogger(HorarioM.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (action.equals("create")) {
            request.setAttribute("trabajadores", trabajadores);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/QuejaC.jsp");
            rd.forward(request, response);
        }
        if (action.equals("update")) {
            request.setAttribute("quejas", quejas);
            request.setAttribute("trabajadores", trabajadores);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/QuejaU.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idU = Integer.parseInt(request.getParameter("idU"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        try {
            QuejaDAO q = new QuejaDAO();
            Queja quejaCrear = new Queja();
            quejaCrear.setIdUsuario(idU);
            quejaCrear.setNombre(nombre);
            quejaCrear.setDescripcion(descripcion);
            q.addQueja(quejaCrear);
        } catch (URISyntaxException | SQLException ex) {
            Logger.getLogger(QuejaC.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Trabajador> trabajadores = new ArrayList();

        TrabajadorDAO t = null;
        try {
            t = new TrabajadorDAO();
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuejaC.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            trabajadores = t.getAllTrabajadores();
        } catch (SQLException ex) {
            Logger.getLogger(QuejaC.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("trabajadores", trabajadores);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/QuejaC.jsp");
        rd.forward(request, response);
    }

}
