package Controlador;

import dao.TrabajadorDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Trabajador;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUsuario = request.getSession();
        Trabajador _sesionUsuario = (Trabajador) sesionUsuario.getAttribute("usuario");
        if (_sesionUsuario != null) {
            sesionUsuario.invalidate();
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        //Validaciones que deben ser realizadas
        Trabajador datosUsuario = new Trabajador();
        datosUsuario.setUsuarioT(login);
        datosUsuario.setPasswordT(pass);

        //Validaciones
        TrabajadorDAO userDao = null;
        try {
            userDao = new TrabajadorDAO();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Trabajador sesion = userDao.validar(datosUsuario);
        HttpSession sesionUsuario = request.getSession();
        Trabajador _sesionUsuario = null;
        try {
            _sesionUsuario = (Trabajador) sesionUsuario.getAttribute("usuario");
        } catch (Exception ex) {
            response.sendRedirect("index.html");
        }

        if (_sesionUsuario == null) {
            //El usuario no a creado la sesion
            if (sesion != null) {
                sesionUsuario.setAttribute("usuario", sesion);
                sesionUsuario.setMaxInactiveInterval(1800);
                response.sendRedirect("menu.jsp");
            } else {
                response.sendRedirect("index.html");
            }

        } else {
            response.sendRedirect("menu.jsp");
        }

        if (sesion != null) {

        } else {
            //request.setAttribute("Error", "Revisar usuario/ pass");
            // RequestDispatcher rq =  request.getRequestDispatcher("index.html");
            //rq.forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
