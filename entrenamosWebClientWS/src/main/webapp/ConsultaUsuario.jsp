<%@ page import="interfaces.IControlador" %>
<%@ page import="interfaces.Fabrica" %>
<%@ page import="datatypes.DtUsuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="header.jsp" %>
    <title>Consulta Usuario</title>
    <style>
        body {
            background-image: url('https://previews.123rf.com/images/theartofphoto/theartofphoto1508/theartofphoto150800200/49636373-hunky-culturista-negro-musculoso-trabajo-en-el-gimnasio-hacer-ejercicio-de-nuevo-en-la-m%C3%A1quina.jpg');
            background-size: cover;
            background-attachment: fixed;
            background-repeat: no-repeat;
            background-position: center center;
            height: 100vh;
            margin: 0;
        }
        body {
            background-color: #363D4B;
            color: #FFFFFF;
            font-family: Arial, sans-serif;
        }

        .container {
            margin: 0 auto;
            max-width: 600px;
            padding: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #FFFFFF;
        }

        input, select {
            width: 100%;
            padding: 5px;
            margin: 5px 0;
            background-color: #FFFFFF;
            color: #000000;
            border: 1px solid #000000;
        }

        input:focus, select:focus {
            filter: none;
            outline: none;
            border: 1px solid #007BFF;
        }
    </style>
</head>
<body>

<div class="container">
    <label for="nickname">Nickname:</label>
    <input type="text" id="nickname" readonly>
</div>

<div class="container">
    <label for="email">E-mail:</label>
    <input type="text" id="email" readonly>
</div>

<div class="container">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" readonly>
</div>

<div class="container">
    <label for="apellido">Apellido:</label>
    <input type="text" id="apellido" readonly>
</div>

<div class="container">
    <label for="fecNac">Fecha de nacimiento:</label>
    <input type="text" id="fecNac" readonly>
</div>

<div class="container">
    <label for="Clases">Clases</label>
    <select name="clase" class="form-control" id="Clases">
        <option value="" selected disabled>Selecciona una clase</option>
        <%
            // Obtienes las clases del atributo de solicitud
            String[] clases = (String[]) request.getAttribute("clases");
            if (clases != null) {
                for (String clase : clases) {
        %>
        <option value="<%= clase %>"><%= clase %></option>
        <%
                }
            }
        %>
    </select>
</div>

<div class="container" id="tablaClasesContainer">
</div>

<div class="container">
    <label for="Actividades">Actividades deportivas</label>
    <select name="Actividades" class="form-control" id="Actividades">
        <option value="" selected disabled>Selecciona una actividad</option>
        <%
            // Obtienes las actividades del atributo de solicitud
            String[] actividades = (String[]) request.getAttribute("actividades"); // Cambiado a "actividades"
            if (actividades != null) {
                for (String act : actividades) {
        %>
        <option value="<%= act %>"><%= act %></option>
        <%
                }
            }
        %>
    </select>
</div>

<div class="container" id="tablaActividadesContainer"> <!-- Asegúrate de que esta línea esté ubicada aquí -->
</div>

<script>
    <%String nick = (String) session.getAttribute("username");%>
    fetch('/Entrenamos.uy/ConsultaUsuario?tipo=usuario&user=' + "<%= nick %>")
        .then(response => response.json())
        .then(data => {
    var nickInput = document.getElementById("nickname");
    var nombreInput = document.getElementById("nombre");
    var apellidoInput = document.getElementById("apellido");
    var emailInput = document.getElementById("email");
    var fechaInput = document.getElementById("fecNac");
    var clasesInput = document.getElementById("Clases")

    nickInput.value = data[0];
    emailInput.value = data[1];
    nombreInput.value = data[2];
    apellidoInput.value = data[3];
    fechaInput.value = data[4];
        });

    var clasesInput = document.getElementById("Clases");
    fetch('/Entrenamos.uy/ConsultaUsuario?tipo=clases&user=' + "<%= nick %>")
        .then(response => response.json())
        .then(data => {
            // Limpiar el select de clases
            clasesInput.innerHTML = '';

            // Iterar sobre las clases obtenidas y agregarlas al select
            for (let i = 0; i < data.length; i++) {
                let clase = data[i];
                console.log("Valor de clase:", clase);
                // Crear un nuevo elemento de opción
                let option = document.createElement("option");
                option.value = clase;
                option.text = clase;
                // Agregar la opción al select
                clasesInput.appendChild(option);
            }

            // Disparar el evento "change" en clasesInput para activar la siguiente actualización si es necesario
            let event = new Event("change");
            clasesInput.dispatchEvent(event);
        });
</script>

<!--Script para mostrar o no las actividades-->
<script>
    <%String nick2 = (String) session.getAttribute("username");%>
    var actividades = document.getElementById("Actividades");
    var userType;
    // Define la función para hacer una solicitud utilizando fetch
    function obtenerTipoUsuario(nickname) {
        fetch('/Entrenamos.uy/ConsultaUsuario?tipo=socio&user=' + "<%= nick2 %>")
            .then(function(response) {
                return response.json(); // Parsea la respuesta JSON
            })
            .then(function(data) {

                if (data) {
                    userType = "S";
                    actividades.style.display = "none";
                }
                else{
                    userType = "P";
                }
            })
            .catch(function(error) {
                console.error('Error:', error);
            });
    }
    // Llama a la función para obtener el tipo de usuario
    obtenerTipoUsuario("<%= nick2 %>");
</script>

<script>
    var claseSelect = document.getElementById("Clases");
    function actualizarTablaClases(claseSeleccionada) {
        console.log("Clase seleccionada: " + claseSeleccionada);
        // Limpiar el contenedor de la tabla
        var tablaContainer = document.getElementById("tablaClasesContainer");
        tablaContainer.innerHTML = '';
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
        }
        if (userType === "P") {
            // Hacer una solicitud al servidor para obtener el arreglo de actividades
            fetch('/Entrenamos.uy/ConsultaUsuario?tipo=actividad&user=' + "<%= nick %>")
                .then(response => response.json())
                .then(data => {
                    // Limpiar el select de actividades
                    var actividadesSelect = document.getElementById("Actividades");
                    actividadesSelect.innerHTML = '';

                    // Agregar las opciones de actividad al select
                    data.forEach(actividad => {
                        var option = document.createElement("option");
                        option.text = actividad;
                        option.value = actividad;
                        actividadesSelect.appendChild(option);
                    });
                });
        }
    }
    var actividadSelect = document.getElementById("Actividades");
    function actualizarTablaActividad(actividadSeleccionada) {
        // Limpiar el contenedor de la tabla
        var tablaContainer2 = document.getElementById("tablaActividadesContainer");
        tablaContainer2.innerHTML = '';
        if (actividadSeleccionada) {
            // Realizar una solicitud al servidor con la actividad seleccionada
            fetch('/Entrenamos.uy/ConsultaActividadDeportiva?tipo=dt&actividad=' + actividadSeleccionada)
                .then(response => response.json())
                .then(data => {
                    // Limpiar el contenedor de la tabla
                    var tablaContainer2 = document.getElementById("tablaActividadesContainer");
                    tablaContainer2.innerHTML = '';
                    // Crear y agregar la tabla al contenedor
                    var table = document.createElement("table");
                    var thead = table.createTHead();
                    var row = thead.insertRow();
                    var headers = ["Descripcion", "Duracion", "Costo", "Fecha de Registro"];

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

                    cell1.innerHTML = data.descripcion;
                    cell2.innerHTML = data.duracion;
                    cell3.innerHTML = data.costo;
                    cell4.innerHTML = data.fecReg;

                    tablaContainer2.appendChild(table);
                });
        }
    }
    claseSelect.addEventListener("change", function () {
        var claseSeleccionada = claseSelect.value;
        // Llamar a la función para actualizar la tabla de clases
        actualizarTablaClases(claseSeleccionada);
    });
    actividadSelect.addEventListener("change", function () {
        var actividadSeleccionada = actividadSelect.value;
        // Llamar a la función para actualizar la tabla de actividades
        actualizarTablaActividad(actividadSeleccionada);
    });
</script>

<%@include file="footer.jsp" %>
</body>
</html>
