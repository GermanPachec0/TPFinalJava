package logic;

import java.util.LinkedList;

import DataBase.DataSemilla;
import entities.Semilla;

public class LogicSemilla {
	private DataSemilla ds;
	
	public LogicSemilla() {
		ds = new DataSemilla();
	}
	public LinkedList<Semilla> getAll(){
		return ds.getAll();
	}
	public Semilla getByCod(Semilla s) {
		return ds.getByCod(s);
	}
	public void add(Semilla s) {
		ds.add(s);
	}
	public void update(Semilla s) {
		ds.update(s);
	}
	public void remove(Semilla s) {
		ds.remove(s);
	}
}
