package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Analisis;
import logic.LogicAnalisis;

/**
 * Servlet implementation class ListaAnalisis
 */
@WebServlet({ "/ListaAnalisis", "/listaanalisis", "/listaAnalisis", "/Listaanalisis" })
public class ListaAnalisis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaAnalisis() {
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
					this.editarAnalisis(request,response);break;
			case "eliminar":
			this.eliminarAnalisis(request,response);break;
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
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion) {
			case "insertar":
			this.insertarAnalisis(request,response);break;
			case "modificar":
			this.modificarAnalisis(request,response);break;
			case "eliminar":
				this.eliminarAnalisis(request,response);break;
				
			
			default:
				//this.accionDefault(request,response);
			}
		}
			else {
				//this.accionDefault(request,response);
			}
		
		}
	
	
	
	private void insertarAnalisis(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble( request.getParameter("precio"));
		Analisis analisis  = new Analisis();
		analisis.setDescripcion(descripcion);
		analisis.setPrecio(precio);
		new LogicAnalisis().add(analisis);
		request.getRequestDispatcher("/ConsultaAnalisis.jsp").forward(request, response);

	}

	private void editarAnalisis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idAnalisis = Integer.parseInt(request.getParameter("idAnalisis"));
		Analisis an = new Analisis();
		an.setCodAnalisis(idAnalisis);
		Analisis analisis = new LogicAnalisis().getByCod(an);
		request.setAttribute("analisis", analisis);
		request.getRequestDispatcher("/EditarAnalisis.jsp").forward(request, response);
		
	}
	
	private void modificarAnalisis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idAnalisis = Integer.parseInt(request.getParameter("idAnalisis"));
		Analisis an = new Analisis();
		an.setCodAnalisis(idAnalisis);
		Analisis analisisActual= new LogicAnalisis().getByCod(an);
		String descripcion= request.getParameter("descripcion");
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		analisisActual.setDescripcion(descripcion);
		analisisActual.setPrecio(precio);
		
		new LogicAnalisis().update(analisisActual);
		//this.accionDefault(request,response);
		 
		
	}
	
	private void eliminarAnalisis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idAnalisis = Integer.parseInt(request.getParameter("idAnalisis"));
		Analisis an = new Analisis();
		an.setCodAnalisis(idAnalisis);
		
		new LogicAnalisis().remove(an);
		
		//this.accionDefault(request,response);
		
	}
	
	
	
	
	
	

}
