package logic;

import java.util.LinkedList;

import DataBase.DataUsuario;
import entities.Usuario;

public class LogicUsuario {
	private DataUsuario du;
	
	public LogicUsuario() {
		du = new DataUsuario();
	}
	
	public LinkedList<Usuario> getAll(){
		return du.getAll();
	}
	
	public Usuario getBycod(Usuario u) {
		return du.getByCod(u);
	}
	
	public Usuario getByUser(Usuario u) {
		return du.getByUser(u);
	}
}
