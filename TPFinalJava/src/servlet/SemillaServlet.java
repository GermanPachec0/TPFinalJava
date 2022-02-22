package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Semilla;
import logic.LogicSemilla;

/**
 * Servlet implementation class SemillaServlet
 */
@WebServlet("/SemillaServlet")
public class SemillaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SemillaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion) {
			case "editar":
				this.editarSemilla(request,response);break;
			case "eliminar":
			this.eliminarSemilla(request,response);break;
			default:
				//this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		
	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion) {
			case "insertar":
			this.insertarSemilla(request,response);break;
			case "modificar":
			this.modificarSemilla(request,response);break;
			case "eliminar":
				this.eliminarSemilla(request,response);break;
			
				
			default:
				//this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		doGet(request, response);
	}


	private void insertarSemilla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String especie = request.getParameter("especie");
		String raza =  request.getParameter("raza");
		Semilla semilla  = new Semilla();
			semilla.setEspecie(especie);
			semilla.setRaza(raza);
		new LogicSemilla().add(semilla);
		request.getRequestDispatcher("/ListaSemilla.jsp").forward(request, response);
	}
	
	private void editarSemilla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int codSemilla = Integer.parseInt(request.getParameter("codSemilla"));
		Semilla semilla = new Semilla();
			semilla.setCodSemilla(codSemilla);
		Semilla sem = new LogicSemilla().getByCod(semilla);
			request.setAttribute("semilla", sem);
		request.getRequestDispatcher("/EditarSemilla.jsp").forward(request, response);
		
	}
	

	private void modificarSemilla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int codSemilla = Integer.parseInt(request.getParameter("codSemilla"));
		Semilla sem = new Semilla();
		sem.setCodSemilla(codSemilla);
		Semilla semillaActual = new LogicSemilla().getByCod(sem);
	
		String especie= request.getParameter("especie");
		String raza = request.getParameter("raza");
		
		semillaActual.setEspecie(especie);
		semillaActual.setRaza(raza);
		
		new LogicSemilla().update(semillaActual);
		request.getRequestDispatcher("/ListaSemilla.jsp").forward(request, response);
	}
	
	private void eliminarSemilla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int codSemilla = Integer.parseInt(request.getParameter("codSemilla"));
		Semilla sem = new Semilla();
		sem.setCodSemilla(codSemilla);
		
		new LogicSemilla().remove(sem);
		request.getRequestDispatcher("/ListaSemilla.jsp").forward(request, response);
		
	}
	
	
	
	
	

}
