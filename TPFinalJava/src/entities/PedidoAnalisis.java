package entities;

public class PedidoAnalisis {

	private Analisis analisis;
	private String estado;
	private String observaciones;
	
	public Analisis getAnalisis() {
		return analisis;
	}
	public void setAnalisis(Analisis analisis) {
		this.analisis = analisis;
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
	
}
