<%@page import="entities.Cliente"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicCliente"%>
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
<title>Lista Clientes</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    
    <%
    	LinkedList<Cliente> listaCli = new LogicCliente().getAll();
    %>
</head>
<body>


<div class="container">

	<div class="container">
	
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand/logo -->
  <a class="navbar-brand" href="#">Logo</a>
  
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Link 1</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 2</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 3</a>
    </li>
  </ul>
</nav>
  
	

		<div class="mt-4 p-5 bg-info text-white rounded">
		  <h1>Lista de Clientes</h1>
		</div>
	</div>
<div>
<br>
</div>

<div class="container">
   <!-- Lista Cliente -->
  <table class="table table-fixed table-condensed">
    <thead class="table-dark">
      <tr>
        <th>Cuit</th>
        <th>Email</th>
        <th>Razon Social</th>
         <th>Telefono</th>
        <th>Editar</th>
        <th>Eliminar</th>
      </tr>
    </thead>
    <tbody>
    <%for(Cliente cli : listaCli ){ %>
      <tr>
        <td><%=cli.getCuit() %> </td>
        <td><%=cli.getEmail() %></td>
        <td><%=cli.getRazonSocial() %></td>
        <td><%=cli.getTelefono() %></td>
        <td><a class="bg-primary text-white" href="ClienteServlet?accion=editar&cuit=<%=cli.getCuit()%>"><button type="button" class="btn btn-primary">Editar</button></a></td>
        <td><a class="bg-danger text-white" href="ClienteServlet?accion=eliminar&cuit=<%=cli.getCuit()%>"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
      </tr>
      <%} %>
     
    </tbody>
  </table>
<button class="btn btn-success" onclick="location.href = 'AgregarCliente.jsp'">Agregar Cliente</button>
  
 

</div>
</div>





<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>




</body>
</html>