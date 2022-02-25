package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Liquidacion;
import entities.Usuario;

public class DataLiquidacion {
	
	public LinkedList<Liquidacion> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Liquidacion> liquidaciones = new LinkedList<Liquidacion>();
		try 
		{
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select cod_liquidacion,cod_user,fecha_liquidacion,total from liquidacion");
			if(rs != null)
			{
				while(rs.next())
				{
					Liquidacion l = new Liquidacion();
					l.setCodLiquidacion(rs.getInt("cod_liquidacion"));
					Usuario u = new Usuario();
					u.setCodUser(rs.getInt("cod_user"));
					l.setEmpleado(new DataUsuario().getByCod(u));
					l.setFechaLiquidacion(rs.getDate("fecha_liquidacion"));
					l.setTotal(rs.getDouble("total"));
					liquidaciones.add(l);
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
		
		return liquidaciones;
	}

	public Liquidacion getByCod(Liquidacion liqToSearch) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Liquidacion l = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_liquidacion,cod_user,fecha_liquidacion,total from liquidacion where cod_liquidacion = ?");
			stmt.setInt(1, liqToSearch.getCodLiquidacion());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				l = new Liquidacion();
				l.setCodLiquidacion(rs.getInt("cod_liquidacion"));
				Usuario u = new Usuario();
				u.setCodUser(rs.getInt("cod_user"));
				l.setEmpleado(new DataUsuario().getByCod(u));
				l.setFechaLiquidacion(rs.getDate("fecha_liquidacion"));
				l.setTotal(rs.getDouble("total"));
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
		return l;
	}

	public void add(Liquidacion l) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into liquidacion(cod_user, fecha_liquidacion, total) values(?,current_date(),?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, l.getEmpleado().getCodUser());
			stmt.setDouble(2, l.getTotal());
			stmt.executeUpdate();
			keyRs = stmt.getGeneratedKeys();
			if(keyRs!=null && keyRs.next()) {
				l.setCodLiquidacion(keyRs.getInt(1));
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

	public void update(Liquidacion l) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update liquidacion set cod_user =?, fecha_liquidacion=?, total=? where cod_liquidacion=?");
			stmt.setInt(1, l.getEmpleado().getCodUser());
			stmt.setDate(2, l.getFechaLiquidacion());
			stmt.setDouble(3, l.getTotal());
			stmt.setInt(4, l.getCodLiquidacion());
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
	
	public void remove(Liquidacion l) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from liquidacion where cod_liquidacion=?");
			stmt.setInt(1, l.getCodLiquidacion());
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
