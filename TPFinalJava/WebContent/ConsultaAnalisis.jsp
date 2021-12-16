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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    
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
 
   <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Agregar Analisis </button>
  
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
    <div class="modal-body">
    <form action="ListaAnalisis" method ="post" class="was-validated">
		<div class="modal-body">
			<div class="form-group">
				<label for="Descripcion">Descripcion</label>
				<input type="text" class="form-control" name="descripcion" required>
			</div>
			<div class="form-group">
			<label for="Precio">Precio</label>
			<input type="text" class="form-control" name="precio" required>
			</div>
		</div>
		
		<div class="modal-footer">
		<button type="submit" class="btn btn-primary" name="accion" value="insertar" data-bs-dismiss="modal">Agregar Analisis</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
       
        </div>
	</form>  
    </div>
   
      
    </div>
  </div>
</div>
		
		
	
	
	
	
	</div>
</div>



  <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>