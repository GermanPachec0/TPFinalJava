package logic;

import java.util.LinkedList;

import DataBase.DataPedidoAnalisis;
import entities.Pedido;
import entities.PedidoAnalisis;

public class LogicPedidoAnalisis {
	private DataPedidoAnalisis dpa;
	
	public LogicPedidoAnalisis() {
		dpa = new DataPedidoAnalisis();
	}
	public LinkedList<PedidoAnalisis> getByPedido(Pedido p){
		return dpa.getByPedido(p);
	}
	public void add(PedidoAnalisis pa) {
		dpa.add(pa);
	}
	public void update(PedidoAnalisis pa) {
		dpa.update(pa);
	}
	public void remove(PedidoAnalisis pa) {
		dpa.remove(pa);
	}
}
