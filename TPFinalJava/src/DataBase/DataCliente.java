package DataBase;
import java.sql.*;
import java.util.LinkedList;
import entities.Cliente;


public class DataCliente {
	
	public LinkedList<Cliente> GetAll()
	{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Cliente> clientes = new LinkedList<Cliente>();
		
		try 
		{
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select cuit,razon_social,telefono,email from cliente");
			
			if(rs != null)
			{
				while(rs.next())
				{
					Cliente cli = new Cliente();
					cli.setCuit(rs.getString("cuit"));
					cli.setRazonSocial("razon_social");
					cli.setTelefono("telefono");
					cli.setEmail("email");
					clientes.add(cli);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return clientes;
	}
	
	
	
	
	
	
	
	

}
