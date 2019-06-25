package org.ite.rvc.servlet.managebook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ite.rvc.book.Book;
import org.ite.rvc.dao.BookDAO;

/**
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
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
	        request.setCharacterEncoding("UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            //Create Bean
	            Book book = new Book();
	            int bookId = Integer.parseInt(request.getParameter("bookid"));

	            //Set Attribute
	            book.setBook_id(bookId);

	            //Delete Course
	            book = BookDAO.delete(book);
	            System.out.println("is"+book.getBook_id());
	            if (book.isExecutionResult() == true) {
	                response.sendRedirect("showprofile.jsp");
	            } else {
	                response.sendRedirect("error.jsp?errorCode=c002");
	            }
	        } catch (Throwable theException) {
	            System.out.println(theException);
	        }
		
	}

}
