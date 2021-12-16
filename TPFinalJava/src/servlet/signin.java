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
 * Servlet implementation class signin
 */
@WebServlet({ "/signin", "/SIGNIN", "/SignIn", "/Signin", "/signIn" })
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("get at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = new Usuario();
		LogicUsuario ctrl = new LogicUsuario();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Validar usuario y contrasenia
		
		user.setUsername(username);
		user.setPassword(password);
		
		user = ctrl.validate(user);
		if(user != null) {
			request.getSession().setAttribute("usuario", user);
			request.getRequestDispatcher("./ConsultaAnalisis.jsp").forward(request, response);
			
		}else {
			response.getWriter().append("Usuario o contraseña incorrectos");
		}
		
		
	}

}
