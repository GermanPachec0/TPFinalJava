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
	public void add(Usuario u) {
		du.add(u);
	}
	public void update(Usuario u) {
		du.update(u);
	}
	public void remove(Usuario u) {
		du.remove(u);
	}
	public Usuario validate(Usuario u) {
		Usuario user = null;
		user = du.getByUser(u);
		return user;
	}
}
