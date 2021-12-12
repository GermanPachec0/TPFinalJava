package logic;

import java.util.LinkedList;

import DataBase.DataAnalisis;
import entities.Analisis;

public class LogicAnalisis {
	private DataAnalisis da;
	
	public LogicAnalisis() {
		da = new DataAnalisis();
	}
	
	public LinkedList<Analisis> getAll(){
		return da.getAll();
	}
	
	public Analisis getByCod(Analisis a) {
		return da.getByCod(a);
	}
	
	public void add(Analisis a) {
		da.add(a);
	}
	
	public void update(Analisis a) {
		da.update(a);
	}
	
	public void remove(Analisis a) {
		da.remove(a);
	}
}
