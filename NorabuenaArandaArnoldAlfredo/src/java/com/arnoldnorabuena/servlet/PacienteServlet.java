/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.servlet;

import com.arnoldnorabuena.model.dao.PacienteDao;
import com.arnoldnorabuena.model.dao.dao;
import com.arnoldnorabuena.model.dto.Paciente;
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
@WebServlet(name = "PacienteServlet", urlPatterns = {"/Paciente"})
public class PacienteServlet extends CoreServlet<Paciente> {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        dao<Paciente> dao = PacienteDao.getInstance();
        String target = "";
        List<Paciente> lst;
        Paciente paciente;
        if (accion == null) {
            accion = "";
        }
        switch (accion) {
            case "create":
                target = "view/pacientes/paciente.jsp?accion=INS";
                break;
            case "GET":
                paciente = dao.getObjectBy(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("paciente", paciente);
                target = "view/pacientes/paciente.jsp?accion=UPD";
                break;
            case "INS":
                try {
                    // SE INSERTA LA FILA
                    paciente = getEntityFromRequest(request);
                    dao.insert(paciente);
                    lst = dao.selectAll();
                    target = "view/pacientes/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "UPD":
                try {
                    paciente = getEntityFromRequest(request);
                    dao.update(paciente);
                    lst = dao.selectAll();
                    target = "view/pacientes/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "DEL":
                try {
                    paciente = getEntityFromRequest(request);
                    dao.delete(paciente);
                    lst = dao.selectAll();
                    target = "view/pacientes/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                lst = dao.selectAll();
                request.setAttribute("lst", lst);
                target = "view/pacientes/index.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
    }

    @Override
    protected Paciente getEntityFromRequest(HttpServletRequest request) throws Exception {
        Paciente paciente = new Paciente();
        try {
            paciente.setIdPaciente(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            paciente.setNacimiento(dao.simple.parse(request.getParameter("nacimiento")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        paciente.setNombre(request.getParameter("txtPaciente"));
        return paciente;
    }
}
