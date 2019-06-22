/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dto;

import java.util.Date;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class Cita {

    private Integer idCita;
    private Paciente paciente;
    private Medico medico;
    private Date diaHora;
    private static Cita cita;

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(Date diaHora) {
        this.diaHora = diaHora;
    }

    public static Cita getCita() {
        return cita;
    }

    public static void setCita(Cita cita) {
        Cita.cita = cita;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", paciente=" + paciente + ", medico=" + medico + ", diaHota=" + diaHora + '}';
    }

}
