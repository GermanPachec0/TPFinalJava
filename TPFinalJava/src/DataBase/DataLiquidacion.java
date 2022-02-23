package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Liquidacion;

public class DataLiquidacion {
	
	public LinkedList<Liquidacion> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Liquidacion> liquidaciones = new LinkedList<Liquidacion>();
		try 
		{
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("Select cod_liquidacion,cod_user,fecha_liquidacion,total from liquidacion");
			if(rs != null)
			{
				while(rs.next())
				{
					Liquidacion l = new Liquidacion();
					l.setCodLiquidacion(rs.getInt("cod_liquidacion"));
					l.setEmpleado(new DataUsuario().getByCod(rs.getInt("cod_user")));
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
}
