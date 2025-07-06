package com.data;

import com.models.ReservationDTO;
import com.models.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
     private final String URL = "jdbc:mysql://localhost:3306/db_cieloandino";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public boolean InsertReservation(ReservationDTO reserva){
        String query = "INSERT INTO tbl_reserva (id_usuario, fecha_ingreso, fecha_salida) VALUES (?,?,?)";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setInt(1, reserva.getId_usuario());            
            stmt.setDate(2, java.sql.Date.valueOf(reserva.getFechaInicio()));
            stmt.setDate(3, java.sql.Date.valueOf(reserva.getFechaFin()));            

            int filasAfectadas = stmt.executeUpdate();            
            return filasAfectadas > 0;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean UpdateReservation(ReservationDTO reserva){
        String query = "UPDATE tbl_reserva SET id_usuario = ?, fecha_ingreso = ?, fecha_salida = ? WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setInt(1, reserva.getId_usuario());            
            stmt.setDate(2, java.sql.Date.valueOf(reserva.getFechaInicio()));
            stmt.setDate(3, java.sql.Date.valueOf(reserva.getFechaFin()));            
            stmt.setInt(4, reserva.getId());
            
            int filasAfectadas = stmt.executeUpdate();            
            return filasAfectadas > 0;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean DeleteReservation(int id){
        String query = "DELETE FROM tbl_reserva WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setInt(1, id);            
            
            int filasAfectadas = stmt.executeUpdate();            
            return filasAfectadas > 0;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public List<ReservationDTO> GetUserReservation(Usuario user){
        String query = "SELECT * FROM tbl_reserva WHERE id_usuario = ?";
        List<ReservationDTO> reservas = new ArrayList();
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ReservationDTO reserva = new ReservationDTO(
                        rs.getInt("id"), 
                        rs.getDate("fecha_ingreso").toLocalDate(), 
                        rs.getDate("fecha_salida").toLocalDate(), 
                        rs.getInt("id_usuario"));
                reservas.add(reserva);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return reservas;
    }
}
