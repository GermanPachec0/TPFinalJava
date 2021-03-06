<%@page import="entities.Usuario"%>
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

<title>Agregar Semilla</title>

 <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
  	<%
  	Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
	if(usr == null){
		request.setAttribute("titulo", "Acceso Denegado");
		request.setAttribute("mensage", "Usted no ha iniciado sesi?n correctamente o carece de los permisos necesarios para acceder a esta p?gina.");
		request.setAttribute("pagina", "Menu Principal");
		request.setAttribute("direccion", "./MenuPrincipal.jsp");
		request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
	}
	%>
</head>
<body style="background-color:rgb(251, 252, 255);">

<div class="container">

	<div class="mt-4 p-5 bg-info text-white rounded text-center">
  		<h1>Agregar Semilla</h1>
	</div>
   <form action="SemillaServlet" method ="post" class="was-validated">
				<div class="form-group">
					<label for="Especie">Especie</label>
					<input type="text" class="form-control" name="especie" required>
				</div>
				<div class="form-group">
					<label for="Raza">Raza</label>
					<input type="text" class="form-control" name="raza" required>
				</div>
			
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" name="accion" value="insertar" data-bs-dismiss="modal">Agregar Semilla</button>
			        <button type="button" class="btn btn-secondary" onclick="location.href = 'ListaSemilla.jsp'">Cerrar</button>
		        </div>
	</form>  

</div>



<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>