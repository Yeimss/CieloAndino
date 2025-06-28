package com.controller;

import com.data.UserDAO;
import com.models.Usuario;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDAO _data = new UserDAO(); 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        Usuario usuario = _data.validarUsuario(correo, clave);
        if(usuario != null){
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("dashboard.jsp");
        }else {
            request.setAttribute("error", "Credenciales incorrectas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
