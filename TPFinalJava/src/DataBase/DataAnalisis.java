package DataBase;

import entities.Cliente;
import java.util.LinkedList;

import java.sql.*;

import entities.Analisis;

public class DataAnalisis {

	public LinkedList<Analisis> getAll(){
		Statement stmt = null;
		ResultSet rs = null;
		
		LinkedList<Analisis> analisis = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select cod_analisis, precio, descripcion from analisis");
			
			if(rs!=null) {
				while(rs.next()) {
					Analisis a = new Analisis();
					a.setCodAnalisis(rs.getInt("cod_analisis"));
					a.setPrecio(rs.getDouble("precio"));
					a.setDescripcion(rs.getString("descripcion"));
					
					analisis.add(a);
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
		
		
		return analisis;
	}
	
	public Analisis getByCod(Analisis analisisToSearch) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Analisis a = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_analisis, precio, descripcion from analisis where cod_analisis = ?");
			stmt.setInt(1, analisisToSearch.getCodAnalisis());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				a = new Analisis();
				a.setCodAnalisis(rs.getInt("cod_analisis"));
				a.setPrecio(rs.getDouble("precio"));
				a.setDescripcion(rs.getString("descripcion"));
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
		
		return a;
	}
	
	public void add(Analisis a) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into analisis(precio, descripcion) values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setDouble(1, a.getPrecio());
			stmt.setString(2, a.getDescripcion());
			
			stmt.executeUpdate();
			
			keyRs = stmt.getGeneratedKeys();
			if(keyRs!=null && keyRs.next()) {
				a.setCodAnalisis(keyRs.getInt(1));
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
	
	public void update(Analisis a) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update analisis set precio =?, descripcion=? where cod_analisis=?");
			stmt.setDouble(1, a.getPrecio());
			stmt.setString(2, a.getDescripcion());
			stmt.setInt(3, a.getCodAnalisis());
			
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
	
	public void remove(Analisis a) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from analisis where cod_analisis=?");
			stmt.setInt(1, a.getCodAnalisis());
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
