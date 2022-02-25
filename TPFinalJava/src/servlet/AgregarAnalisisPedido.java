package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Analisis;
import entities.Pedido;
import entities.PedidoAnalisis;
import logic.LogicAnalisis;

/**
 * Servlet implementation class AgregarAnalisisPedido
 */
@WebServlet("/AgregarAnalisisPedido")
public class AgregarAnalisisPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAnalisisPedido() {
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
			case "agregarAnalisis":
			this.agregarAnalisis(request,response);break;
		
			}
		}
		else {
			//this.accionDefault(request,response);
		}
	}

	private void agregarAnalisis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
		PedidoAnalisis pa = new PedidoAnalisis();
			String estado = request.getParameter("estado");
			String observacion = request.getParameter("observacion");
			pa.setEstado(estado);
			pa.setObservaciones(observacion);
		
		int codAnalisis = Integer.parseInt(request.getParameter("codAnalisis"));
		Analisis ana = new Analisis();
		ana.setCodAnalisis(codAnalisis);
		ana = new LogicAnalisis().getByCod(ana);
		
		pa.setAnalisis(ana);
		pa.setObservaciones(observacion);
		pa.setEstado(estado);
		pa.setState(entities.Estado.New);
		
		pedido.getListAnalisis().add(pa);
		
		request.getRequestDispatcher("/EditarPedido.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
