<%@page import="entities.Semilla"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicSemilla"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="https://getbootstrap.com/favicon.ico">
<title>Lista Semillas</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    
    <%
    	LinkedList<Semilla> listaSem = new LogicSemilla().getAll();
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
	    <li class="nav-item">
	      <a class="nav-link" href="ListaUsuario.jsp">Usuarios</a>
	    </li>
	  </ul>
	</nav>
	<br>

	<div class="container">
	   <!-- Lista Cliente -->
	  <table class="table table-fixed table-condensed">
	    <thead class="table-dark">
	      <tr>
	        <th>Codigo</th>
	        <th>Especie</th>
	        <th>Raza</th>
	        
	        <th>Editar</th>
	        <th>Eliminar</th>
	      </tr>
	    </thead>
	    <tbody>
	    <%for(Semilla sem : listaSem ){ %>
	      <tr>
	        <td><%=sem.getCodSemilla() %> </td>
	        <td><%=sem.getEspecie()%></td>
	        <td><%=sem.getRaza() %></td>
	        <td><a class="bg-primary text-white" href="SemillaServlet?accion=editar&codSemilla=<%=sem.getCodSemilla()%>"><button type="button" class="btn btn-primary">Editar</button></a></td>
	        <td><a class="bg-danger text-white" href="SemillaServlet?accion=eliminar&codSemilla=<%=sem.getCodSemilla()%>"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
	      </tr>
	      <%} %>
	     
	    </tbody>
	  </table>
	<button class="btn btn-success" onclick="location.href = 'AgregarSemilla.jsp'">Agregar Semilla</button>
	
	</div>
</div>

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>