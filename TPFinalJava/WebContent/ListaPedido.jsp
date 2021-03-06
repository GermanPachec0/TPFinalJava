<%@page import="entities.Pedido"%>
<%@page import="entities.Cliente"%>
<%@page import="entities.PedidoAnalisis"%>
<%@page import="java.util.LinkedList"%>
<%@page import="logic.LogicPedido"%>
<%@page import="logic.LogicCliente"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="https://getbootstrap.com/favicon.ico">
<title>Lista Pedido</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
    
    <link href="styles/bootstrap.min.css" rel="stylesheet">
    <%
    	Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
		if(usr == null){
			request.setAttribute("titulo", "Acceso Denegado");
			request.setAttribute("mensage", "Usted no ha iniciado sesi?n correctamente o carece de los permisos necesarios para acceder a esta p?gina.");
			request.setAttribute("pagina", "Menu Principal");
			request.setAttribute("direccion", "./MenuPrincipal.jsp");
			request.getRequestDispatcher("/Advertencia.jsp").forward(request, response);
		}
    	LinkedList<Cliente> listaCli = new LogicCliente().getAll();
    	Cliente cliActual = (Cliente)request.getSession().getAttribute("cliente");
    	LinkedList<Pedido> listaPed = null;
    	if(cliActual != null){
    		listaPed = new LogicPedido().getByCliente(cliActual);
    	}else{
    		listaPed = new LogicPedido().getAll();
    	}
    	request.getSession().setAttribute("pedido", null);
    %>
</head>
<body style="background-color:rgb(251, 252, 255);">


<div class="fixed-top">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <!-- Brand/logo -->
		  <a class="navbar-brand mb-0 h1" href="MenuPrincipal.jsp">Men?</a>
		  
		  <!-- Links -->
		  <ul class="navbar-nav">
		    <li class="nav-item">
		      <a class="nav-link" href="ListaPedido.jsp">Pedidos</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="ListaLiquidacion.jsp">Liquidaci?n</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="ListaCliente.jsp">Clientes</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="ConsultaAnalisis.jsp">An?lisis</a>
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
		  <h1>Lista de Pedidos</h1>
		</div>
		<form name="cliente" action="PedidoServlet" method="Post">
			<input type="hidden" name="accion" value="checking">
			<label for="sel1">Filtrar por Cliente</label>	
			<select class="form-control" id="sel1" name="cuit" onchange="document.cliente.submit()">
			<%if(cliActual != null){%>
				<option value="<%=cliActual.getCuit()%>" ><%=cliActual.getRazonSocial()%></option>
			<%} %>
				<option value="">Mostrar Todos</option>
			<%for(Cliente cli : listaCli){ 
				if(!(cliActual != null && cliActual.getCuit().equals(cli.getCuit()))){%>
			    <option value="<%=cli.getCuit()%>" ><%=cli.getRazonSocial()%></option>
			<% }} %>	    
			</select>
		</form>
		

   <!-- Lista Cliente -->
  <table class="table table-fixed table-condensed">
    <thead class="table-dark">
      <tr>
        <th>Codigo Pedido</th>
        <th>Cliente</th>
        <th>Descuento</th>
         <th>Fecha Pedido</th>
          <th>SubTotal</th>
         <th>Analisis</th>
        
        <th>Editar</th>
        <th>Eliminar</th>
      </tr>
    </thead>
    <tbody>
    <%int index = 0; %>
    <%for(Pedido ped : listaPed ){ %>
 	<%index++;%>
      <tr>
        <td><%=ped.getCodPedido() %> </td>
        <td><%=ped.getCliente().getRazonSocial()%></td>
        <td><%=ped.getDescuento() %></td>
        <td><%=ped.getFechaPedido()%></td>
        <td><%=ped.GetSubTotal()%></td>
        <td><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo<%=index%>" >Listar Analisis</button>
        <td><a class="bg-primary text-white" href="PedidoServlet?accion=editar&codPed=<%=ped.getCodPedido()%>"><button type="button" class="btn btn-primary">Editar</button></a></td>
        <td><a class="bg-danger text-white" href="PedidoServlet?accion=eliminar&codPed=<%=ped.getCodPedido()%>"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
        <thead class="collapse" id="demo<%=index%>">
      		<tr>
		        <th></th>
		        <th>Analisis</th>
		        <th>Estado</th>
		        <th>Observaciones</th>
		        <th>Precio</th>
		      </tr>
		 </thead>
		 <tbody class="collapse" id="demo<%=index%>">
		 <%for(PedidoAnalisis pa : ped.getListAnalisis()){%> 
			 <tr>
			  	<td></td>
				<td><%=pa.getAnalisis().getDescripcion() %> </td>
		        <td><%=pa.getEstado()%></td>
		        <td><%=pa.getObservaciones()%></td>
		        <td><%=pa.getAnalisis().getPrecio()%></td>
			 </tr>
			 <%} %>
			 
		 </tbody>
		
        </td>
      </tr>
    <%} %>
     	
    </tbody>
  </table>
<button class="btn btn-success" onclick="location.href = 'AgregarPedido.jsp'">Agregar Pedido</button>
</div>
</div>





<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Option 2: Separate Popper and Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>




</body>
</html>