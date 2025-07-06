/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.data;

import com.models.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    
    public UserDAOTest() {
    }

    @Test
    void ValidarUsuario(){
        UserDAO dao = new UserDAO(); 
        Usuario user = dao.validarUsuario("admin@example.com", "admin1234");
        assertNotNull(user, "No debería retonar null");
        assertEquals(user.getId_rol(), 1, "El rol debería ser 1 (admin)");
    }
    /*@Test
    void ValidarUsuarioIncorrecto(){
        UserDAO dao = new UserDAO(); 
        Usuario user = dao.validarUsuario("admin@ejemplo.com", "nanananaEstaContraseñaNoExiste");
        assertNull(user, "Debería retonar null");
        assertEquals(user.getId_rol(), 1, "El rol debería ser 1 (admin)");
    }*/
    @Test
    void RegistrarUsuario(){
        UserDAO dao = new UserDAO(); 
        Usuario user = new Usuario();
        user = dao.CrearUsuario(user.generateRandomEmail(5), "test1234", "test");
        assertNotNull(user, "No debería retonar null");
        assertEquals(user.getId_rol(), 2, "El rol no debería ser 1 (admin)");
    }
    /*@Test
    void RegistrarUsuarioIncorrecto(){
        UserDAO dao = new UserDAO(); 
        Usuario user = dao.validarUsuario("admin@ejemplo.com", "nanananaEstaContraseñaNoExiste");
        assertNotNull(user, "No debería retonar null");
        assertEquals(user.getId_rol(), 1, "El rol debería ser 1 (admin)");
    }*/

}
