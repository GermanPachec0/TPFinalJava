package entities;

public class Analisis {

	private int cod_analisis;
	private Double precio;
	private String descripcion;
	
	public int getCod_analisis() {
		return cod_analisis;
	}
	public void setCod_analisis(int cod_analisis) {
		this.cod_analisis = cod_analisis;
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
