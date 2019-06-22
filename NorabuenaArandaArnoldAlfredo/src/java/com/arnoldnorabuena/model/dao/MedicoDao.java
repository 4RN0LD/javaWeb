/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dao;

import static com.arnoldnorabuena.model.dao.dao.consulta;
import com.arnoldnorabuena.model.dto.Especialidad;
import com.arnoldnorabuena.model.dto.Medico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class MedicoDao implements dao<Medico> {

    private static MedicoDao medicoDao;

    private MedicoDao() {
    }

    public static MedicoDao getInstance() {
        if (medicoDao == null) {
            medicoDao = new MedicoDao();
        }
        return medicoDao;
    }

    @Override
    public List<Medico> selectAll(Object... parameters) {
        List<Medico> medicos = new ArrayList<>();
        try {
            List<Object> objetos = consulta.getConsulta("SELECT\n"
                    + "medicos.idmedicos,\n"
                    + "medicos.nombre,\n"
                    + "especialidades.idespecialidades,\n"
                    + "especialidades.especialidad\n"
                    + "FROM medicos\n"
                    + "INNER JOIN especialidades ON medicos.idespecialidades = especialidades.idespecialidades");
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                Medico medico = new Medico();
                Especialidad especialidad = new Especialidad();
                medico.setIdMedico(Integer.parseInt(data[0]));
                medico.setNombre(data[1]);
                especialidad.setIdEspecialidad(Integer.parseInt(data[2]));
                especialidad.setEspecialidad(data[3]);
                medico.setEspecialidad(especialidad);
                medicos.add(medico);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicos;
    }

    @Override
    public int insert(Medico object) {
        int rowsAffected = -1;
        String SQL = "INSERT INTO `clinica`.`medicos` (`idespecialidades`, `nombre`) VALUES (?,?);";
        consulta.ejecutaQuery(SQL, object.getEspecialidad().getIdEspecialidad(), object.getNombre());
        return rowsAffected;
    }

    @Override
    public int update(Medico object) {
        int rowsAffected = -1;
        String SQL = "UPDATE `clinica`.`medicos` SET `idespecialidades`=?, `nombre`=? WHERE (`idmedicos`=?);";
        consulta.ejecutaQuery(SQL, object.getEspecialidad().getIdEspecialidad(), object.getNombre(), object.getIdMedico());
        return rowsAffected;
    }

    @Override
    public int delete(Medico object) {
        int rowsAffected = -1;
        String SQL = "DELETE FROM `clinica`.`medicos` WHERE (`idmedicos`=?);";
        consulta.ejecutaQuery(SQL, object.getIdMedico());
        return rowsAffected;
    }

    @Override
    public Medico getObjectBy(Object... parameters) {
        Medico medico = null;
        try {
            List<Object> objetos = consulta.getConsulta("SELECT\n"
                    + "medicos.idmedicos,\n"
                    + "medicos.nombre,\n"
                    + "especialidades.idespecialidades,\n"
                    + "especialidades.especialidad\n"
                    + "FROM medicos\n"
                    + "INNER JOIN especialidades ON medicos.idespecialidades = especialidades.idespecialidades "
                    + "WHERE (medicos.idmedicos=?);",parameters[0].toString());
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                medico = new Medico();
                Especialidad especialidad = new Especialidad();
                medico.setIdMedico(Integer.parseInt(data[0]));
                medico.setNombre(data[1]);
                especialidad.setIdEspecialidad(Integer.parseInt(data[2]));
                especialidad.setEspecialidad(data[3]);
                medico.setEspecialidad(especialidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medico;
    }

}
