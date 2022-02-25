<%@page import="entities.Liquidacion"%>
<%@page import="entities.Pedido"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicLiquidacion"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	
<title>Lista Liquidacion</title>
	<!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <%
    	request.getSession().setAttribute("modo", null);
    	request.getSession().setAttribute("liquidacion", null);
    	LinkedList<Liquidacion> listaLiq= new LogicLiquidacion().getAll();
   		Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
   		if(usr == null){
   			request.setAttribute("titulo", "Acceso Denegado");
   			request.setAttribute("mensage", "Usted no ha iniciado sesión correctamente o carece de los permisos necesarios para acceder a esta página.");
   			request.setAttribute("pagina", "Menu Principal");
   			request.setAttribute("direccion", "./MenuPrincipal.jsp");
   			request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
   		}
    %>
</head>
<body style="background-color:rgb(251, 252, 255);">
<div class="fixed-top">

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
	    <%if(usr.getTipo() == 0){%>
	    <li class="nav-item">
	      <a class="nav-link" href="ListaUsuario.jsp">Usuarios</a>
	    </li>
	    <%}%>
	  </ul>
	</nav>
	<br>
	
	<div class="container">
		<div class="mt-4 p-5 bg-info text-white rounded">
		  <h1>Lista de Liquidaciones</h1>
		</div>
		<!-- Lista Liquidación -->
		<table class="table table-fixed table-condensed">
			<thead class="table-dark">
				<tr>
					<th>Código Liquidación</th>
					<th>Fecha Liquidación</th>
					<th>Empleado</th>
					<th>Total</th>
					<th>Pedidos</th>
					<th>Editar</th>
       				<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
			<%int index = 0; 
			for(Liquidacion l : listaLiq){
			index++;%>
				<tr>
					<td><%=l.getCodLiquidacion()%></td>
					<td><%=l.getFechaLiquidacion()%></td>
					<td><%=l.getEmpleado().getNombreCompleto()%></td>
					<td><%=l.getTotal()%></td>
					<td><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo<%=index%>">Mostrar Pedidos</button></td>
					<td><a class="bg-primary text-white" href="LiquidacionServlet?accion=editar&codLiquidacion=<%=l.getCodLiquidacion()%>"><button type="button" class="btn btn-primary">Editar</button></a></td>
       				<td><a class="bg-danger text-white" href="LiquidacionServlet?accion=eliminar&codLiquidacion=<%=l.getCodLiquidacion()%>"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
       			</tr>
       			<thead class="collapse" id="demo<%=index%>">
		      		<tr>
				        <th></th>
				        <th>Código Pedido</th>
				        <th>Cliente</th>
				        <th>Descuento</th>
				        <th>Fecha Pedido</th>
				        <th>SubTotal</th>
				      </tr>
				 </thead>
       			<tbody class="collapse" id="demo<%=index%>">
       			<%for(Pedido p: l.getPedidos()){%>
       				<tr>
       					<td></td>
       					<td><%=p.getCodPedido() %> </td>
				        <td><%=p.getCliente().getRazonSocial()%></td>
				        <td><%=p.getDescuento() %></td>
				        <td><%=p.getFechaPedido()%></td>
				        <td><%=p.GetSubTotal()%></td>
       				</tr>
       			<%} %>
       			</tbody>
			<%} %>
			</tbody>
		</table>
		<button class="btn btn-success" onclick="location.href = 'AgregarLiquidacion.jsp'">Nueva Liquidación</button>
	</div>
</div>
</body>
</html>