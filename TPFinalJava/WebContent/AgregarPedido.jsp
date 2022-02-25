<%@page import="entities.Estado"%>
<%@page import="entities.Cliente"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicCliente"%>
<%@page import="logic.LogicSemilla"%>
<%@page import="entities.Semilla"%>
<%@page import="entities.Pedido"%>
<%@page import="entities.PedidoAnalisis"%>
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

<title>Agregar Pedido</title>

 <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
  <%LinkedList<Cliente> listaCli = new LogicCliente().getAll();
    LinkedList<Semilla> listaSem = new LogicSemilla().getAll();
    		
    Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");	
    if(pedido == null){
	    pedido = new Pedido();
	    pedido.setListAnalisis(new LinkedList<PedidoAnalisis>());
	    pedido.setCliente(new Cliente());
	    pedido.setSemilla(new Semilla());
	    pedido.setFechaPedido(new java.sql.Date(new java.util.Date().getTime()));
	    pedido.setState(Estado.New);
	    request.getSession().setAttribute("pedido",pedido);
    }
    
    
  %>
</head>
<body style="background-color:rgb(251, 252, 255);">

<div class="container">

	<div class="mt-4 p-5 bg-info text-white rounded text-center">
  		<h1>Agregar Pedido</h1>
	</div>
  <br>
   
   <form action="PedidoServlet" method ="post" class="was-validated">
   
    	<div class="form-group">
  			<label for="sel1">Seleccionar Cliente</label>
  				<select class="form-control" id="sel1" name="cli">
  				<%if(pedido.getCliente().getCuit() != null){ %>
  					<option  value="<%=pedido.getCliente().getCuit()%>"><%=pedido.getCliente().getRazonSocial() %></option>
  				<%} %>
  				<%for(Cliente cli : listaCli){
  					if(!cli.getCuit().equals(pedido.getCliente().getCuit())){%>
				    <option  value="<%=cli.getCuit()%>"><%=cli.getRazonSocial() %></option>
				<%} } %>	    
  				</select>
		</div>
		
		<div class="form-group">
  			<label for="sel1">Seleccionar Semilla</label>
  		   	
  				<select class="form-control" id="sel1" name="codSem">
  				<%if(pedido.getSemilla().getCodSemilla() != 0){ %>
  					<option value="<%=pedido.getSemilla().getCodSemilla()%>" ><%="Especie: "+pedido.getSemilla().getEspecie() + " -- Raza: " + pedido.getSemilla().getRaza() %></option>
  				<%} %>
  				<%for(Semilla sem : listaSem){ 
  					if(sem.getCodSemilla() != pedido.getSemilla().getCodSemilla()){%>
					    <option value="<%=sem.getCodSemilla()%>" ><%="Especie: "+sem.getEspecie() + " -- Raza: " + sem.getRaza() %></option>
				<%} } %>
  				</select>
		</div>
		
		<div class="form-group">
			 <label for="birthday">Fecha de Pedido</label>
 			 <input type="date" id="fecha" name="fecha" value="<%=pedido.getFechaPedido()%>" required>
		</div>
		<div class="form-group">
			 <label for="quantity">Elegir Porcentaje de descuento</label>
  			<input type="number" id="quantity" step="0.01" name="descuento" min="0" max="100" value="<%=pedido.getDescuento()%>" >
		</div>
	
		<button type="submit" class="btn btn-primary" name="accion" value="insertar">Agregar analisis al pedido</button>
		<br>
		<br>
	<table class="table table-fixed table-condensed">
	    <thead class="table-dark">
	      <tr>
	        <th>Codigo Analisis</th>
	        <th>Descripcion</th>
	        <th>Precio</th>
	      </tr>
	    </thead>
	    <tbody>
	    
	    <%for(PedidoAnalisis pa : pedido.getListAnalisis() ){ %>
	      <tr>
	        <td><%=pa.getAnalisis().getDescripcion() %> </td>
	        <td><%=pa.getEstado() %></td>
	        <td><%=pa.getObservaciones() %></td>
	      </tr>
	      <%} %>
	     
	    </tbody>
  </table>  
  
  <button type="submit" class="btn btn-primary" name="accion" value="insertar_def">Confirmar</button>
  <button type="button" class="btn btn-secondary" onclick="location.href = 'ListaPedido.jsp'">Cerrar </button>
   
	</form>  

</div>



<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>