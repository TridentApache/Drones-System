package DispacherServlet;
import DispacherServlet.UserDaoImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * Servlet implementation class DispacherServletlogin
 */
@WebServlet("/DispacherServletlogin")
public class DispacherServletlogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String target = "/UAVlist.jsp";  
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username == null || password ==null){
			username = "wangle";
			password = "1234";
		}
		request.setAttribute("USER",username);
		request.setAttribute("PASSWORD",password);
		UserDaoImpl Usercheck = new UserDaoImpl();
		boolean flag = Usercheck.checkUser((String)username,(String)password);
		
		
		  if (flag) {
		//验证成功，转向登录成功后的界面
		   request.getRequestDispatcher(target).forward(request, response);
		  } else {
		//验证失败，转向登录界面，并传递一个参数error，其值为yes
		   response.sendRedirect("login.jsp?error=yes");
		  }
	}
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispacherServletlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

}
