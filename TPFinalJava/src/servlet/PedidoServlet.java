package servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Pedido;
import entities.Semilla;
import logic.LogicCliente;
import logic.LogicPedido;

/**
 * Servlet implementation class PedidoServlet
 */
@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoServlet() {
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
			this.editarPedido(request,response);break;
			case "eliminar":
			this.eliminarPedido(request,response);break;
			case "insertar":
				 this.insertarPedido(request,response);
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
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch (accion) {
			
			case "modificar":
			this.modificarPedido(request,response);break;
			case "eliminar":
			this.eliminarPedido(request,response);break;
			case "insertar_def":
				 this.insertar_defPedido(request,response);
				
			default:
				//this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		doGet(request, response);
	}



	private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
			// TODO Auto-generated method stub
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			String cuit = request.getParameter("cli");
			int codSem = Integer.parseInt(request.getParameter("codSem"));
			
			java.util.Date fecha=null;
			
			try {
				System.out.println(request.getParameter("fecha"));
				fecha = formato.parse(request.getParameter("fecha"));
				System.out.println(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println(e);
			} 
			
			double descuento = Double.parseDouble(request.getParameter("descuento"));
			Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
			
			Cliente cli = new Cliente();
				cli.setCuit(cuit);
			Semilla sem = new Semilla();
				sem.setCodSemilla(codSem);
			Cliente cliente = new LogicCliente().getByCuit(cli);
			
			pedido.setCliente(cliente);
			pedido.setSemilla(sem);
			pedido.setFechaPedido(new java.sql.Date(fecha.getTime()));
			pedido.setDescuento(descuento);
			
			request.getRequestDispatcher("/AgregarAnalisisPedido.jsp").forward(request, response);

		
	}
	
	private void insertar_defPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
		
		new LogicPedido().add(pedido);
		request.getRequestDispatcher("/ListaPedido.jsp").forward(request, response);
		
	}

	private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void modificarPedido(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private void editarPedido(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}



}