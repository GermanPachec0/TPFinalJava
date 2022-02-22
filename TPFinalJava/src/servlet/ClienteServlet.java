package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Cliente;
import logic.LogicCliente;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
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
				this.editarCliente(request,response);break;
			case "eliminar":
			this.eliminarCliente(request,response);break;
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
			case "insertar":
			this.insertarCliente(request,response);break;
			case "modificar":
			this.modificarCliente(request,response);break;
			case "eliminar":
				this.eliminarCliente(request,response);break;
			
				
			default:
				//this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		doGet(request, response);
	}



	private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cuit = request.getParameter("cuit");
		String email = request.getParameter("email");
		String razonSocial = request.getParameter("razonSocial");
		String telefono = request.getParameter("telefono");
		
		Cliente cliente = new Cliente();
			cliente.setCuit(cuit);
			cliente.setEmail(email);
			cliente.setRazonSocial(razonSocial);
			cliente.setTelefono(telefono);
		new LogicCliente().add(cliente);
		request.getRequestDispatcher("/ListaCliente.jsp").forward(request, response);
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cuit = request.getParameter("cuit");
		Cliente cliente = new Cliente();
			cliente.setCuit(cuit);
		Cliente cli = new LogicCliente().getByCuit(cliente);
		request.setAttribute("Cliente", cli);
		request.getRequestDispatcher("/EditarCliente.jsp").forward(request, response);

	}
	
	private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cuit = request.getParameter("cuit");
		Cliente cliente = new Cliente();
		cliente.setCuit(cuit);
		Cliente clienteActual= new LogicCliente().getByCuit(cliente);
		String email= request.getParameter("email");
		String razonSocial = request.getParameter("razonSocial");
		String telefono = request.getParameter("telefono");
		
		
		clienteActual.setEmail(email);
		clienteActual.setRazonSocial(razonSocial);
		clienteActual.setTelefono(telefono);
		
		new LogicCliente().update(clienteActual);
		request.getRequestDispatcher("/ListaCliente.jsp").forward(request, response);
		
	}
	
	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cuit = request.getParameter("cuit");
		Cliente cliente = new Cliente();
		cliente.setCuit(cuit);
		
		new LogicCliente().remove(cliente);
		request.getRequestDispatcher("/ListaCliente.jsp").forward(request, response);
	}
	
}




	


