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
public class Especialidad {

    private Integer idEspecialidad;
    private String especialidad;
    private static Especialidad especialidades;

    /**
     * @return the idEspecialidad
     */
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Especialidades{" + "idEspecialidad=" + idEspecialidad + ", especialidad=" + especialidad + '}';
    }

}
