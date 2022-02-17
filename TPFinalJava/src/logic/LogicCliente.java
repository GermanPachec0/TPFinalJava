package logic;

import java.util.LinkedList;

import DataBase.DataCliente;
import entities.Cliente;

public class LogicCliente {
	private DataCliente dc;
	
	public LogicCliente() {
		dc = new DataCliente();
	}
	public LinkedList<Cliente> getAll(){
		return dc.getAll();
	}
	public Cliente getByCuit(Cliente c) {
		return dc.getByCuit(c);
	}
	public void add(Cliente c) {
		dc.add(c);
	}
	public void update(Cliente c) {
		dc.update(c);
	}
	public void remove(Cliente c) {
		dc.remove(c);
	}
}
