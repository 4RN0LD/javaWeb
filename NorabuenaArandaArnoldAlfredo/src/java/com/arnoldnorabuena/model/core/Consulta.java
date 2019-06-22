/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public class Consulta {

    private Connection conexion;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private List<Object> rows;
    private String[] Cavecera;

    public Consulta() {
        try {
            conexion = Conexion.getInstanceConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Se ingresa como parametros la cosulta y los parametros como consulta
     * preparada y devuelve una coleccion de elementos de tipo String[] en tipo
     * Object, donde cada Array de String[] es una fila del ResultSet. por
     * Ejemplo: { String[] data; List<Object> objetos = getConsulta("SELECT *
     * FROM employees WHERE id = ?",1); for(Object objeto : objetos) { data =
     * (String[]) objeto; } }
     *
     * @param Parametros
     * @return
     * @throws SQLException
     */
    public List<Object> getConsulta(Object... Parametros) throws SQLException {
        boolean isMultiParameter = Parametros.length > 1;
        long start = System.currentTimeMillis();
        rows = new ArrayList<>();
        if (isMultiParameter) {
            preparedStatement = conexion.prepareStatement(Parametros[0].toString());
            for (int i = 1; i < Parametros.length; i++) {
                switch (Parametros[i].getClass().getName()) {
                    case "java.lang.Integer":
                        preparedStatement.setInt(i, (int) Parametros[i]);
                        break;
                    case "java.lang.String":
                        preparedStatement.setString(i, (String) Parametros[i]);
                        break;
                    case "java.util.Date":
                        Date date = (Date) Parametros[i];
                        if (date.getHours() == 0 && date.getMinutes() == 0 & date.getSeconds() == 0) {
                            preparedStatement.setDate(i, new java.sql.Date(date.getTime()));
                        } else {
                            preparedStatement.setTimestamp(i, new java.sql.Timestamp(date.getTime()));
                        }
                        break;
                    case "java.lang.Double":
                        preparedStatement.setDouble(i, (double) Parametros[i]);
                        break;
                }
            }
            resultSet = preparedStatement.executeQuery();
        } else {
            //Esta tarda mas en ejecutarse
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(Parametros[0].toString());
        }
        long end = System.currentTimeMillis();
        System.out.println("Se tardo " + (end - start) + " milisegundos");
        int columnas = resultSet.getMetaData().getColumnCount();
        Cavecera = new String[columnas];
        for (int i = 0; i < columnas; i++) {
            Cavecera[i] = resultSet.getMetaData().getColumnLabel(i + 1);
        }
        String[] datos;
        while (resultSet.next()) {
            datos = new String[columnas];
            for (int i = 0; i < columnas; i++) {
                datos[i] = resultSet.getString(i + 1);
            }
            rows.add(datos);
        }
        resultSet.close();
        if (isMultiParameter) {
            preparedStatement.close();
        } else {
            statement.close();
        }
        return rows;
    }

    /**
     *
     * @param Parametros
     * @return
     */
    public Integer ejecutaQuery(Object... Parametros) {
        try {
            preparedStatement = conexion.prepareStatement(Parametros[0].toString());
            for (int i = 1; i < Parametros.length; i++) {
                switch (Parametros[i].getClass().getName()) {
                    case "java.lang.Integer":
                        preparedStatement.setInt(i, (int) Parametros[i]);
                        break;
                    case "java.lang.String":
                        preparedStatement.setString(i, (String) Parametros[i]);
                        break;
                    case "java.util.Date":
                        Date date = (Date) Parametros[i];
                        if (date.getHours() == 0 && date.getMinutes() == 0 & date.getSeconds() == 0) {
                            preparedStatement.setDate(i, new java.sql.Date(date.getTime()));
                        } else {
                            preparedStatement.setTimestamp(i, new java.sql.Timestamp(date.getTime()));
                        }
                        break;
                    case "java.lang.Double":
                        preparedStatement.setDouble(i, (double) Parametros[i]);
                        break;
                }
            }
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected;
        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public String[] getCavecera() {
        return Cavecera;
    }
}
