package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Semilla;

public class DataSemilla {

	public LinkedList<Semilla> getAll(){
		Statement stmt = null;
		ResultSet rs = null;
		
		LinkedList<Semilla> semillas = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select cod_semilla, raza, especie from semilla");
			
			if(rs!=null) {
				while(rs.next()) {
					Semilla s = new Semilla();
					s.setCodSemilla(rs.getInt("cod_semilla"));
					s.setRaza(rs.getString("raza"));
					s.setEspecie(rs.getString("especie"));
					
					semillas.add(s);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return semillas;
	}
	
	public Semilla getByCod(Semilla semillaToSearch){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Semilla s = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select cod_semilla, raza, especie from semilla where cod_semilla = ?");
			stmt.setInt(1, semillaToSearch.getCodSemilla());
			rs=stmt.executeQuery();
					
			if(rs!=null && rs.next()) {
				s = new Semilla();
				s.setCodSemilla(rs.getInt("cod_semilla"));
				s.setEspecie(rs.getString("especie"));
				s.setRaza(rs.getString("raza"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public void add(Semilla s) {
		PreparedStatement stmt = null;
		ResultSet keyRs = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("insert into semilla(raza, especie) values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getRaza());
			stmt.setString(2, s.getEspecie());
			
			stmt.executeUpdate();
			
			keyRs = stmt.getGeneratedKeys();
			if(keyRs!=null && keyRs.next()) {
				s.setCodSemilla(keyRs.getInt(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(keyRs!=null) keyRs.close();
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Semilla s) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("update semilla set raza =?, especie=? where cod_semilla=?");
			stmt.setString(1, s.getRaza());
			stmt.setString(2, s.getEspecie());
			stmt.setInt(3, s.getCodSemilla());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(Semilla s) {
		PreparedStatement stmt = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("delete from semilla where cod_semilla=?");
			stmt.setInt(1, s.getCodSemilla());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null) stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
