<%@page import="entities.Analisis"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicAnalisis"%>
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

<title>Consulta de Analisis</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/boostrap.min.css" rel="stylesheet">
    
    <%
    	LinkedList<Analisis> la = new LogicAnalisis().getAll();
    %>
</head>


<body>

<div class ="container">
	<div class = "fixed-top">
		<div class ="row">
			<div class = "col - 12">
				<nav class ="navbar navbar-expand-sm bg-dark navbar-dark">
					<ul class= "navbar-nav">
						 <li class="nav-item"><a href="#" class ="nav-link">link 1</a>
						 <li class="nav-item"><a href="#" class ="nav-link">link 1</a>
						 <li class="nav-item"><a href="#" class ="nav-link">link 1</a>
						 <li class="nav-item"><a href="#" class ="nav-link">link 1</a>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	
<div class="container">

<div class="mt-4 p-5 bg-info text-white rounded">
  <h1>Lista de Analisis</h1>
</div>

<div>
<br>
</div>
  
  <table class="table table-fixed table-condensed">
    <thead class="table-dark">
      <tr>
        <th>Codigo</th>
        <th>Descripcion</th>
        <th>Precio</th>
        <th>Editar</th>
        <th>Eliminar</th>
      </tr>
    </thead>
    <tbody>
    <%for(Analisis an : la ){ %>
      <tr>
        <td><%= an.getCodAnalisis()%></td>
        <td><%=an.getDescripcion() %></td>
        <td><%=an.getPrecio() %></td>
        <td><a>Editar</a></td>
        <td><a>Eliminar</a></td>
      </tr>
      <%} %>
     
    </tbody>
  </table>
  

  
  <button type="button" class="btn btn-success">Editar</button>
  <button type="button" class="btn btn-danger">Eliminar</button>
  <button type="button" class ="btn btn-primary" data-toggle ="modal" data-target="#modal1">Agregar</button>
  
  
  <!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>





</div>



  
  

</div>

</div>





  

</body>
</html>