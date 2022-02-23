package logic;

import java.util.LinkedList;

import DataBase.DataLiquidacion;
import DataBase.DataPedido;
import entities.Liquidacion;
import entities.Pedido;

public class LogicLiquidacion {
	private DataLiquidacion dl;
	private DataPedido dp;
	
	public LogicLiquidacion() {
		dl = new DataLiquidacion();
		dp = new DataPedido();
	}
	public LinkedList<Liquidacion> getAll(){
		return dl.getAll();
	}
	public Liquidacion getByCod(Liquidacion liqToSearch) {
		Liquidacion l = dl.getByCod(liqToSearch);
		l.setPedidos(dp.getByCodLiquidacion(l));
		return l;
	}
	public void add(Liquidacion l) {
		dl.add(l);
		for(Pedido p: l.getPedidos()) {
			p.setCodLiquidacion(l.getCodLiquidacion());
			dp.update(p);
		}
	}
	public void update(Liquidacion l) {
		dl.update(l);
		for(Pedido p : l.getPedidos()) {
			switch (p.getState()) {
			case Untouched:
				break;
			case New:
				p.setCodLiquidacion(l.getCodLiquidacion());
				dp.update(p);
				break;
			case Deleted:
				p.setCodLiquidacion(0);
				dp.update(p);
				break;
			default:
				break;
			}
		}
	}
	public void remove(Liquidacion l) {
		for(Pedido p : l.getPedidos()) {
			p.setCodLiquidacion(0);
			dp.update(p);
		}
		dl.remove(l);
	}
}
