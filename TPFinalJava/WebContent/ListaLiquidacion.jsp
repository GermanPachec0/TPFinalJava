<%@page import="entities.Liquidacion"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicLiquidacion"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <%
    	LinkedList<Liquidacion> listaLiq= new LogicLiquidacion().getAll();
   		Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
   		if(usr == null){
   			request.setAttribute("titulo", "Acceso Denegado");
   			request.setAttribute("mensage", "Usted no ha iniciado sesión correctamente o carece de los permisos necesarios para acceder a esta página.");
   			request.setAttribute("pagina", "Menu Principal");
   			request.setAttribute("direccion", "./MenuPrincipal.jsp");
   			request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
   		}
    %>
</head>
<body>
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
	
	<div class="container">
		<div class="mt-4 p-5 bg-info text-white rounded">
		  <h1>Lista de Liquidaciones</h1>
		</div>
			
	</div>
</div>
</body>
</html>