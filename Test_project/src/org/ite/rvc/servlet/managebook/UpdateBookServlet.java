package org.ite.rvc.servlet.managebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ite.rvc.dao.BookDAO;
import org.ite.rvc.book.Book;


/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
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
        
      
        Book book = new Book();
       
        
        
        try {
        	Integer bookid = Integer.parseInt(request.getParameter("bookid").trim());
        	//String username =request.getParameter("username");
            book.setBook_id(bookid);
            book.setCategories_id(Integer.parseInt(request.getParameter("categories")));
    		book.setTitle(request.getParameter("title"));
    		book.setPublisher(request.getParameter("publisher"));
    		book.setDetail(request.getParameter("description"));
    		book.setAuthor(request.getParameter("author"));
    		System.out.println(book.getCategories_name()+"b n");
    		
            book = BookDAO.updateDetail(book);

            if (book.isExecutionResult() == true) {
                response.sendRedirect("home.jsp");
                //response.sendRedirect("userdetail.jsp?username=" + user.getUsername());
            } else {
                response.sendRedirect("error.jsp?errorCode=c003");
            } 
        } catch (Throwable theException) {
            System.out.println(theException);
            
        } finally {
            out.close();
        }
	}

}
