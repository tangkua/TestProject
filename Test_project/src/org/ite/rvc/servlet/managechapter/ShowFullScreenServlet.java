package org.ite.rvc.servlet.managechapter;

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

import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class ShowFullScreenServlet
 */
@WebServlet("/ShowFullScreenServlet")
public class ShowFullScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFullScreenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Chapter chapter = new Chapter();

		String chapter_id = request.getParameter("chapter_id");
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_CHAPTER where chapter_id=(?) ;");
			pst.setString(1, chapter_id);
			rs = pst.executeQuery();
			if (rs.next()) {
				response.getOutputStream().write(rs.getBytes("chapter_path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("chapter", chapter);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("PdfFullscreen.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
