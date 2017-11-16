
package Controlador;

import dao.TrabajadorDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
        Usuario _sesionUsuario = (Usuario) sesionUsuario.getAttribute("usuario");
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
        Usuario _sesionUsuario = (Usuario) sesionUsuario.getAttribute("usuarioT");
        if (_sesionUsuario == null) {
            //El usuario no a creado la sesion
            if (sesion != null) {
                sesionUsuario.setAttribute("usuarioT", sesion);
                sesionUsuario.setMaxInactiveInterval(20);
                response.sendRedirect("aplicacion.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

        } else {
            response.sendRedirect("aplicacion.jsp");
        }

        if (sesion != null) {

        } else {
            request.setAttribute("Error", "Revisar usuario/ pass");
            RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
            rq.forward(request, response);
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
