package org.ite.rvc.servlet.manageuser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ite.rvc.dao.UserDAO;
import org.ite.rvc.user.User;

/**
 * Servlet implementation class UpdateUserDetailServlet
 */
@WebServlet("/UpdateUserDetailServlet")
public class UpdateUserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        
       // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        User user = new User();
       // Date date = new Date();
        
        
        try {
        	int userid = Integer.parseInt(request.getParameter("userID").trim());
        	
            user.setId(userid);
            user.setUsername(request.getParameter("U"));
    		user.setPassword(request.getParameter("P"));
    		user.setFirstName(request.getParameter("N"));
    		user.setLastName(request.getParameter("L"));
    		user.setGender(request.getParameter("optionsRadiosGender"));
    		
    		user.setEmail(request.getParameter("E"));
    		System.out.println("is"+userid);
    		System.out.println("is"+user.getFirstName());
    		user.setUsertype(request.getParameter("optionsUsertype"));
    		
    		
            
            user = UserDAO.updateDetail(user);

            if (user.isExecutionResult() == true) {
                response.sendRedirect("home.jsp");
                //response.sendRedirect("userdetail.jsp?username=" + user.getUsername());
            } /*else {
                response.sendRedirect("error.jsp?errorCode=c003");
            } */
        } catch (Throwable theException) {
            System.out.println(theException);
            
        } finally {
            out.close();
        }
	}

}
