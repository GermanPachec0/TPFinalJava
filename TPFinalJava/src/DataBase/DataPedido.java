package DataBase;

import java.sql.*;
import java.util.LinkedList;

import entities.Cliente;
import entities.Liquidacion;
import entities.Pedido;
import entities.PedidoAnalisis;
import entities.Semilla;

public class DataPedido {
	
	public LinkedList<Pedido> getall(){
		Statement stmt = null;
		ResultSet rs = null;
		
		LinkedList<Pedido> pedidos = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select cod_pedido, cuit, cod_semilla, cod_liquidacion, fecha_pedido, descuento from pedido");
			
			if(rs!=null) {
				while(rs.next()) {
					Pedido p = new Pedido();
					p.setCodPedido(rs.getInt("cod_pedido"));
					p.setListAnalisis(new DataPedidoAnalisis().getByPedido(p));
					Cliente c = new Cliente();
					c.setCuit(rs.getString("cuit"));
					p.setCliente(new DataCliente().getByCuit(c));
					Semilla s = new Semilla();
					s.setCodSemilla(rs.getInt("cod_semilla"));
					p.setSemilla(s);
					p.setCodLiquidacion(rs.getInt("cod_liquidacion"));
					p.setFechaPedido(rs.getDate("fecha_pedido"));
					p.setDescuento(rs.getDouble("descuento"));
					
					pedidos.add(p);
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
		return pedidos;
	}

	public LinkedList<Pedido> getByCodLiquidacion(Liquidacion l) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<Pedido> pedidos = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select cod_pedido, cuit, cod_semilla, fecha_pedido, descuento from pedido where cod_liquidacion=?");
			stmt.setInt(1, l.getCodLiquidacion());
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Pedido p = new Pedido();
					p.setCodPedido(rs.getInt("cod_pedido"));
					p.setListAnalisis(new DataPedidoAnalisis().getByPedido(p));
					Cliente c = new Cliente();
					c.setCuit(rs.getString("cuit"));
					p.setCliente(new DataCliente().getByCuit(c));
					Semilla s = new Semilla();
					s.setCodSemilla(rs.getInt("cod_semilla"));
					p.setSemilla(s);
					p.setCodLiquidacion(rs.getInt("cod_liquidacion"));
					p.setFechaPedido(rs.getDate("fecha_pedido"));
					p.setDescuento(rs.getDouble("descuento"));
					
					pedidos.add(p);
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
		return pedidos;
	}

	public void add(Pedido p) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into pedido(cuit, cod_semilla, fecha_pedido, descuento) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,p.getCliente().getCuit());
			stmt.setInt(2, p.getSemilla().getCodSemilla());
			stmt.setDate(3, p.getFechaPedido());
			stmt.setDouble(4, p.getDescuento());
			stmt.executeUpdate();
			
			keyRs = stmt.getGeneratedKeys();
			if(keyRs!=null && keyRs.next()) {
				p.setCodPedido(keyRs.getInt(1));
			}
			
			DataPedidoAnalisis dpa = new DataPedidoAnalisis();
			for(PedidoAnalisis pa : p.getListAnalisis()) {
				pa.setPedido(p);
				dpa.add(pa);
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

	public void update(Pedido p) {
		//Aun por configurar
	}
}
