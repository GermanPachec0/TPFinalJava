package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Liquidacion;
import logic.LogicLiquidacion;

/**
 * Servlet implementation class LiquidacionServlet
 */
@WebServlet("/LiquidacionServlet")
public class LiquidacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiquidacionServlet() {
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
			case "eliminar":
				this.eliminarLiquidacion(request,response);
				break;
			case "editar":
				this.editarLiquidacion(request,response);
				break;
			default:
				break;
			}
		}
	}

	private void eliminarLiquidacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Liquidacion l = new Liquidacion();
		l.setCodLiquidacion(Integer.parseInt(request.getParameter("codLiquidacion")));
		l = new LogicLiquidacion().getByCod(l);
		new LogicLiquidacion().remove(l);
		request.getRequestDispatcher("/ListaLiquidacion.jsp").forward(request, response);
	}

	private void editarLiquidacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Liquidacion l = new Liquidacion();
		l.setCodLiquidacion(Integer.parseInt(request.getParameter("codLiquidacion")));
		l = new LogicLiquidacion().getByCod(l);
		request.getSession().setAttribute("liquidacion", l);
		request.getSession().setAttribute("modo", "editar");
		request.getRequestDispatcher("/AgregarLiquidacion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
