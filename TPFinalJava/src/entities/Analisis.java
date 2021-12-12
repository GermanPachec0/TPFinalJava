package entities;

public class Analisis {

	private int codAnalisis;
	private Double precio;
	private String descripcion;
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCodAnalisis() {
		return codAnalisis;
	}
	public void setCodAnalisis(int codAnalisis) {
		this.codAnalisis = codAnalisis;
	}
	
}
