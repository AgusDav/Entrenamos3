<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html><html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale=1,shrink-to-fit=no">
    <%@include file="header.jsp" %>
    <title>Eliminar registro a clase</title>
</head>
<body>
<form action="/Entrenamos.uy/EliminarRegClase" method="post">
    <div id="errorContainer" style="display:none;">
        <div class="alert alert-danger" id="errorText"></div>
    </div>

    <div class="form-group">
        <label for="inputInst">Institucion</label>
        <select name="institucion" class="form-control" id="inputInst">
            <option value="" selected disabled>Selecciona una institución</option>
        </select>
    </div>

    <div class="form-group">
        <label for="inputAct">Actividad deportiva</label>
        <select name="actividad_depor" class="form-control" id="inputAct">
            <option value="" selected disabled>Selecciona una actividad</option>
            <%
                // Obtienes las actividades deportivas del atributo de solicitud
                String[] actividades = (String[]) request.getAttribute("actividades");
                if (actividades != null) {
                    for (String actividad : actividades) {
            %>
            <option value="<%= actividad %>"><%= actividad %></option>
            <%
                    }
                }
            %>
        </select>
    </div>

    <div class="form-group">
        <label for="inputAct">Clase</label>
        <select name="clase" class="form-control" id="inputClase">
            <option value="" selected disabled>Selecciona una Clase</option>
            <!-- Aquí se llenarán las opciones de clases en base a la selección -->
        </select>
    </div>

    <div id="tablaClasesContainer"></div>
    <div id="tablaSociosContainer"></div>

    <div class="mt-3"></div>

    <button type="submit" class="btn btn-primary">Eliminar registro</button>

    <!--SECCION DE SCRIPTS-->

    <script> // Script para desplegar instituciones
        document.addEventListener("DOMContentLoaded", function () {
            fetch('/Entrenamos.uy/AgregarDictadoClase?tipo=institutos')
                .then(response => response.json())
                .then(data => {
                    var institutos = data;
                    var select = document.getElementById('inputInst');
                    institutos.forEach(function (instituto) {
                        var option = document.createElement('option');
                        option.value = instituto;
                        option.text = instituto;
                        select.appendChild(option);
                    });
                });
        });
    </script>

    <script> //Script para desplegar actividades deportivas
        var institucionSelect = document.getElementById("inputInst");
        var actividadSelect = document.getElementById("inputAct");

        institucionSelect.addEventListener("change", function () {
            var institucionSeleccionada = institucionSelect.value;

            // Realizar una solicitud al servidor con la institución seleccionada
            fetch('/Entrenamos.uy/AgregarDictadoClase?tipo=actividades&institucion=' + institucionSeleccionada)
                .then(response => response.json())
                .then(data => {
                    // Limpiar el select de actividades
                    actividadSelect.innerHTML = '';

                    // Agregar las opciones de actividad al select
                    data.forEach(actividad => {
                        var option = document.createElement("option");
                        option.text = actividad;
                        option.value = actividad;
                        actividadSelect.appendChild(option);
                    });
                    actividadSelect.value = data[0];
                    actualizarClases();
                    console.log("Función actualizarTablaClases ejecutada");
                });

            // Llamar a la función para actualizar las clases después de cargar las actividades
        });
    </script>

    <script> //Script para desplegar las clases
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
        var event = new Event("change");
        claseSelect.dispatchEvent(event);
    </script>

    <script> //Script para desplegar y generar las tablas
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
                        var cell4 = newRow.insertCell(3);

                        cell1.innerHTML = data[0];
                        cell2.innerHTML = data[1];
                        cell3.innerHTML = data[2];
                        cell4.innerHTML = data[3];

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

    <script> //Script para actualizar las clases cuando se cambia de institucion y/o actividad
        var elect = document.getElementById("inputAct");
        var clasesContainer = document.getElementById("inputClase");
        var claseSeleccionada = claseSelect.value;

        // Función para actualizar las clases
        function actualizarClases() {institucionSelect = document.getElementById("inputInst");
            var actividadS
            var institucionSeleccionada = institucionSelect.value;
            var actividadSeleccionada = actividadSelect.value;

            // Realizar una solicitud al servidor con la institución y actividad seleccionadas
            fetch('/Entrenamos.uy/RegistroADictadoClase?institucion=' + institucionSeleccionada + '&actividad_depor=' + actividadSeleccionada)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    // Limpiar el select de clases
                    clasesContainer.innerHTML = '';

                    // Agregar las clases al select
                    data.forEach(clase => {
                        var option = document.createElement("option");
                        option.text = clase;
                        option.value = clase;
                        clasesContainer.appendChild(option);
                    });
                    claseSelect.value = data[0];
                });
        }

        // Event listener para cuando cambia la institución o la actividad
        institucionSelect.addEventListener("change", actualizarClases);
        actividadSelect.addEventListener("change", actualizarClases);
        actualizarTablaClases(claseSeleccionada);
    </script>

    <script> //Despliega mensaje de error
        document.addEventListener("DOMContentLoaded", function () {
            var error = "<%= request.getAttribute("error") %>";
            if (error && error !== "null") {
                var errorContainer = document.getElementById("errorContainer");
                var errorText = document.getElementById("errorText");
                errorText.innerText = error;
                errorContainer.style.display = "block";
            }
        });
    </script>
</form>

<%@include file="footer.jsp" %>
</body>
</html>
