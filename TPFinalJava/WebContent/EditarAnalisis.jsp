<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entities.Analisis"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="ListaAnalisis" method ="post" class="was-validated"><%
			Analisis anActual  = (Analisis)request.getAttribute("analisis");
			
			if(anActual==null){
				anActual = new Analisis();
			}
		%>
		<div class="form-group">
			<label for="ID">ID</label>
			<input type="hidden" class="form-control" name="id"  value="<%=anActual.getCodAnalisis() %>" required>
		</div>
	
		<div class="form-group">
			<label for="Descripcion">Descripcion</label>
			<input type="text" class="form-control" name="descripcion" value="<%=anActual.getDescripcion() %>" required>
		</div>
		<div class="form-group">
		<label for="Precio">Precio</label>
		<input type="text" class="form-control" name="precio" value="<%= anActual.getPrecio() %>" required>
		</div>
	<button type="submit" class="btn btn-primary" name="accion" value="modificar">Editar Analisis</button>
       <button type="button" class="btn btn-secondary">Cerrar</button>
      
</form>  
</body>
</html>