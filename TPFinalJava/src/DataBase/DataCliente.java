package DataBase;
import java.sql.*;
import java.util.LinkedList;

import entities.Cliente;


public class DataCliente {
	
	public LinkedList<Cliente> getAll()
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
	
	public Cliente getByCuit(Cliente clienteToSearch) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cliente c = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cuit, razon_social, telefono, email from cliente where cuit = ?");
			stmt.setString(1, clienteToSearch.getCuit());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				c = new Cliente();
				c.setCuit(rs.getString("cuit"));
				c.setRazonSocial(rs.getString("razon_social"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public void add(Cliente c) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into cliente(cuit, razon_social, telefono, email) values(?,?,?,?)");
			
			stmt.setString(1, c.getCuit());
			stmt.setString(2, c.getRazonSocial());
			stmt.setString(3, c.getTelefono());
			stmt.setString(4, c.getEmail());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(keyRs!=null) keyRs.close();
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Cliente c) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update cliente set razon_social=?, telefono=?, email=? where cuit=?");
			stmt.setString(1, c.getRazonSocial());
			stmt.setString(2, c.getTelefono());
			stmt.setString(3, c.getEmail());
			stmt.setString(4, c.getCuit());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(Cliente c) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from cliente where cuit=?");
			stmt.setString(1, c.getCuit());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
