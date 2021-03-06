package servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Analisis;
import entities.Cliente;
import entities.Pedido;
import entities.PedidoAnalisis;
import entities.Semilla;
import logic.LogicAnalisis;
import logic.LogicCliente;
import logic.LogicPedido;
import logic.LogicSemilla;

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
		    this.insertarPedido(request,response);break;
			case "editarPA":
			this.editarPA(request,response);break;
			case "eliminarPA":
			this.eliminarPA(request,response);break;
			case "checking":
			this.SetCliente(request,response);break;
				 
			default:
				//this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		
	}

	

	private void SetCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cuit = (String)request.getParameter("cuit");
		if(cuit != null) {
			Cliente c = new Cliente();
			c.setCuit(cuit);
			c = new LogicCliente().getByCuit(c);
			request.getSession().setAttribute("cliente", c);
		}else {
			request.getSession().setAttribute("cliente", null);
		}
		request.getRequestDispatcher("/ListaPedido.jsp").forward(request, response);
	}

	private void eliminarPA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index =  Integer.parseInt(request.getParameter("index"));
		Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
		PedidoAnalisis paActual=pedido.getListAnalisis().get(index);
		if(paActual.getState() == entities.Estado.New) {
			pedido.getListAnalisis().remove(paActual);
		}else {
			paActual.setState(entities.Estado.Deleted);
		}
		request.getRequestDispatcher("/EditarPedido.jsp").forward(request, response);
		
		
	}

	private void editarPA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int index =  Integer.parseInt(request.getParameter("index"));
		request.setAttribute("index", index);
		request.getRequestDispatcher("/EditarPA.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if(accion!=null)
		{
			switch(accion) {
			
			case "modificar":
			this.modificarPedido(request,response);break;
			case "eliminar":
			this.eliminarPedido(request,response);break;
			case "insertar_def":
			this.insertar_defPedido(request,response);
			case "modificarPA":
				this.modificarPA(request,response);
				
				
			default:
				//this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		doGet(request, response);
	}



	private void modificarPA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
		int codAnalisis =Integer.parseInt(request.getParameter("codAnalisis"));
		String estado = request.getParameter("estado");
		String observacion = request.getParameter("observacion");
		int index = Integer.parseInt( request.getParameter("index"));
		
		PedidoAnalisis paActual=pedido.getListAnalisis().get(index);
		Analisis an = new Analisis();
		an.setCodAnalisis(codAnalisis);
		paActual.setAnalisis(new LogicAnalisis().getByCod(an));
		paActual.setEstado(estado);
		paActual.setObservaciones(observacion);
		if(paActual.getState() != entities.Estado.New) {
			paActual.setState(entities.Estado.Modified);
		}
		
		request.getRequestDispatcher("/EditarPedido.jsp").forward(request, response);
	
		
		
	}

	private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
			// TODO Auto-generated method stub
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			String cuit = request.getParameter("cli");
			int codSem = Integer.parseInt(request.getParameter("codSem"));
			
			java.util.Date fecha=null;
			
			try {
				fecha = formato.parse(request.getParameter("fecha"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			double descuento = Double.parseDouble(request.getParameter("descuento"));
			Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
			
			Cliente cli = new Cliente();
				cli.setCuit(cuit);
			Semilla sem = new Semilla();
				sem.setCodSemilla(codSem);
			Cliente cliente = new LogicCliente().getByCuit(cli);
			Semilla s = new LogicSemilla().getByCod(sem);
			pedido.setCliente(cliente);
			pedido.setSemilla(s);
			pedido.setFechaPedido(new java.sql.Date(fecha.getTime()));
			pedido.setDescuento(descuento);
			
			request.getRequestDispatcher("/AgregarAnalisisPedido.jsp").forward(request, response);

		
	}
	
	private void insertar_defPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String cuit = request.getParameter("cli");
		int codSem = Integer.parseInt(request.getParameter("codSem"));
		
		java.util.Date fecha=null;
		
		try {
			fecha = formato.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		double descuento = Double.parseDouble(request.getParameter("descuento"));
		Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
		
		Cliente cli = new Cliente();
			cli.setCuit(cuit);
		Semilla sem = new Semilla();
			sem.setCodSemilla(codSem);
		Cliente cliente = new LogicCliente().getByCuit(cli);
		Semilla s = new LogicSemilla().getByCod(sem);
		pedido.setCliente(cliente);
		pedido.setSemilla(s);
		pedido.setFechaPedido(new java.sql.Date(fecha.getTime()));
		pedido.setDescuento(descuento);
		
		new LogicPedido().add(pedido);
		request.getRequestDispatcher("/ListaPedido.jsp").forward(request, response);
	}

	private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codPed = Integer.parseInt(request.getParameter("codPed"));
		Pedido pedido = new Pedido();
		pedido.setCodPedido(codPed);
		new LogicPedido().remove(pedido);
		request.getRequestDispatcher("/ListaPedido.jsp").forward(request, response);
		
	}

	private void modificarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String cuit = request.getParameter("cli");
		int codSem = Integer.parseInt(request.getParameter("codSem"));
		
		java.util.Date fecha=null;
		
		try {
			fecha = formato.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		double descuento = Double.parseDouble(request.getParameter("descuento"));
		
		Cliente cli = new Cliente();
			cli.setCuit(cuit);
		Semilla sem = new Semilla();
			sem.setCodSemilla(codSem);
		Cliente cliente = new LogicCliente().getByCuit(cli);
		Semilla s = new LogicSemilla().getByCod(sem);
		pedido.setCliente(cliente);
		pedido.setSemilla(s);
		pedido.setFechaPedido(new java.sql.Date(fecha.getTime()));
		pedido.setDescuento(descuento);
		
		
		new LogicPedido().update(pedido);
		request.getRequestDispatcher("/ListaPedido.jsp").forward(request, response);
	}
	
	private void editarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		int codPedido = Integer.parseInt(request.getParameter("codPed"));
		Pedido pedido = new Pedido();
		pedido.setCodPedido(codPedido);
		pedido = new LogicPedido().getByCod(pedido);
		
		
		
		request.setAttribute("pedido", pedido);
		request.getRequestDispatcher("/EditarPedido.jsp").forward(request, response);
		
	}



}
