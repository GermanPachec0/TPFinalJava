<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Analisis"%>
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


<title>Editar Analisis</title>

  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    
</head>
<body style="background-color:rgb(251, 252, 255);">
<div class="container-xl">

<div class="mt-4 p-5 bg-info text-white rounded">
  <h1>Editar Analisis</h1>
</div>

<form action="ListaAnalisis" method ="post" class="was-validated"><%
			Analisis anActual  = (Analisis)request.getAttribute("analisis");
			
			if(anActual==null){
				anActual = new Analisis();
			}
		%>
		<div class="form-group">
			<label for="ID">ID</label>
			<input type="text" class="form-control" name="id"  value="<%=anActual.getCodAnalisis() %>" readonly required>
		</div>
	
		<div class="form-group">
			<label for="Descripcion">Descripcion</label>
			<input type="text" class="form-control" name="descripcion" value="<%=anActual.getDescripcion() %>"  required>
		</div>
		<div class="form-group">
		<label for="Precio">Precio</label>
		<input type="text" class="form-control" name="precio" value="<%= anActual.getPrecio() %>" required>
		</div>
	<button type="submit" class="btn btn-primary" name="accion" value="modificar">Editar Analisis</button>
    <button type="button" class="btn btn-secondary" onclick="location.href = 'ConsultaAnalisis.jsp'">Cerrar </button>
      
</form>  

</div>

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>