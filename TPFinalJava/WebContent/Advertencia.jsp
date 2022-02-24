<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Advertencia</title>
	<link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <%
    	String mensage = (String)request.getAttribute("mensage");
    	String titulo = (String)request.getAttribute("titulo");
    	String pagina = (String)request.getAttribute("pagina");
    	String direccion = (String)request.getAttribute("direccion");
    %>
</head>
<body>
<div class="alert alert-danger" role="alert">
  <h4 class="alert-heading"><%=titulo%>></h4>
  <p><%=mensage%></p>
  <hr>
  <p class="mb-0"><a href="<%=direccion%>>" class="alert-link">Volver a la página de <%=pagina%></a></p>
</div>
</body>
</html>