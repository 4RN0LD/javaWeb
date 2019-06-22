/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.servlet;

import com.arnoldnorabuena.model.dao.EspecialidadDao;
import com.arnoldnorabuena.model.dao.dao;
import com.arnoldnorabuena.model.dto.Especialidad;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ARNOLD-PC
 */
@WebServlet(name = "EspecialidadServlet", urlPatterns = {"/Especialidad"})
public class EspecialidadServlet extends CoreServlet<Especialidad> {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        dao<Especialidad> dao = EspecialidadDao.getInstance();
        String target = "";
        List<Especialidad> lst;
        Especialidad especialidad;
        Integer id;
        if (accion == null) {
            accion = "";
        }
        switch (accion) {
            case "create":
                target = "view/especialidades/especialidad.jsp?accion=INS";
                break;
            case "GET":
                especialidad = dao.getObjectBy(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("especialidad", especialidad);
                target = "view/especialidades/especialidad.jsp?accion=UPD";
                break;
            case "INS":
                try {
                    // SE INSERTA LA FILA
                    especialidad = getEntityFromRequest(request);
                    dao.insert(especialidad);
                    lst = dao.selectAll();
                    target = "view/especialidades/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "UPD":
                try {
                    especialidad = getEntityFromRequest(request);
                    dao.update(especialidad);
                    lst = dao.selectAll();
                    target = "view/especialidades/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "DEL":
                try {
                    especialidad = getEntityFromRequest(request);
                    dao.delete(especialidad);
                    lst = dao.selectAll();
                    target = "view/especialidades/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                lst = dao.selectAll();
                request.setAttribute("lst", lst);
                target = "view/especialidades/index.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
    }

    @Override
    protected Especialidad getEntityFromRequest(HttpServletRequest request) throws Exception {
        Especialidad especialidad = new Especialidad();
        try {
            especialidad.setIdEspecialidad(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        especialidad.setEspecialidad(request.getParameter("txtEspecialidad"));
        return especialidad;
    }

}
