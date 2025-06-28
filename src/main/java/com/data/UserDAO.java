package com.data;

import com.models.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private final String URL = "jdbc:mysql://localhost:3306/db_cieloandino";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public Usuario validarUsuario(String correo, String Password){
        String query = "SELECT * FROM tbl_usuario WHERE correo = ?";
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)){
            
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                String storedHash = rs.getString("password");
                String encryptPassword = Usuario.sha256(Password);
                if(storedHash.equals(encryptPassword)){
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getInt("id_rol"),
                        rs.getString("correo"),
                        rs.getString("nombre")
                    );
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public Usuario CrearUsuario(String correo, String Password, String nombre){
        String query = "INSERT INTO tbl_usuario (nombre, correo, password, id_rol) VALUES (?,?,?,?)";
        try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            String encryptPassword = Usuario.sha256(Password);
            
            stmt.setString(1, nombre);            
            stmt.setString(2, correo);
            stmt.setString(3, encryptPassword);            
            stmt.setInt(4, 2);

            int filasAfectadas = stmt.executeUpdate();            
            if(filasAfectadas > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    return new Usuario(id, 2, correo, nombre);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
