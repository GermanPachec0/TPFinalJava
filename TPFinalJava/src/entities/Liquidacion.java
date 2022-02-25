package entities;

import java.sql.Date;
import java.util.LinkedList;

public class Liquidacion {

	private int codLiquidacion;
	private Date fechaLiquidacion;
	private Double total;
	private LinkedList<Pedido> pedidos;
	private Usuario empleado;
	
	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}
	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}
	public Double getTotal() {
		total = (double) 0;
		for(Pedido p : pedidos) {
			if(p.getState() != entities.Estado.Deleted) {
				/*total += p.GetSubTotal();*/
			}
		}
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public LinkedList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(LinkedList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Usuario getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Usuario empleado) {
		this.empleado = empleado;
	}
	public int getCodLiquidacion() {
		return codLiquidacion;
	}
	public void setCodLiquidacion(int codLiquidacion) {
		this.codLiquidacion = codLiquidacion;
	}
}
