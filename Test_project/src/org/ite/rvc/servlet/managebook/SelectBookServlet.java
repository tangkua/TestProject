package org.ite.rvc.servlet.managebook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ite.rvc.book.Book;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class SelectBookServlet
 */
@WebServlet("/SelectBookServlet")
public class SelectBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBookServlet() {
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
		Book book= new Book();
		
		
		String bookID = request.getParameter("bookid");

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_BOOK where book_id=(?);");
			pst.setString(1, bookID);
			rs = pst.executeQuery();
			if (rs.next()) {
				//response.getOutputStream().write(rs.getBytes("image_url"));
				book.setBook_id(rs.getInt("book_id"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setCategories_name(rs.getString("categories_name"));
				book.setDetail(rs.getString("book_description"));
				book.setPublisher(rs.getString("publisher"));
				book.setBookdate(rs.getString("book_date"));
				book.setOwner(rs.getString("owner"));
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("book", book);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("editbook.jsp");
		dispatcher.forward(request, response);

	}

}
