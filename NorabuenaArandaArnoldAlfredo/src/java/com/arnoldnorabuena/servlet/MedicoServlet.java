/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.servlet;

import com.arnoldnorabuena.model.dao.MedicoDao;
import com.arnoldnorabuena.model.dao.dao;
import com.arnoldnorabuena.model.dto.Especialidad;
import com.arnoldnorabuena.model.dto.Medico;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arnold Alfredo Norabuena Aranda
 * @version 1.0
 */
@WebServlet(name = "MedicoServlet", urlPatterns = {"/Medico"})
public class MedicoServlet extends CoreServlet<Medico> {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        dao<Medico> dao = MedicoDao.getInstance();
        String target = "";
        List<Medico> lst;
        Medico medico;
        if (accion == null) {
            accion = "";
        }
        List<Especialidad> especialidades;
        switch (accion) {
            case "create":
                especialidades = com.arnoldnorabuena.model.dao.EspecialidadDao.getInstance().selectAll();
                request.setAttribute("especialidades", especialidades);
                target = "view/medicos/medico.jsp?accion=INS";
                break;
            case "GET":
                especialidades = com.arnoldnorabuena.model.dao.EspecialidadDao.getInstance().selectAll();
                request.setAttribute("especialidades", especialidades);
                medico = dao.getObjectBy(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("medico", medico);
                target = "view/medicos/medico.jsp?accion=UPD";
                break;
            case "INS":
                try {
                    // SE INSERTA LA FILA
                    medico = getEntityFromRequest(request);
                    dao.insert(medico);
                    lst = dao.selectAll();
                    target = "view/medicos/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "UPD":
                try {
                    medico = getEntityFromRequest(request);
                    dao.update(medico);
                    lst = dao.selectAll();
                    target = "view/medicos/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "DEL":
                try {
                    medico = getEntityFromRequest(request);
                    dao.delete(medico);
                    lst = dao.selectAll();
                    target = "view/medicos/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                lst = dao.selectAll();
                request.setAttribute("lst", lst);
                target = "view/medicos/index.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
    }

    @Override
    protected Medico getEntityFromRequest(HttpServletRequest request) throws Exception {
        Medico medico = new Medico();
        Especialidad especialidad = new Especialidad();
        try {
            medico.setIdMedico(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        medico.setNombre(request.getParameter("txtMedico"));
        try {
            especialidad.setIdEspecialidad(Integer.parseInt(request.getParameter("idEspecialidad")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        medico.setEspecialidad(especialidad);
        return medico;
    }

}
