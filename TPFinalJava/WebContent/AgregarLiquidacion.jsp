<%@page import="entities.Liquidacion"%>
<%@page import="java.util.LinkedList"%>
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
    	Liquidacion l = (Liquidacion)request.getSession().getAttribute("liquidacion");
    	if(l == null){
    		l = new Liquidacion();
    		l.setPedidos(new LinkedList<Pedido>());
    		request.getSession().setAttribute("liquidacion", l);
    	}
    	LinkedList<Pedido> pedidosALiquidar = new LogicPedido().getAll();
    %>
</head>
<body>
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
			    <%for(Pedido p : pedidosALiquidar ){
			    	System.out.println(!l.getPedidos().contains(p));
			    	if(!l.getPedidos().contains(p)){%>
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
	</form> 
	</div>
</div>
</body>
</html>