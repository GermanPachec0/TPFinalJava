package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pedido;
import entities.Usuario;
import entities.Liquidacion;
import logic.LogicLiquidacion;
import logic.LogicPedido;

/**
 * Servlet implementation class LiquidacionPedidoServlet
 */
@WebServlet("/LiquidacionPedidoServlet")
public class LiquidacionPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiquidacionPedidoServlet() {
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
			case "insertar":
				this.insertarPedido(request,response);
				break;
			case "eliminar":
				this.eliminarPedido(request,response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion) {
			case "insertar_def":
				this.insertar_defLiquidacion(request,response);
				break;
			case "update_def":
				this.update_defLiquidacion(request,response);
				break;
			default:
				break;
			}
		}
		doGet(request, response);
	}

	private void update_defLiquidacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Liquidacion l = (Liquidacion)request.getSession().getAttribute("liquidacion");
		new LogicLiquidacion().update(l);
		request.getSession().setAttribute("liquidacion", null);
		request.getRequestDispatcher("/ListaLiquidacion.jsp").forward(request, response);
	}

	private void insertar_defLiquidacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Liquidacion l = (Liquidacion)request.getSession().getAttribute("liquidacion");
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		l.setEmpleado(u);
		new LogicLiquidacion().add(l);
		request.getSession().setAttribute("liquidacion", null);
		request.getRequestDispatcher("/ListaLiquidacion.jsp").forward(request, response);
	}

	private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int index = Integer.parseInt(request.getParameter("index"));
		Liquidacion l = (Liquidacion)request.getSession().getAttribute("liquidacion");
		Pedido p = l.getPedidos().get(index);
		if(p.getState() == entities.Estado.New) {
			l.getPedidos().remove(p);
		}else {
			p.setState(entities.Estado.Deleted);
		}
		request.getRequestDispatcher("/AgregarLiquidacion.jsp").forward(request, response);
		
	}

	private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int codPedido = Integer.parseInt(request.getParameter("codPedido"));
		Pedido p = new Pedido();
		p.setCodPedido(codPedido);
		p = new LogicPedido().getByCod(p);
		p.setState(entities.Estado.New);
		((Liquidacion)request.getSession().getAttribute("liquidacion")).getPedidos().add(p);
		request.getRequestDispatcher("/AgregarLiquidacion.jsp").forward(request, response);
	}

}
