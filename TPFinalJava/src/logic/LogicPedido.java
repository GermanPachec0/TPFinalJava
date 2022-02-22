package logic;

import java.util.LinkedList;

import DataBase.DataPedido;
import entities.Liquidacion;
import entities.Pedido;

public class LogicPedido {
	private DataPedido dp;
	
	public LogicPedido() {
		dp = new DataPedido();
	}
	public LinkedList<Pedido> getall(){
		return dp.getall();
	}
	public LinkedList<Pedido> getByCodLiquidacion(Liquidacion l) {
		return dp.getByCodLiquidacion(l);
	}
	public void add(Pedido p) {
		dp.add(p);
	}
	public void update(Pedido p) {
		dp.update(p);
	}
	public void remove(Pedido p) {
		dp.remove(p);
	}
}
