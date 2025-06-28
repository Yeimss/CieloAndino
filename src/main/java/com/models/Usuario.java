package com.models;

import java.security.MessageDigest;

public class Usuario {
    private int id;
    private String correo;
    private String nombre;
    private String password;
    private int id_rol;
    
    public Usuario(int id, int id_rol, String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;        
        this.id_rol = id_rol;        
        this.id = id;      
    }
    public Usuario(){}

    public int getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    
    public static String sha256(String text){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b:hash){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
