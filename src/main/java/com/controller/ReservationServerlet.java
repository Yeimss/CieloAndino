package com.controller;

import com.data.ReservationDAO;
import com.models.ReservationDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReservationServerlet", urlPatterns = {"/ReservationServerlet"})
public class ReservationServerlet extends HttpServlet {
    private final ReservationDAO _data = new ReservationDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            Usuario user = (Usuario) session.getAttribute("usuario");
            if (user == null) {
                // No hay sesión iniciada
                response.sendRedirect("login.jsp");
                return;
            }
            
            
            
            List<ReservationDTO> lista = _data.GetUserReservation(user);
            
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate date, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(date.toString()); // formato ISO: yyyy-MM-dd
                }
            })
            .create();
            String listaJson = gson.toJson(lista);
            response.setContentType("application/json");
            response.getWriter().write(listaJson);            
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) {
            // No hay sesión iniciada
            response.sendRedirect("login.jsp");
            return;
        }
        String fecha_ingreso_str = request.getParameter("fecha_ingreso");
        String fecha_salida_str = request.getParameter("fecha_salida");
        
        ReservationDTO reserva = new ReservationDTO(1, LocalDate.parse(fecha_ingreso_str), LocalDate.parse(fecha_salida_str), user.getId());
        boolean insertado = _data.InsertReservation(reserva);
        Map<String, Object> respuesta = new HashMap<>();
        if(insertado){
            respuesta.put("success", true);
            respuesta.put("message", "Insertado correctamente");
        }else{
            respuesta.put("success", false);
            respuesta.put("message", "No se pudo insertar");
        }
        String json = new Gson().toJson(respuesta);
        response.getWriter().write(json);
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) {
            // No hay sesión iniciada
            response.sendRedirect("login.jsp");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        String fecha_ingreso_str = request.getParameter("fecha-ingreso");
        String fecha_salida_str = request.getParameter("fecha_salida");
        
        ReservationDTO reserva = new ReservationDTO(id, LocalDate.parse(fecha_ingreso_str), LocalDate.parse(fecha_salida_str), user.getId());
        boolean actualizado = _data.UpdateReservation(reserva);
        Map<String, Object> respuesta = new HashMap<>();
        if(actualizado){
            respuesta.put("success", true);
            respuesta.put("message", "Actualizado correctamente");
        }else{
            respuesta.put("success", false);
            respuesta.put("message", "No se pudo actualizar");
        }
        String json = new Gson().toJson(respuesta);
        response.getWriter().write(json);
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user == null) {
            // No hay sesión iniciada
            response.sendRedirect("login.jsp");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        
        boolean actualizado = _data.DeleteReservation(id);
        Map<String, Object> respuesta = new HashMap<>();
        if(actualizado){
            respuesta.put("success", true);
            respuesta.put("message", "Eliminado correctamente");
        }else{
            respuesta.put("success", false);
            respuesta.put("message", "No se pudo eliminar");
        }
        String json = new Gson().toJson(respuesta);
        response.getWriter().write(json);
    }
}
