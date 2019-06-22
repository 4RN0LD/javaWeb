<%-- 
    Document   : index
    Created on : 21-jun-2019, 2:53:13
    Author     : ARNOLD-PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fecha" uri="com.arnoldnorabuena.tlds" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Citas</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Menu</a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Inicio<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="Especialidad">Especialidad</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Medico">Medico</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Paciente">Paciente</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Cita</a>
                    </li>
                </ul>
                <a class="btn btn-outline-secondary my-2 my-sm-0" href="#">Login</a>
            </div>
        </nav>
        <div class="jumbotron">
            <h1 class="display-4">Cita</h1>
            <hr class="my-4">
            <jsp:useBean id="cita" scope="request" class="com.arnoldnorabuena.model.dto.Cita"/>
            <form action="Cita?accion=${param.accion}" method="post">
                <input type="hidden" name="id" value="${cita.idCita}">
                <div class="form-row my-3">
                    <div class="col-2">
                        <label for="dia">D&iacute;a</label>
                        <input type="date" class="form-control" name="dia" id="dia" placeholder="dd/mm/aaaa" value="<fecha:YMD fecha="${cita.diaHora}"/>">
                    </div>
                    <div class="col-2">
                        <label for="hora">Hora</label>
                        <input type="time" class="form-control" name="hora" id="hora" placeholder="hh:mm" value="<fecha:hora hora="${cita.diaHora}"/>">
                    </div>
                    <div class="col-4">
                        <label for="idMedico">Medico</label>
                        <select class="custom-select" name="idMedico">
                            <c:forEach var="medico" items="${medicos}">
                                <option value="${medico.idMedico}" <c:if test="${medico.idMedico == cita.medico.idMedico}">selected</c:if>>${medico.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label for="idPaciente">Paciente</label>
                        <select class="custom-select" name="idPaciente">
                            <c:forEach var="paciente" items="${pacientes}">
                                <option value="${paciente.idPaciente}" <c:if test="${paciente.idPaciente == cita.paciente.idPaciente}">selected</c:if>>${paciente.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row my-3">
                    <div class="col-3">
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="Cita" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
