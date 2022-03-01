<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
<title>Menú Principal</title>
	<!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
	<% 
		Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
		if(usr == null){
			request.setAttribute("titulo", "Acceso Denegado");
			request.setAttribute("mensage", "Usted no ha iniciado sesión correctamente o carece de los permisos necesarios apra acceder a esta página.");
			request.setAttribute("pagina", "Login");
			request.setAttribute("direccion", "./index.html");
			request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
		}
	%>
</head>
<body style="background-color:rgb(251, 252, 255);">
<div class="fixed-top">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <!-- Brand/logo -->
	  <a class="navbar-brand mb-0 h1" href="MenuPrincipal.jsp">Menú</a>
	  
	  <!-- Links -->
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="ListaPedido.jsp">Pedidos</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="ListaLiquidacion.jsp">Liquidación</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="ListaCliente.jsp">Clientes</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="ConsultaAnalisis.jsp">Análisis</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="ListaSemilla.jsp">Semillas</a>
	    </li>
	    <%if(usr.getTipo() == 0){%>
	    <li class="nav-item">
	      <a class="nav-link" href="ListaUsuario.jsp">Usuarios</a>
	    </li>
	    <%}%>
	  </ul>
	</nav>
	<br>
	<div class="container-fluid">
	<div class="jumbotron text-center" >
	  <h1>Welcome</h1>
	  <br>
	  <br>
	  <p><b>This system was developed by Pacheco Germán and Angel Folguera</b></p>
	</div>
	</div>
	
</div>

</body>
</html>