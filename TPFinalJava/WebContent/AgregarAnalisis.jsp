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

<title>Consulta de Analisis</title>
  <!-- Bootstrap core CSS -->
    <link href="styles/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="styles/signin.css" rel="stylesheet">
  
</head>
<body>

<div class ="modal fade" id="agregarAnalisisModal">

</div>

<form action="ListaAnalisis" method ="post" class="was-validated">

<div class="modal-body">

	<div class="form-group">
		<label for="Descripcion">Descripcion</label>
		<input type="text" class="form-control" name="descripcion" required>
	</div>
	
	<div class="form-group">
		<label for="Precio">Precio</label>
		<input type="text" class="form-control" name="precio" required>
	</div>
	
	<div class="modal-footer">
	<button class="btn-prymary" type="submit" name="accion" value="insertar">Guardar Analisis</button>
	</div>

</div>


</form>

</body>
</html>