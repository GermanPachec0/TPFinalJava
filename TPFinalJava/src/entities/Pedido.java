package entities;

import java.sql.Date;
import java.util.LinkedList;

public class Pedido {

	private int codPedido;
	private Date fechaPedido;
	private double descuento;
	private Cliente cliente;
	private Semilla semilla;
	private LinkedList<PedidoAnalisis> listAnalisis;
	private int codLiquidacion;
	private Estado state;
	
	
	public double GetSubTotal() {
		double total = 0;
		for(PedidoAnalisis pa : listAnalisis){
			if(pa.getState() != entities.Estado.Deleted) {
				total += pa.getAnalisis().getPrecio() ;
			}
		}
		return total*((double)1-(descuento*0.01));
	}
		
	
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public int getCodLiquidacion() {
		return codLiquidacion;
	}
	public void setCodLiquidacion(int codLiquidacion) {
		this.codLiquidacion = codLiquidacion;
	}
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int cod_pedido) {
		this.codPedido = cod_pedido;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Semilla getSemilla() {
		return semilla;
	}
	public void setSemilla(Semilla semilla) {
		this.semilla = semilla;
	}
	public LinkedList<PedidoAnalisis> getListAnalisis() {
		return listAnalisis;
	}
	public void setListAnalisis(LinkedList<PedidoAnalisis> listAnalisis) {
		this.listAnalisis = listAnalisis;
	}
	public Estado getState() {
		return state;
	}
	public void setState(Estado state) {
		this.state = state;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Pedido otro = (Pedido) obj;
        if(otro.getCodPedido() != this.getCodPedido()) {
        	return false;
        }
        if(this.getState() == entities.Estado.Deleted) {
        	return false;
        }
        return true;
	}
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 53 * hash + this.codPedido;
		return hash;
	}
}
