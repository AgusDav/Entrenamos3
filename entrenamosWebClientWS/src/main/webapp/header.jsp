<%@ page import="main.publicadores.ControladorPublishServiceLocator" %>
<%@ page import="main.publicadores.ControladorPublishService" %>
<%@ page import="main.publicadores.ControladorPublish" %>
<%@ page import="javax.xml.rpc.ServiceException" %>
<%@ page import="java.rmi.RemoteException" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	  integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	  crossorigin="anonymous">
<html>
<style>
	/*Color del fondo*/
	body {
		background-color: #e3e8e8;
	}
</style>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
				<ul class="navbar-nav">

					<li class="nav-item button">
						<button class="btn btn-dark" onclick="window.location.href='index.jsp'">
							Inicio
						</button>
					</li>

					<li class="nav-item dropdown">
						<button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							Altas
						</button>
						<ul class="dropdown-menu dropdown-menu-dark">
							<li><a id="AgregarInstitucion" class="dropdown-item" href="AgregarInstuticion.jsp">Institucion Deportiva (Tarea 1)</a></li>
							<li><a id="AgregarUser" class="dropdown-item" href="AgregarUsuario.jsp">Profesor/Socio (Tarea 1)</a></li>
							<li><a id="AgregarActDepor" class="dropdown-item" href="AgregarActividadDeportiva.jsp">Actividad Deportiva (Tarea 1)</a></li>
							<li><a id="AgregarDicClase" class="dropdown-item" href="AgregarDictadoClase.jsp">Dictado de Clase</a></li>
							<li><a id="RegADicClase" class="dropdown-item" href="RegistroADictadoClase.jsp">Registro a dictado de Clase</a></li>
						</ul>
					</li>

					<li class="nav-item dropdown">
						<button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							Modificar
						</button>
						<ul class="dropdown-menu dropdown-menu-dark">
							<li><a id="ModifUser" class="dropdown-item" href="ModificarUsuario.jsp">Usuario (opcional)</a></li>
						</ul>
					</li>

					<li class="nav-item dropdown">
					<button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
						Consultas
					</button>
					<ul class="dropdown-menu dropdown-menu-dark">
						<li><a id="ConsultaActDepor" class="dropdown-item" href="ConsultaActividadDeportiva.jsp">Actividad Deportiva</a></li>
						<li><a id="ConsultaUser" class="dropdown-item" href="ConsultaUsuario.jsp">Usuario</a></li>
						<li><a id="ConsultaDicClase" class="dropdown-item" href="ConsultaDictadoClase.jsp">Dictado de Clase</a></li>
						<li><a id="RankingClase" class="dropdown-item" href="RankingClase.jsp">Ranking de Clases</a></li>
						<li><a id="RankingActividad" class="dropdown-item" href="RankingActividad.jsp">Ranking de Actividades</a></li>
						<li><a id="Easteregg" class="dropdown-item" href="easteregg.jsp">Easteregg</a></li>
					</ul>
					</li>

					<li class="nav-item button">
						<button class="btn btn-dark" onclick="window.location.href='Login.jsp'">
							Cerrar Sesion
						</button>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<script>
		// Supongamos que tienes una variable userType que indica el tipo de usuario
		var userType; // Esto es lo que obtienes de la JSP
		<%	session = request.getSession();
			String nickname = (String) session.getAttribute("username");
			try {
				if(esSocio(nickname)){
					%>
						userType = "S";
					<%
				}else{
					%>
					userType = "P";
					<%
				}} catch (ServiceException e) {
					throw new RuntimeException(e);
				}
						%>

		// Función para mostrar u ocultar elementos del menú según el tipo de usuario
		function toggleMenuItems() {
			var AgregarActDepor = document.getElementById("AgregarActDepor");
			var AgregarInstitucion = document.getElementById("AgregarInstitucion");
			var AgregarUser = document.getElementById("AgregarUser");
			var AgregarDicClase = document.getElementById("AgregarDicClase");
			var ModifUser = document.getElementById("ModifUser");
			var ConsultaActDepor = document.getElementById("ConsultaActDepor");
			var ConsultaUser = document.getElementById("ConsultaUser");
			var ConsultaDicClase = document.getElementById("ConsultaDicClase");
			var Belligol = document.getElementById("Belligol");
			var AgregarRegADicClase = document.getElementById("RegADicClase");
			var RankingClase = document.getElementById("RankingClase");
			var RankingActividad = document.getElementById("RankingActividad");

			AgregarActDepor.style.display = "block";		//No hay que implementarla para esta tarea. Aparecen en los dos
			AgregarInstitucion.style.display = "block";		//No hay que implementarla para esta tarea. Aparecen en los dos
			AgregarUser.style.display = "block";			//No hay que implementarla para esta tarea. Aparecen en los dos

			if (userType === "P") {
				// Mostrar elementos específicos para profesores
				AgregarRegADicClase.style.display = "none";
				AgregarDicClase.style.display = "block";
				ModifUser.style.display = "block";
				ConsultaActDepor.style.display = "block";
				ConsultaUser.style.display = "block";
				ConsultaDicClase.style.display = "block";
				RankingClase.style.display = "block";
				RankingActividad.style.display = "block";
				Belligol.style.display = "block";
			} else if (userType === "S") {
				// Mostrar elementos específicos para socios
				RankingClase.style.display = "none";
				RankingActividad.style.display = "none";
				AgregarRegADicClase.style.display = "block";
				AgregarDicClase.style.display = "none";
				ModifUser.style.display = "block";
				ConsultaActDepor.style.display = "block";
				ConsultaUser.style.display = "block";
				ConsultaDicClase.style.display = "none";
				Belligol.style.display = "none";
			}
		}

		// Llamar a la función para inicializar el menú
		toggleMenuItems();
	</script>
</body>
</html>
<%!
	private Boolean esSocio(String nick) throws ServiceException, RemoteException {
		ControladorPublishService cps = new ControladorPublishServiceLocator();
		ControladorPublish port = cps.getControladorPublishPort();
		return port.esSocio(nick);
	}
%>