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
import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class DisplayBookServlet
 */
@WebServlet("/DisplayBookServlet")
public class DisplayBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Chapter chapter=new Chapter();
		Book book= new Book();
		String userName = request.getParameter("username");
		String usertype = request.getParameter("usertype");

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_BOOK B,TBL_CHAPTER C where B.owner=(?) AND B.book_id = C.book_id;");
			pst.setString(1, userName);
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
				
				chapter.setBook_id(rs.getInt("book_id"));
				chapter.setChapter_name(rs.getString("chapter_name"));
				chapter.setChapter_datetime(rs.getString("chapter_detail"));
				chapter.setUser_id(rs.getInt("user_id"));
				chapter.setChapter_datetime(rs.getString("chapter_datetime"));
				chapter.setChapter_id(rs.getInt("chapter_id"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("book", book);
		request.setAttribute("chapter", chapter);
		if(usertype.equals("Normal User")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("showprofile.jsp");
			dispatcher.forward(request, response);
		}
		else if(usertype.equals("Blind User")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile_blind.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubBook book= new Book();
		
}
}
