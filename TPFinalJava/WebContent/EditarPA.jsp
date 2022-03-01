<%@page import="entities.Pedido"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicCliente"%>
<%@page import="logic.LogicAnalisis"%>
<%@page import="entities.Analisis"%>
<%@page import="entities.PedidoAnalisis"%>
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
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Editar Pedido - Analisis</title>

 <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
  <%
	  	Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
		if(usr == null){
			request.setAttribute("titulo", "Acceso Denegado");
			request.setAttribute("mensage", "Usted no ha iniciado sesión correctamente o carece de los permisos necesarios para acceder a esta página.");
			request.setAttribute("pagina", "Menu Principal");
			request.setAttribute("direccion", "./MenuPrincipal.jsp");
			request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
		}
		
  		Pedido pedido =(Pedido)request.getSession().getAttribute("pedido");
    	int index = (int)request.getAttribute("index");
        PedidoAnalisis pa = pedido.getListAnalisis().get(index);
  %>
</head>
<body style="background-color:rgb(251, 252, 255);">

<div class="container">

	<div class="mt-4 p-5 bg-info text-white rounded text-center">
  		<h1>Editar Analisis del Pedido</h1>
	</div>
  <br>
   
   <form action="PedidoServlet" method ="post" class="was-validated" >
    	<div class="form-group">
  			<label for="sel1">Seleccionar Analisis</label>
  				<select class="form-control" id="sel1" name="codAnalisis">
  				
  				<%if(pa.getAnalisis().getCodAnalisis() != 0 ){ %>
  			 <option value="<%=pa.getAnalisis().getCodAnalisis()%>" ><%=pa.getAnalisis().getDescripcion()%></option>
  				<%} %>
  				
  				<%for(Analisis ana : new LogicAnalisis().getAll()){ 
  					if(ana.getCodAnalisis()!= pa.getAnalisis().getCodAnalisis()){%>
					    <option value="<%=ana.getCodAnalisis()%>" ><%=ana.getDescripcion()%></option>
					<%}} %>	    
  				</select>
		</div>
		
		<div class="form-group">
					<label for="Estado">Estado</label>
					<input type="text" class="form-control" name="estado" value="<%=pa.getEstado()%>"  >
		</div>
		
		<div class="form-group">
					<label for="Observaciones">Observaciones</label>
					<input type="text" class="form-control" name="observacion" value="<%=pa.getObservaciones()%>" >
		</div>
		
		<div class="form-group"  style="display:none;">
					<input type="text" class="form-control" name="index" value="<%=index%>" >
		</div>

		<button type="submit" class="btn btn-primary" name="accion" value="modificarPA">Confirmar</button>
		<button type="button" class="btn btn-secondary" onclick="location.href = 'EditarPedido.jsp'">Cerrar </button>
		
	</form>  

</div>



<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>