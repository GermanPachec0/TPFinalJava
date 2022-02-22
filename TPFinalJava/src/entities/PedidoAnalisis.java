package entities;

public class PedidoAnalisis {

	private int codPedidoAnalisis;
	private Analisis analisis;
	private Pedido pedido;
	private String estado;
	private String observaciones;
	private Estado state;
	
	public int getCodPedidoAnalisis() {
		return codPedidoAnalisis;
	}
	public void setCodPedidoAnalisis(int codPedidoAnalisis) {
		this.codPedidoAnalisis = codPedidoAnalisis;
	}
	public Analisis getAnalisis() {
		return analisis;
	}
	public void setAnalisis(Analisis analisis) {
		this.analisis = analisis;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Estado getState() {
		return state;
	}
	public void setState(Estado state) {
		this.state = state;
	}
}