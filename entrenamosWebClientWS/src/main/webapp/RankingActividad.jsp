<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
    <%@include file="header.jsp" %>
    <title>Ranking de Actividades</title>
    <style>

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

    </style>
</head>
<body>
<div class="form-group">
    <label for="inputActividad">Actividades</label>
    <select name="clase" class="form-control" id="inputActividad">
        <option value="" selected disabled>Ranking ↴</option>
    </select>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        fetch('/Entrenamos.uy/RankingActividad?')
            .then(response => response.json())
            .then(data => {
                var clases = data;
                var select = document.getElementById('inputActividad');
                clases.forEach(function (clase) {
                    var option = document.createElement('option');
                    option.value = clase;
                    option.text = clase;
                    select.appendChild(option);
                });
            });
    });
</script>
<div id="tablaActividadContainer"></div>
<script>
    var actividadSelect = document.getElementById("inputActividad");

    function actualizarTablaActividades(actividadSeleccionada) {
        // Limpiar el contenedor de la tabla
        var tablaContainer = document.getElementById("tablaActividadContainer");
        tablaContainer.innerHTML = '';
        if (actividadSeleccionada) {
            // Realizar una solicitud al servidor con la clase seleccionada
            fetch('/Entrenamos.uy/ConsultaActividadDeportiva?tipo=dt&actividad=' + actividadSeleccionada)
                .then(response => response.json())
                .then(data => {
                    // Limpiar el contenedor de la tabla
                    var tablaContainer = document.getElementById("tablaActividadContainer");
                    tablaContainer.innerHTML = '';

                    // Crear y agregar la tabla al contenedor
                    var table = document.createElement("table");
                    var thead = table.createTHead();
                    var row = thead.insertRow();
                    var headers = ["Nombre", "Costo", "Descripcion"];

                    for (var i = 0; i < headers.length; i++) {
                        var th = document.createElement("th");
                        th.innerHTML = headers[i];
                        row.appendChild(th);
                    }

                    var tbody = table.createTBody();
                    var newRow = tbody.insertRow();

                    var cell1 = newRow.insertCell(0);
                    var cell2 = newRow.insertCell(1);
                    var cell3 = newRow.insertCell(2);

                    cell1.innerHTML = data.nombre;
                    cell2.innerHTML = data.costo;
                    cell3.innerHTML = data.descripcion;

                    tablaContainer.appendChild(table);
                });
        }
    }

    actividadSelect.addEventListener("change", function () {
        var actividadSeleccionada = actividadSelect.value;
        // Llamar a la función para actualizar la tabla
        actualizarTablaActividades(actividadSeleccionada);
    });
</script>

<%@include file="footer.jsp" %>
</body>