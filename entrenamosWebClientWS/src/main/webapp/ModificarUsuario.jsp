<%@ page import="main.publicadores.DtUsuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width", initial-scale=1,shrink-to-fit=no">
  <%@include file="header.jsp" %>
  <title>Modificar Usuario</title>

  <style>
    .form-group {
      margin-bottom: 10px;
    }
  </style>

</head>
<body>
      <form action="/Entrenamos.uy/ModificarUsuario" method="post">
        <fieldset>
          <legend>Datos del usuario</legend>
          <div class="form-group">
            <label for="nickname">Nickname:</label>
            <input type="text" id="nickname" readonly>
          </div>

          <div class="form-group">
            <label for="email">E-mail:</label>
            <input type="text" id="email" readonly>
          </div>

          <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" readonly>
          </div>

          <div class="form-group">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" readonly>
          </div>

          <div class="form-group">
            <label for="fecNac">Fecha de nacimiento:</label>
            <input type="text" id="fecNac" readonly>
          </div>
        </fieldset>

        <fieldset>
          <legend>Datos a modificar</legend>
            <div class="form-group">
              <label for="inputNombre">Nuevo Nombre</label>
              <input type="text"
                     name="nuevoNombre"
                     class="form-control"
                     id="inputNombre"
                     placeholder="Nombre de usuario"
                     value="">
            </div>

            <div class="form-group">
              <label for="inputApellido">Nuevo Apellido</label>
              <input type="text"
                     name="nuevoApellido"
                     class="form-control"
                     id="inputApellido"
                     placeholder="Apellido de usuario"
                     value="">
            </div>

            <div class="form-group">
              <label for="inputFechaNacim">Nueva fecha de nacimiento</label>
              <input type="date"
                     name="nuevaFecNac"
                     class="form-control"
                     id="inputFechaNacim"
                     placeholder="Fecha de nacimiento de usuario">
            </div>
        </fieldset>

        <div class="mt-3"></div>
        <button type="submit" class="btn btn-primary">Modificar Usuario</button>

        <script>
          <%String nick = (String) session.getAttribute("username");
          DtUsuario user = null;try {
user = obtenerUsuario(nick);} catch (ServiceException e) {
    throw new RuntimeException(e);
}%>
            var user = {
            nombre: "<%= user.getNombre() %>",
            apellido: "<%= user.getApellido() %>",
            email: "<%= user.getEmail() %>",
            fecNac: "<%= user.getFecNac() %>"
        </script>

        <script>
          <%String nick2 = (String) session.getAttribute("username");%>
          var actividades = document.getElementById("Actividades");
          var userType;
          // Define la función para hacer una solicitud utilizando fetch
          fetch('/Entrenamos.uy/ConsultaUsuario?tipo=usuario&user=' + "<%= nick %>")
                    .then(function(response) {
                      return response.json(); // Parsea la respuesta JSON
                    })
                    .then(function(data) {
                      var nickInput = document.getElementById("nickname");
                      var nombreInput = document.getElementById("nombre");
                      var apellidoInput = document.getElementById("apellido");
                      var emailInput = document.getElementById("email");
                      var fechaInput = document.getElementById("fecNac");

                      nickInput.value = data[0];
                      emailInput.value = data[1];
                      nombreInput.value = data[2];
                      apellidoInput.value = data[3];
                      fechaInput.value = data[4];
                    })
                    .catch(function(error) {
                      console.error('Error:', error);
                    });
          // Llama a la función para obtener el tipo de usuario
        </script>

        <%@include file="footer.jsp" %>
      </form>
</body>
</html>

<%!
  private DtUsuario obtenerUsuario(String nick) throws RemoteException, ServiceException {
    ControladorPublishService cps = new ControladorPublishServiceLocator();
    ControladorPublish port = cps.getControladorPublishPort();
    return port.obtenerUsuario(nick);
  }
%>

