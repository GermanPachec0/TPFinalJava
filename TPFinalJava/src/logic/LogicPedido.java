package logic;

import java.util.LinkedList;

import DataBase.DataPedido;
import DataBase.DataPedidoAnalisis;
import entities.Liquidacion;
import entities.Pedido;
import entities.PedidoAnalisis;

public class LogicPedido {
	private DataPedido dp;
	private DataPedidoAnalisis dpa;
	
	public LogicPedido() {
		dp = new DataPedido();
		dpa = new DataPedidoAnalisis();
	}
	public LinkedList<Pedido> getAll(){
		LinkedList<Pedido> listaPedido = dp.getAll();
		for(Pedido p : listaPedido) {
			p.setListAnalisis(dpa.getByPedido(p));
		}
		return listaPedido;
	}
	public LinkedList<Pedido> getByCodLiquidacion(Liquidacion l) {
		return dp.getByCodLiquidacion(l);
	}
	public LinkedList<Pedido> getNoLiquidado(){
		return dp.getNoLiquidado();
	}
	
	public Pedido getByCod(Pedido p) {
		Pedido pedido = dp.getByCod(p);
		pedido.setListAnalisis(dpa.getByPedido(pedido));
		return pedido;
	}
	public void add(Pedido p) {
		dp.add(p);
		for(PedidoAnalisis pa : p.getListAnalisis()) {
			pa.setCodPedido(p.getCodPedido());
			dpa.add(pa);
		}
	}
	public void update(Pedido p) {
		System.out.println(p.getCodPedido());
		dp.update(p);
		for(PedidoAnalisis pa : p.getListAnalisis()) {
			System.out.println(p.getCodPedido());
			switch (pa.getState()) {
			case Untouched:
				break;
			case New:
				pa.setCodPedido(p.getCodPedido());
				dpa.add(pa);
				break;
			case Modified:
				dpa.update(pa);
				break;
			case Deleted:
				dpa.remove(pa);
				break;
			default:
				break;
			}
		}
	}
	public void remove(Pedido p) {
		for(PedidoAnalisis pa : dpa.getByPedido(p)) {
			dpa.remove(pa);
		}
		dp.remove(p);
	}
}
