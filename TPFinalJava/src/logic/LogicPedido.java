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
		LinkedList<Pedido> listaPedido = dp.getall();
		for(Pedido p : listaPedido) {
			p.setListAnalisis(dpa.getByPedido(p));
		}
		return listaPedido;
	}
	public LinkedList<Pedido> getByCodLiquidacion(Liquidacion l) {
		return dp.getByCodLiquidacion(l);
	}
	public void add(Pedido p) {
		dp.add(p);
		for(PedidoAnalisis pa : p.getListAnalisis()) {
			dpa.add(pa);
		}
	}
	public void update(Pedido p) {
		dp.update(p);
		for(PedidoAnalisis pa : p.getListAnalisis()) {
			switch (pa.getState()) {
			case Untouched:
				break;
			case New:
				pa.set
			default:
				break;
			}
		}
	}
	public void remove(Pedido p) {
		for(PedidoAnalisis pa : p.getListAnalisis()) {
			dpa.remove(pa);
		}
		dp.remove(p);
	}
}
