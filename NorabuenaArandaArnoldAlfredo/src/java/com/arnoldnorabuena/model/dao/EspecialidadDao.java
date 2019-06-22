/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dao;

import com.arnoldnorabuena.model.dto.Especialidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class EspecialidadDao implements dao<Especialidad> {

    private static EspecialidadDao especialidadDao;

    private EspecialidadDao() {
    }

    public static EspecialidadDao getInstance() {
        if (especialidadDao == null) {
            especialidadDao = new EspecialidadDao();
        }
        return especialidadDao;
    }

    @Override
    public List<Especialidad> selectAll(Object... parameters) {
        List<Especialidad> especialidades = new ArrayList<>();
        try {
            List<Object> objetos = consulta.getConsulta("SELECT * FROM especialidades");
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(Integer.parseInt(data[0]));
                especialidad.setEspecialidad(data[1]);
                especialidades.add(especialidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return especialidades;
    }

    @Override
    public int insert(Especialidad object) {
        int rowsAffected = -1;
        String SQL = "INSERT INTO `clinica`.`especialidades` (`especialidad`) VALUES (?);";
        consulta.ejecutaQuery(SQL, object.getEspecialidad());
        return rowsAffected;
    }

    @Override
    public int update(Especialidad object) {
        int rowsAffected = -1;
        String SQL = "UPDATE `clinica`.`especialidades` SET `especialidad`=? WHERE (`idespecialidades`=?);";
        consulta.ejecutaQuery(SQL, object.getEspecialidad(), object.getIdEspecialidad());
        return rowsAffected;
    }

    @Override
    public int delete(Especialidad object) {
        int rowsAffected = -1;
        String SQL = "DELETE FROM `clinica`.`especialidades` WHERE (`idespecialidades`=?);";
        consulta.ejecutaQuery(SQL, object.getIdEspecialidad());
        return rowsAffected;
    }

    @Override
    public Especialidad getObjectBy(Object... parameters) {
        Especialidad especialidad = null;
        try {
            List<Object> objetos = consulta.getConsulta("SELECT * FROM especialidades WHERE (`idespecialidades`=?);", Integer.parseInt(parameters[0].toString()));
            for (Object objeto : objetos) {
                String[] data = (String[]) objeto;
                especialidad = new Especialidad();
                especialidad.setIdEspecialidad(Integer.parseInt(data[0]));
                especialidad.setEspecialidad(data[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return especialidad;
    }

}
