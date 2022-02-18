package DataBase;

import java.sql.*;
import java.util.LinkedList;

import entities.Analisis;
import entities.Pedido;
import entities.PedidoAnalisis;

public class DataPedidoAnalisis {

	public LinkedList<PedidoAnalisis> getByPedido(Pedido p){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<PedidoAnalisis> lista = new LinkedList<PedidoAnalisis>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_analisis, estado, telefono from pedido_analisis where cod_pedido = ?");
			stmt.setInt(1, p.getCodPedido());
			rs = stmt.executeQuery();
			if(rs != null)
			{
				while(rs.next())
				{
					PedidoAnalisis pa = new PedidoAnalisis();
					Analisis a = new Analisis();
					a.setCodAnalisis(rs.getInt("cod_analisis"));
					pa.setAnalisis(new DataAnalisis().getByCod(a));
					pa.setEstado(rs.getString("estado"));
					pa.setObservaciones(rs.getString("observaciones"));
					lista.add(pa);
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
		
		return lista;
		
	}

}
