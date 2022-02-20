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
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_pedido_analisis, cod_analisis, estado, observaciones from pedido_analisis where cod_pedido = ?");
			stmt.setInt(1, p.getCodPedido());
			rs = stmt.executeQuery();
			if(rs != null)
			{
				while(rs.next())
				{
					PedidoAnalisis pa = new PedidoAnalisis();
					Analisis a = new Analisis();
					pa.setCodPedidoAnalisis(rs.getInt("cod_pedido_analisis"));
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
	
	public void add(PedidoAnalisis pa) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into pedido_analisis(cod_pedido, cod_analisis, estado, observaciones) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, pa.getPedido().getCodPedido());
			stmt.setInt(2, pa.getAnalisis().getCodAnalisis());
			stmt.setString(3, pa.getEstado());
			stmt.setString(4, pa.getObservaciones());
			stmt.executeUpdate();
			
			keyRs = stmt.getGeneratedKeys();
			if(keyRs!=null && keyRs.next()) {
				pa.setCodPedidoAnalisis(keyRs.getInt(1));
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

	public void update(PedidoAnalisis pa) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update pedido_analisis set cod_pedido =?, cod_analisis=?, estado=?, observaciones=? where cod_pedido_analisis=?");
			stmt.setInt(1, pa.getPedido().getCodPedido());
			stmt.setInt(2, pa.getAnalisis().getCodAnalisis());
			stmt.setString(3, pa.getEstado());
			stmt.setString(4, pa.getObservaciones());
			stmt.setInt(5, pa.getCodPedidoAnalisis());
			
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

	public void remove(PedidoAnalisis pa) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from pedido_analisis where cod_pedido_analisis=?");
			stmt.setInt(1, pa.getCodPedidoAnalisis());
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
