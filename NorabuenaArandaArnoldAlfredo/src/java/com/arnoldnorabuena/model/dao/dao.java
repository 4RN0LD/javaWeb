/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.model.dao;

import com.arnoldnorabuena.model.core.Consulta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
public interface dao<T> {

    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat complejo = new SimpleDateFormat("yyyy-MM-dd H:mm");
    Consulta consulta = new Consulta();

    public List<T> selectAll(Object... parameters);

    public int insert(T object);

    public int update(T object);

    public int delete(T object);

    public T getObjectBy(Object... parameters);
}
