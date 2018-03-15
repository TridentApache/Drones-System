package DispacherServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UAVway
 */
@WebServlet("/UAVway")
public class UAVway extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UAVway() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//先直接跳转
		String target2d = "/UAVmap2d.jsp";   
		String target3d = "/UAVmap3d.jsp";  
		String path2d = request.getParameter("path2d");
		String path3d = request.getParameter("path3d");
		String state = request.getParameter("state");
		String control = request.getParameter("control");
		
		//对应无人机id，选择进入
		// TODO Auto-generated method stub
		if(path2d != null){
			request.getRequestDispatcher(target2d).forward(request, response);
		}else
		if(path3d != null){
			request.getRequestDispatcher(target3d).forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub			
		
	}

}
