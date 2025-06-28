package com.controller;

import com.data.UserDAO;
import com.models.Usuario;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/RegisterServerlet")
public class RegisterServlet extends HttpServlet {
    
    private UserDAO _data = new UserDAO(); 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");        
        String nombre = request.getParameter("nombre");

        
        Usuario usuario = _data.CrearUsuario(correo, clave, nombre);
        if(usuario != null){
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("dashboard.jsp");
        }else {
            request.setAttribute("error", "ocurrió un error creando el usuario");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
