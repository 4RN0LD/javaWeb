<%-- 
    Document   : index
    Created on : 21-jun-2019, 2:53:13
    Author     : ARNOLD-PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Especialidades</title>
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
                        <a class="nav-link active" href="#">Especialidad</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Medico">Medico</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Paciente">Paciente</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Cita">Cita</a>
                    </li>
                </ul>
                <a class="btn btn-outline-secondary my-2 my-sm-0" href="#">Login</a>
            </div>
        </nav>
        <div class="jumbotron">
            <h1 class="display-4">Especialidad</h1>
            <hr class="my-4">
            <jsp:useBean id="especialidad" scope="request" class="com.arnoldnorabuena.model.dto.Especialidad"/>
            <form action="Especialidad?accion=${param.accion}" method="post">
                <input type="hidden" name="id" value="${especialidad.idEspecialidad}">
                <div class="form-row my-3">
                    <div class="col-9">
                        <label for="txtEspecialidad">Especialidad</label>
                        <input type="text" class="form-control" name="txtEspecialidad" id="txtEspecialidad" placeholder="Especialidad" value="${especialidad.especialidad}">
                    </div>
                </div>
                <div class="form-row my-3">
                    <div class="col-3">
                        <button type="submit" class="btn btn-success">Guardar</button>
                        <a href="Especialidad" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
