package DataBase;

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
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return analisis;
	}
	
}
