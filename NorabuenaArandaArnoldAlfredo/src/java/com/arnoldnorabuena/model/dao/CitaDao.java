/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dao;

import static com.arnoldnorabuena.model.dao.dao.consulta;
import com.arnoldnorabuena.model.dto.Cita;
import com.arnoldnorabuena.model.dto.Medico;
import com.arnoldnorabuena.model.dto.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class CitaDao implements dao<Cita> {

    private static CitaDao citaDao;

    private CitaDao() {
    }

    public static CitaDao getInstance() {
        if (citaDao == null) {
            citaDao = new CitaDao();
        }
        return citaDao;
    }

    /**
     * No ingresar parametros para obtener todas las filas ingresar "PACIENTE" o
     * "MEDICO" en caso de obtener la lista de pacientes por el que corresponte,
     * seguido del ID del paciente o medico. Ejemplo: selectAll("PACIENTE", 4);
     *
     * @param parameters
     * @return
     */
    @Override
    public List<Cita> selectAll(Object... parameters) {
        List<Cita> citas = new ArrayList<>();
        try {
            List<Object> objetos;
            switch (parameters[0].toString()) {
                case "PACIENTE":
                    objetos = consulta.getConsulta("SELECT\n"
                            + "citas.idcitas,\n"
                            + "medicos.idmedicos,\n"
                            + "medicos.nombre,\n"
                            + "pacientes.idpacientes,\n"
                            + "pacientes.nombre,\n"
                            + "pacientes.nacimiento,\n"
                            + "citas.diahora\n"
                            + "FROM citas\n"
                            + "JOIN medicos ON citas.idmedicos = medicos.idmedicos\n"
                            + "JOIN pacientes ON citas.idpacientes = pacientes.idpacientes\n"
                            + "WHERE pacientes.idpacientes = ?", Integer.parseInt(parameters[1].toString()));
                    break;
                case "MEDICO":
                    objetos = consulta.getConsulta("SELECT\n"
                            + "citas.idcitas,\n"
                            + "medicos.idmedicos,\n"
                            + "medicos.nombre,\n"
                            + "pacientes.idpacientes,\n"
                            + "pacientes.nombre,\n"
                            + "pacientes.nacimiento,\n"
                            + "citas.diahora\n"
                            + "FROM citas\n"
                            + "JOIN medicos ON citas.idmedicos = medicos.idmedicos\n"
                            + "JOIN pacientes ON citas.idpacientes = pacientes.idpacientes\n"
                            + "WHERE medicos.idmedicos = ?", Integer.parseInt(parameters[1].toString()));
                    break;
                default:
                    objetos = consulta.getConsulta("SELECT\n"
                            + "citas.idcitas,\n"
                            + "medicos.idmedicos,\n"
                            + "medicos.nombre,\n"
                            + "pacientes.idpacientes,\n"
                            + "pacientes.nombre,\n"
                            + "pacientes.nacimiento,\n"
                            + "citas.diahora\n"
                            + "FROM citas\n"
                            + "JOIN medicos ON citas.idmedicos = medicos.idmedicos\n"
                            + "JOIN pacientes ON citas.idpacientes = pacientes.idpacientes");
                    break;
            }

            for (Object objeto : objetos) {
                Cita cita = new Cita();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                String[] data = (String[]) objeto;
                cita.setIdCita(Integer.parseInt(data[0]));
                medico.setIdMedico(Integer.parseInt(data[1]));
                medico.setNombre(data[2].toString());
                paciente.setIdPaciente(Integer.parseInt(data[3]));
                paciente.setNombre(data[4].toString());
                paciente.setNacimiento(simple.parse(data[5].toString()));
                cita.setMedico(medico);
                cita.setPaciente(paciente);
                cita.setDiaHora(complejo.parse(data[6]));
                citas.add(cita);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public int insert(Cita object) {
        int rowsAffected = -1;
        String SQL = "INSERT INTO `clinica`.`citas` (`idmedicos`, `idpacientes`, `diahora`) VALUES (?,?,?);";
        consulta.ejecutaQuery(SQL, object.getMedico().getIdMedico(), object.getPaciente().getIdPaciente(), object.getDiaHora());
        return rowsAffected;
    }

    @Override
    public int update(Cita object) {
        int rowsAffected = -1;
        String SQL = "UPDATE `clinica`.`citas` SET `idmedicos`=?, `idpacientes`=?, `diahora`=? WHERE (`idcitas`=?);";
        consulta.ejecutaQuery(SQL, object.getMedico().getIdMedico(), object.getPaciente().getIdPaciente(), object.getDiaHora(), object.getIdCita());
        return rowsAffected;
    }

    @Override
    public int delete(Cita object) {
        int rowsAffected = -1;
        String SQL = "DELETE FROM `clinica`.`citas` WHERE (`idcitas`=?);";
        consulta.ejecutaQuery(SQL, object.getIdCita());
        return rowsAffected;
    }

    @Override
    public Cita getObjectBy(Object... parameters) {
        Cita cita = null;
        try {
            List<Object> objetos = consulta.getConsulta("SELECT\n"
                    + "citas.idcitas,\n"
                    + "medicos.idmedicos,\n"
                    + "medicos.nombre,\n"
                    + "pacientes.idpacientes,\n"
                    + "pacientes.nombre,\n"
                    + "pacientes.nacimiento,\n"
                    + "citas.diahora\n"
                    + "FROM citas\n"
                    + "JOIN medicos ON citas.idmedicos = medicos.idmedicos\n"
                    + "JOIN pacientes ON citas.idpacientes = pacientes.idpacientes\n"
                    + "WHERE citas.idcitas = ?", Integer.parseInt(parameters[0].toString()));
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                cita = new Cita();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                cita.setIdCita(Integer.parseInt(data[0]));
                medico.setIdMedico(Integer.parseInt(data[1]));
                medico.setNombre(data[2].toString());
                paciente.setIdPaciente(Integer.parseInt(data[3]));
                paciente.setNombre(data[4].toString());
                paciente.setNacimiento(simple.parse(data[5].toString()));
                cita.setMedico(medico);
                cita.setPaciente(paciente);
                cita.setDiaHora(complejo.parse(data[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cita;
    }

}
