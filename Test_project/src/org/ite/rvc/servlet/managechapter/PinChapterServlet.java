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

import org.ite.rvc.book.Book;
import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.chapter.ChapterUser;
import org.ite.rvc.dao.ChapterUserDAO;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class PinChapterServlet
 */
@WebServlet("/PinChapterServlet")
public class PinChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PinChapterServlet() {
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
		
		String pin = request.getParameter("pinchapter");
		ChapterUser chappin = new ChapterUser();
		int user_id = Integer.parseInt(request.getParameter("userid"));
		int chapter_id = Integer.parseInt(request.getParameter("chapterid"));
		
		Book book= new Book();
		Chapter chapter =new Chapter();
		try {	
			
					chappin.setChapter_id(chapter_id);
					chappin.setPin_chapter(pin);
					chappin.setUser_id(user_id);
					ChapterUserDAO.updatePin(chappin);
					
				}
			
			
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_BOOK B,TBL_CHAPTER C where B.book_id = C.book_id AND chapter_id=(?) ;");
			pst.setInt(1, chapter_id);
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
				chapter.setChapter_description(rs.getString("chapter_detail"));
				chapter.setUser_id(rs.getInt("user_id"));
				chapter.setChapter_datetime(rs.getString("chapter_datetime"));
				chapter.setChapter_id(rs.getInt("chapter_id"));
				chapter.setChapter_path(rs.getBinaryStream("chapter_path"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("chapter", chapter);
		request.setAttribute("book", book);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("chapterdetail.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
