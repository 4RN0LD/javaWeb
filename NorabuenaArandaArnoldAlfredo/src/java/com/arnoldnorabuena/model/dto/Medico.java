/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dto;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class Medico {

    private Integer idMedico;
    private Especialidad especialidad;
    private String nombre;
    private static Medico medico;

    /**
     * @return the idMedico
     */
    public Integer getIdMedico() {
        return idMedico;
    }

    /**
     * @param idMedico the idMedico to set
     */
    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * @return the especialida
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialida to set
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Medico{" + "idMedico=" + idMedico + ", especialidad=" + especialidad + ", nombre=" + nombre + '}';
    }

}
