<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,shrink-to-fit=no">
    <%@include file="header.jsp" %>
    <title>Ranking de Clases</title>
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
    <label for="inputClase">Clases</label>
    <select name="clase" class="form-control" id="inputClase">
        <option value="" selected disabled>Ranking ↴</option>
    </select>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        fetch('/Entrenamos.uy/RankingClase?tipo=clases')
            .then(response => response.json())
            .then(data => {
                var clases = data;
                var select = document.getElementById('inputClase');
                clases.forEach(function (clase) {
                    var option = document.createElement('option');
                    option.value = clase;
                    option.text = clase;
                    select.appendChild(option);
                });
            });
    });
</script>

<div id="tablaClasesContainer"></div>
<div id="tablaSociosContainer"></div>


<script>
    var claseSelect = document.getElementById("inputClase");

    function actualizarTablaClases(claseSeleccionada) {
        // Limpiar el contenedor de la tabla
        var tablaContainer = document.getElementById("tablaClasesContainer");
        tablaContainer.innerHTML = '';
        var tablaContainer2 = document.getElementById("tablaSociosContainer");
        tablaContainer2.innerHTML = '';
        if (claseSeleccionada) {
            // Realizar una solicitud al servidor con la clase seleccionada
            fetch('/Entrenamos.uy/ConsultaDictadoClase?tipo=dtclase&clase=' + claseSeleccionada)
                .then(response => response.json())
                .then(data => {
                    // Limpiar el contenedor de la tabla
                    var tablaContainer = document.getElementById("tablaClasesContainer");
                    tablaContainer.innerHTML = '';

                    // Crear y agregar la tabla al contenedor
                    var table = document.createElement("table");
                    var thead = table.createTHead();
                    var row = thead.insertRow();
                    var headers = ["Nombre", "URL", "Fecha", "Hora"];

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
                    //var cell4 = newRow.insertCell(3);

                    cell1.innerHTML = data.nombre;
                    cell2.innerHTML = data.url;
                    cell3.innerHTML = data.fecha;
                    //cell4.innerHTML = data.horaInicio;

                    tablaContainer.appendChild(table);
                });
            // Realizar una solicitud al servidor con la clase seleccionada
            fetch('/Entrenamos.uy/ConsultaDictadoClase?tipo=dtsocio&clase=' + claseSeleccionada)
                .then(response => response.json())
                .then(data => {
                    // Limpiar el contenedor de la tabla

                    // Crear y agregar la tabla al contenedor
                    var table = document.createElement("table");
                    var thead = table.createTHead();
                    var row = thead.insertRow();
                    var headers = ["Nombre de Socios"];

                    for (var i = 0; i < headers.length; i++) {
                        var th = document.createElement("th");
                        th.innerHTML = headers[i];
                        row.appendChild(th);
                    }

                    var tbody = table.createTBody();

                    data.forEach(function (data) {
                        var newRow = tbody.insertRow();
                        var cell1 = newRow.insertCell(0);

                        // Llena las celdas con los datos del socio
                        cell1.innerHTML = data;
                    });

                    tablaContainer2.appendChild(table);
                });
        }
    }

    claseSelect.addEventListener("change", function () {
        var claseSeleccionada = claseSelect.value;
        // Llamar a la función para actualizar la tabla
        actualizarTablaClases(claseSeleccionada);
    });
</script>


<%@include file="footer.jsp" %>
</body>
</html>
