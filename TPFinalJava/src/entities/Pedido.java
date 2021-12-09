package entities;

import java.sql.Date;
import java.util.LinkedList;

public class Pedido {

	private int codPedido;
	private Date fecha_pedido;
	private double descuento;
	private Cliente cliente;
	private Semilla semilla;
	private LinkedList<Analisis> listAnalisis;
	
	public int getCod_pedido() {
		return codPedido;
	}
	public void setCod_pedido(int cod_pedido) {
		this.codPedido = cod_pedido;
	}
	public Date getFecha_analisis() {
		return fecha_pedido;
	}
	public void setFecha_analisis(Date fecha_analisis) {
		this.fecha_pedido = fecha_analisis;
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
	public LinkedList<Analisis> getListAnalisis() {
		return listAnalisis;
	}
	public void setListAnalisis(LinkedList<Analisis> listAnalisis) {
		this.listAnalisis = listAnalisis;
	}
}
