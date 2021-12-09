package entities;

public class Usuario {

	private int codUser;
	private String username;
	private String password;
	private int tipo;
	private String nombre;
	private String apellido;
	
	public int getCod_user() {
		return codUser;
	}
	public void setCod_user(int cod_user) {
		this.codUser = cod_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
