/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnoldnorabuena.servlet;

import com.arnoldnorabuena.model.dao.CitaDao;
import com.arnoldnorabuena.model.dao.dao;
import com.arnoldnorabuena.model.dto.Cita;
import com.arnoldnorabuena.model.dto.Medico;
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
@WebServlet(name = "CitaServlet", urlPatterns = {"/Cita"})
public class CitaServlet extends CoreServlet<Cita> {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        dao<Cita> dao = CitaDao.getInstance();
        String target = "";
        List<Cita> lst;
        Cita cita;
        if (accion == null) {
            accion = "";
        }
        List<Medico> medicos;
        List<Paciente> pacientes;
        switch (accion) {
            case "create":
                medicos = com.arnoldnorabuena.model.dao.MedicoDao.getInstance().selectAll();
                pacientes = com.arnoldnorabuena.model.dao.PacienteDao.getInstance().selectAll();
                request.setAttribute("medicos", medicos);
                request.setAttribute("pacientes", pacientes);
                target = "view/citas/cita.jsp?accion=INS";
                break;
            case "GET":
                medicos = com.arnoldnorabuena.model.dao.MedicoDao.getInstance().selectAll();
                pacientes = com.arnoldnorabuena.model.dao.PacienteDao.getInstance().selectAll();
                request.setAttribute("medicos", medicos);
                request.setAttribute("pacientes", pacientes);
                cita = dao.getObjectBy(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("cita", cita);
                target = "view/citas/cita.jsp?accion=UPD";
                break;
            case "INS":
                try {
                    // SE INSERTA LA FILA
                    cita = getEntityFromRequest(request);
                    dao.insert(cita);
                    lst = dao.selectAll("ALL");
                    target = "view/citas/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "UPD":
                try {
                    cita = getEntityFromRequest(request);
                    dao.update(cita);
                    lst = dao.selectAll("ALL");
                    target = "view/citas/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "DEL":
                try {
                    cita = getEntityFromRequest(request);
                    dao.delete(cita);
                    lst = dao.selectAll("ALL");
                    target = "view/citas/index.jsp";
                    request.setAttribute("lst", lst);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                lst = dao.selectAll("ALL");
                request.setAttribute("lst", lst);
                target = "view/citas/index.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request, response);
    }

    @Override
    protected Cita getEntityFromRequest(HttpServletRequest request) throws Exception {
        Cita cita = new Cita();
        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        try {
            cita.setIdCita(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e.getMessage() + "primero");
        }
        try {
            medico.setIdMedico(Integer.parseInt(request.getParameter("idMedico")));
            paciente.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
            cita.setDiaHora(dao.complejo.parse(request.getParameter("dia") + " " + request.getParameter("hora")));
        } catch (Exception e) {
            System.out.println(e.getMessage() + "segundo");
        }
        cita.setMedico(medico);
        cita.setPaciente(paciente);
        return cita;
    }

}
