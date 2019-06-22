/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dao;

import static com.arnoldnorabuena.model.dao.dao.consulta;
import com.arnoldnorabuena.model.dto.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class PacienteDao implements dao<Paciente> {

    private static PacienteDao pacienteDao;

    private PacienteDao() {
    }

    public static PacienteDao getInstance() {
        if (pacienteDao == null) {
            pacienteDao = new PacienteDao();
        }
        return pacienteDao;
    }

    @Override
    public List<Paciente> selectAll(Object... parameters) {
        List<Paciente> pacientes = new ArrayList<>();
        try {
            List<Object> objetos = consulta.getConsulta("SELECT * FROM pacientes");
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(Integer.parseInt(data[0]));
                paciente.setNombre(data[1]);
                paciente.setNacimiento(simple.parse(data[2]));
                pacientes.add(paciente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public int insert(Paciente object) {
        int rowsAffected = -1;
        String SQL = "INSERT INTO `clinica`.`pacientes` (`nombre`, `nacimiento`) VALUES (?, ?);";
        consulta.ejecutaQuery(SQL, object.getNombre(), object.getNacimiento());
        return rowsAffected;
    }

    @Override
    public int update(Paciente object) {
        int rowsAffected = -1;
        String SQL = "UPDATE `clinica`.`pacientes` SET `nombre`=?, `nacimiento`=? WHERE (`idpacientes`=?);";
        consulta.ejecutaQuery(SQL, object.getNombre(), object.getNacimiento(), object.getIdPaciente());
        return rowsAffected;
    }

    @Override
    public int delete(Paciente object) {
        int rowsAffected = -1;
        String SQL = "DELETE FROM `clinica`.`pacientes` WHERE (`idpacientes`=?);";
        consulta.ejecutaQuery(SQL, object.getIdPaciente());
        return rowsAffected;
    }

    @Override
    public Paciente getObjectBy(Object... parameters) {
        Paciente paciente = null;
        try {
            List<Object> objetos = consulta.getConsulta("SELECT * FROM pacientes WHERE (`idpacientes`=?);", Integer.parseInt(parameters[0].toString()));
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                paciente = new Paciente();
                paciente.setIdPaciente(Integer.parseInt(data[0]));
                paciente.setNombre(data[1]);
                paciente.setNacimiento(simple.parse(data[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paciente;
    }

}
