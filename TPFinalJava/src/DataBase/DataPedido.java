package DataBase;

import java.sql.*;
import java.util.LinkedList;

import entities.Cliente;
import entities.Estado;
import entities.Liquidacion;
import entities.Pedido;
import entities.Semilla;

public class DataPedido {
	
	public LinkedList<Pedido> getAll(){
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
					Cliente c = new Cliente();
					c.setCuit(rs.getString("cuit"));
					p.setCliente(new DataCliente().getByCuit(c));
					Semilla s = new Semilla();
					s.setCodSemilla(rs.getInt("cod_semilla"));
					p.setSemilla(s);
					p.setCodLiquidacion(rs.getInt("cod_liquidacion"));
					p.setFechaPedido(rs.getDate("fecha_pedido"));
					p.setDescuento(rs.getDouble("descuento"));
					p.setState(Estado.Untouched);
					
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
					p.setFechaPedido(rs.getDate("fecha_pedido"));
					p.setDescuento(rs.getDouble("descuento"));
					p.setState(Estado.Untouched);
					
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
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update pedido set cuit=?, cod_semilla=?,cod_liquidacion=?,fecha_pedido=?,descuento=? where cod_pedido=?");
			stmt.setString(1, p.getCliente().getCuit());
			stmt.setInt(2, p.getSemilla().getCodSemilla());
			if(p.getCodLiquidacion() == 0) {
				stmt.setNull(3, java.sql.Types.INTEGER);
			}else{
				stmt.setInt(3, p.getCodLiquidacion());
			}
			stmt.setDate(4, p.getFechaPedido());
			stmt.setDouble(5, p.getDescuento());
			stmt.setInt(6, p.getCodPedido());
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
	
	public void remove(Pedido p) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from pedido where cod_pedido=?");
			stmt.setInt(1, p.getCodPedido());
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
