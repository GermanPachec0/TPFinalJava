package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Usuario;
import logic.LogicUsuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
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
				this.editarUsuario(request,response);break;
			case "eliminar":
			this.eliminarUsuario(request,response);break;
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
			this.insertarUsuario(request,response);break;
			case "modificar":
			this.modificarUsuario(request,response);break;
			case "eliminar":
				this.eliminarUsuario(request,response);break;
			
				
			default:
				// this.accionDefault(request,response);
			}
		}
		else {
			//this.accionDefault(request,response);
		}
		doGet(request, response);
	}


	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String apellido = request.getParameter("apellido");
		String nombre =  request.getParameter("nombre");
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		String username = request.getParameter("username");
		Usuario usuario  = new Usuario();
			usuario.setApellido(apellido);
			usuario.setTipo(tipo);
			usuario.setNombre(nombre);
			usuario.setUsername(username);
		new LogicUsuario().add(usuario);
		request.getRequestDispatcher("/ListaUsuario.jsp").forward(request, response);
	}
	
	private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codUser = Integer.parseInt(request.getParameter("codUser"));
		Usuario usuario = new Usuario();
			usuario.setCodUser(codUser);
		Usuario usu = new LogicUsuario().getBycod(usuario);
			request.setAttribute("usuario", usu);
		request.getRequestDispatcher("/EditarUsuario.jsp").forward(request, response);
		
	}
	
	private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codUser = Integer.parseInt(request.getParameter("codUser"));
		Usuario usu = new Usuario();
		usu.setCodUser(codUser);
		Usuario usuarioActual = new LogicUsuario().getBycod(usu);
			String apellido = request.getParameter("apellido");
			String nombre =  request.getParameter("nombre");
			int tipo = Integer.parseInt(request.getParameter("tipo"));
			String username = request.getParameter("username");
			
			usuarioActual.setApellido(apellido);
			usuarioActual.setTipo(tipo);
			usuarioActual.setNombre(nombre);
			usuarioActual.setUsername(username);
			
		new LogicUsuario().update(usuarioActual);
		request.getRequestDispatcher("/ListaUsuario.jsp").forward(request, response);
	}
	
	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int codUser = Integer.parseInt(request.getParameter("codUser"));
		Usuario usu = new Usuario();
		usu.setCodUser(codUser);
		
		new LogicUsuario().remove(usu);
		request.getRequestDispatcher("/ListaUsuario.jsp").forward(request, response);
		
	}
	
	

	
	
	
	

}
