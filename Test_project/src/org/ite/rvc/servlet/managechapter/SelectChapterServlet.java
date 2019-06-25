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
 * Servlet implementation class SelectChapterServlet
 */
@WebServlet("/SelectChapterServlet")
public class SelectChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectChapterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Chapter chapter = new Chapter();

		String chapterID = request.getParameter("chapterid");

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_CHAPTER where chapter_id=(?);");
			pst.setString(1, chapterID);
			rs = pst.executeQuery();
			if (rs.next()) {
				// response.getOutputStream().write(rs.getBytes("image_url"));
				chapter.setChapter_id(rs.getInt("chapter_id"));
				chapter.setBook_id(rs.getInt("book_id"));
				chapter.setChapter_description(rs.getString("chapter_detail"));
				chapter.setUser_id(rs.getInt("user_id"));
				chapter.setChapter_name(rs.getString("chapter_name"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("chapter", chapter);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("editchapter.jsp");
		dispatcher.forward(request, response);

	}

}
