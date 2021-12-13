package DataBase;

import java.sql.*;
import java.util.LinkedList;

import entities.Usuario;

public class DataUsuario {

	public LinkedList<Usuario> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Usuario> users= new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select cod_usuario,username,tipo,nombre,apellido from usuarios");
			if(rs!=null) {
				while(rs.next()) {
					Usuario user = new Usuario();
					user.setCodUser(rs.getInt("cod_usuario"));
					user.setUsername(rs.getString("username"));
					user.setTipo(rs.getInt("tipo"));
					user.setNombre(rs.getString("nombre"));
					user.setApellido(rs.getString("apellido"));
					
					users.add(user);
				}
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
		
		return users;
	}
	
	public Usuario getByCod(Usuario userToSearch) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario u = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_usuario,username,tipo,nombre,apellido from usuarios where cod_usuario = ?");
			stmt.setInt(1, userToSearch.getCodUser());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				u = new Usuario();
				u.setCodUser(rs.getInt("cod_usuario"));
				u.setUsername(rs.getString("username"));
				u.setTipo(rs.getInt("tipo"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
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
		return u;
	}
	
	public Usuario getByUser(Usuario userToSearch) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario u = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_usuario,username,tipo,nombre,apellido from usuarios where username = ? and password = ?");
			stmt.setString(1, userToSearch.getUsername());
			stmt.setString(2, userToSearch.getPassword());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				u = new Usuario();
				u.setCodUser(rs.getInt("cod_usuario"));
				u.setUsername(rs.getString("username"));
				u.setTipo(rs.getInt("tipo"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
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
		return u;
	}

	public void add(Usuario u) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into usuarios(username,password,tipo,nombre,apellido) values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setInt(3, u.getTipo());
			stmt.setString(4, u.getNombre());
			stmt.setString(5, u.getApellido());
			
			stmt.executeUpdate();
			
			keyRs = stmt.getGeneratedKeys();
			if(keyRs!=null && keyRs.next()) {
				u.setCodUser(keyRs.getInt(1));
			}
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
	
	public void update(Usuario u) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update usuarios set username = ?, password = ?, tipo = ?, nombre = ?, apellido = ? where cod_usuario=?");
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setInt(3, u.getTipo());
			stmt.setString(4, u.getNombre());
			stmt.setString(5, u.getApellido());
			stmt.setInt(6, u.getCodUser());
			
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
	
	public void remove(Usuario u) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from usuarios where cod_usuario=?");
			stmt.setInt(1, u.getCodUser());
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
