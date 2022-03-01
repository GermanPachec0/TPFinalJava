<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Usuario"%>
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


<title>Editar Semilla</title>

  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <%
   	Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
	if(usr == null || usr.getTipo() != 0){
		request.setAttribute("titulo", "Acceso Denegado");
		request.setAttribute("mensage", "Usted no ha iniciado sesión correctamente o carece de los permisos necesarios para acceder a esta página.");
		request.setAttribute("pagina", "Menu Principal");
		request.setAttribute("direccion", "./MenuPrincipal.jsp");
		request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
	}
	%>
</head>
<body style="background-color:rgb(251, 252, 255);">
<div class="container-xl">

<div class="mt-4 p-5 bg-info text-white rounded">
  <h1>Editar Usuario</h1>
</div>

<form action="UsuarioServlet" method ="post" class="was-validated"><%
			Usuario usuActual  = (Usuario)request.getAttribute("usuario");
			
			if(usuActual==null){
				usuActual = new Usuario();
			}
		%>
		
			<div class="form-group">
			<label for="Codigo de usuario">Codigo de Usuario</label>
			<input type="text" class="form-control" name="codUser"  value="<%=usuActual.getCodUser() %>" readonly required>
		</div>
		<div class="form-group">
			<label for="Apellido">Apellido</label>
			<input type="text" class="form-control" name="apellido"  value="<%=usuActual.getApellido() %>"  required>
		</div>
		<div class="form-group">
			<label for="Nombre">Nombre</label>
			<input type="text" class="form-control" name="nombre" value="<%=usuActual.getNombre()%>"  required>
		</div>
		<div class="form-group">
			<label for="Tipo Usuario">Tipo de Usuario</label>
			<input type="text" class="form-control" name="tipo" value="<%=usuActual.getTipo()%>" required>
		</div>
			<div class="form-group">
			<label for="Username">Username</label>
			<input type="text" class="form-control" name="username" value="<%= usuActual.getUsername()%>" required>
		</div>
		
		
	<button type="submit" class="btn btn-primary" name="accion" value="modificar">Editar Usuario</button>
    <button type="button" class="btn btn-secondary" onclick="location.href = 'ListaUsuario.jsp'">Cerrar </button>
      
</form>  

</div>

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>