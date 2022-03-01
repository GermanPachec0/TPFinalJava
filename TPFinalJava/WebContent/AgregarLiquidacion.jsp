<%@page import="entities.Estado"%>
<%@page import="entities.Liquidacion"%>
<%@page import="entities.Usuario"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Date"%>
<%@page import="entities.Pedido"%>
<%@page import="logic.LogicPedido"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Liquidación</title>
	<!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <%
	    Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
		if(usr == null){
			request.setAttribute("titulo", "Acceso Denegado");
			request.setAttribute("mensage", "Usted no ha iniciado sesión correctamente o carece de los permisos necesarios para acceder a esta página.");
			request.setAttribute("pagina", "Menu Principal");
			request.setAttribute("direccion", "./MenuPrincipal.jsp");
			request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
		}
		
    	String modo = (String)request.getSession().getAttribute("modo");
    	Liquidacion l = (Liquidacion)request.getSession().getAttribute("liquidacion");
    	if(l == null && modo == null){
    		l = new Liquidacion();
    		l.setPedidos(new LinkedList<Pedido>());
    		l.setFechaLiquidacion(new java.sql.Date(new java.util.Date().getTime()));
    		request.getSession().setAttribute("liquidacion", l);
    	}
    	LinkedList<Pedido> pedidosALiquidar = new LogicPedido().getNoLiquidado();
    	Usuario u;
    	if(modo == null){
    		u = (Usuario)request.getSession().getAttribute("usuario");
    	}else{
    		u = l.getEmpleado();
    	}
    %>
</head>
<body style="background-color:rgb(251, 252, 255);">
<div class="fixed-top">
	<div class="mt-4 p-5 bg-info text-white rounded text-center">
  		<h1>Generar Liquidación</h1>
	</div>
	<div class="container">
	<form action="LiquidacionPedidoServlet" method ="post" class="was-validated">
		<div class="form-group">
			<br>
  			<h3>Pedidos sin Liquidar</h3>
 			<table class="table table-fixed table-condensed">
			    <thead class="table-dark">
			      <tr>
			        <th>Codigo Pedido</th>
			        <th>Cliente</th>
			        <th>Descuento</th>
			        <th>Fecha Pedido</th>
			        <th>Acción</th>
			      </tr>
			    </thead>
			    <tbody>
			    <%LinkedList<Pedido> listaNo = new LinkedList<Pedido>();
			    for(Pedido ped : l.getPedidos()){
		    		if(ped.getState() != entities.Estado.Deleted){
		    			listaNo.add(ped);
		    		}
		    	}%>
			    <%for(Pedido p : pedidosALiquidar ){
			    	if(!listaNo.contains(p)){%>
			      <tr>
			        <td><%=p.getCodPedido() %> </td>
			        <td><%=p.getCliente().getRazonSocial()%></td>
			        <td><%=p.getDescuento() %></td>
			        <td><%=p.getFechaPedido()%></td>
			        <td><a class="bg-primary text-white" href="LiquidacionPedidoServlet?accion=insertar&codPedido=<%=p.getCodPedido()%>"><button type="button" class="btn btn-primary">Agregar</button></a></td>
			      </tr>
			      <%}} %>
			    </tbody>
	  		</table>  
		</div>
		<div class="form-group">
			<br>
  			<h3>Pedidos en la Liquidación</h3>
 			<table class="table table-fixed table-condensed">
			    <thead class="table-dark">
			      <tr>
			        <th>Codigo Pedido</th>
			        <th>Cliente</th>
			        <th>Descuento</th>
			        <th>Fecha Pedido</th>
			        <th>Acción</th>
			      </tr>
			    </thead>
			    <tbody>
			    <%int i = 0;%>
			    <%for(Pedido p : l.getPedidos()){
			    if(p.getState() != Estado.Deleted){%>
			      <tr>
			        <td><%=p.getCodPedido() %> </td>
			        <td><%=p.getCliente().getRazonSocial()%></td>
			        <td><%=p.getDescuento() %></td>
			        <td><%=p.getFechaPedido()%></td>
			        <td><a class="bg-danger text-white" href="LiquidacionPedidoServlet?accion=eliminar&index=<%=i%>"><button type="button" class="btn btn-danger">Quitar</button></a></td>
			      </tr>
			      <%}i++;} %>
			    </tbody>
	  		</table>
	  		<div>
	  			<label for="birthday">Total</label>
	  			<input type="text" id="total" name="total" value="<%=l.getTotal()%>" readonly>
	  			<label for="birthday">Empleado Asignado</label>
	  			<input type="text" id="empleado" name="empleado" value="<%=u.getNombreCompleto()%>" readonly>
	  			<label for="birthday">Fecha</label>
	  			<input type="text" id="date" name="date" value="<%=l.getFechaLiquidacion()%>" readonly>
	  		</div>
	  		<div>
	  			<button type="submit" class="btn btn-primary" name="accion" value="<%=(modo == null ? "insertar_def" : "update_def")%>">Confirmar</button>
 				<button type="button" class="btn btn-secondary" onclick="location.href = 'ListaLiquidacion.jsp'">Cancelar</button>
   			</div>
   		</div>
	</form> 
	</div>
</div>
</body>
</html>