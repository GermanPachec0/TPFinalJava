package entities;

public class Analisis {

	private int codAnalisis;
	private Double precio;
	private String descripcion;
	
	public int getCod_analisis() {
		return codAnalisis;
	}
	public void setCod_analisis(int cod_analisis) {
		this.codAnalisis = cod_analisis;
	}
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
	
}
