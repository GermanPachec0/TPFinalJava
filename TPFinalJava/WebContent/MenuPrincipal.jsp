<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menú Principal</title>
	<link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/bootstrap.min.css" rel="stylesheet">
	<link href="styles/signin.css" rel="stylesheet">
</head>
<body>
<div class="container">
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
      <a class="nav-link" href="ListaUsuario">Usuarios</a>
    </li>
  </ul>
</nav>
</div>
</body>
</html>