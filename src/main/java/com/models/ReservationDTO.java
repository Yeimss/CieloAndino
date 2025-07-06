package com.models;

import java.time.LocalDate;

public class ReservationDTO {
    private int id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int id_usuario;

    public ReservationDTO(int id, LocalDate fechaInicio, LocalDate fechaFin, int id_usuario) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
