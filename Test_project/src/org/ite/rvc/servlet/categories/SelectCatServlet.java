package org.ite.rvc.servlet.categories;

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

import org.ite.rvc.audio.Audio;
import org.ite.rvc.book.Book;
import org.ite.rvc.categories.Categories;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class SelectCatServlet
 */
@WebServlet("/SelectCatServlet")
public class SelectCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usertype = request.getParameter("usertype");
		String catname = request.getParameter("catname");
		int catid= Integer.parseInt(request.getParameter("catid")); 
		Audio audio = new Audio();
		Book book =new Book();
		Categories cat= new Categories();
		
		
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_CATEGORIES WHERE CATEGORIES_ID=(?);");
			pst.setInt(1, catid);
			rs = pst.executeQuery();
	        if (rs.next()) {
	        	cat.setCategories_id(rs.getInt("categories_id"));
	        	cat.setCategories_name(rs.getString("categories_name"));
	        	
	        	
	        
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		request.setAttribute("cat", cat);
		if(usertype.equals("Normal User")){
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("categories.jsp");
			dispatcher.forward(request, response);
		}
		else if(usertype.equals("Blind User")){
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("categories_blind.jsp");
			dispatcher.forward(request, response);
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
