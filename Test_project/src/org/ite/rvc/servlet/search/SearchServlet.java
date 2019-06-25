package org.ite.rvc.servlet.search;

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
import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		// PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String searchtype=request.getParameter("searchtype");
		//int user_id = Integer.parseInt(request.getParameter("userid"));
		String keyword=request.getParameter("keyword");
		Chapter chapter=new Chapter();
		//User user=new User();
		Book book= new Book();
		Audio audio = new Audio();
		try {
			
			
			
			
			
			if(searchtype.equals("Audio")){
				connection = ConnectionManager.getConnection();
				pst = connection.prepareCall("select audio_id from tbl_audio where AUDIO_NAME like ?");
				pst.setString(1, "%" + keyword + "%");
				rs = pst.executeQuery();
				if (rs.next()) {
					audio.setId(rs.getInt("audio_id"));
			}
			}
			else if(searchtype.equals("Book")){
				connection = ConnectionManager.getConnection();
				pst = connection.prepareCall("select book_id from tbl_book where TITLE like ? or AUTHOR like ?");
				pst.setString(1, "%" + keyword + "%");
				pst.setString(2, "%" + keyword + "%");
				rs = pst.executeQuery();
				if (rs.next()) {
					book.setBook_id(rs.getInt("book_id"));
			}
			}
			
		/*	else if(searchtype.equals("Chapter")){
				connection = ConnectionManager.getConnection();
				pst = connection.prepareCall("select chapter_id from tbl_chapter where CHAPTER_NAME like ?");
				pst.setString(1, "%" + keyword + "%");
				rs = pst.executeQuery();
				if (rs.next()) {
					chapter.setChapter_id(rs.getInt("chapter_id"));
			}
			} 
			
			
			else if(searchtype.equals("User")){
				connection = ConnectionManager.getConnection();
				pst = connection.prepareCall("select id from tbl_user where USERNAME like ?");
				pst.setString(1, "%" + keyword + "%");
				rs = pst.executeQuery();
				if (rs.next()) {
					user.setId(rs.getInt("id"));
			}
			}*/
			//connection = ConnectionManager.getConnection();
			//pst = connection.prepareCall("select a.audio_id,c.chapter_id from tbl_audio a , tbl_chapter c where a.user_id=c.user_id and AUDIO_NAME like ? or CHAPTER_NAME like ?");
			 //pst = connection.prepareCall("select * from tbl_book, tbl_audio,tbl_user where TITLE LIKE ? or AUDIO_NAME like ? or USERNAME like ?");
			//pst.setString(1, "%" + keyword + "%");
			//pst.setString(2, "%" + keyword + "%");
			//pst.setString(3, "%" + keyword + "%");
			
			//rs = pst.executeQuery();
			//if (rs.next()) {
				
				//response.getOutputStream().write(rs.getBytes("image_url"));
				
				//book.setAuthor(rs.getString("author"));
				//book.setTitle(rs.getString("title"));
				//user.setUsername(rs.getString("username"));
				//audio.setName(rs.getString("audio_name"));
				//audio.setId(rs.getInt("audio_id"));
				//audio.setAudiodate(rs.getString("a.audio_datetime"));
				//audio.setCategories_id(rs.getInt("a.categories_id"));
	        	//audio.setUser_id(rs.getInt("a.user_id"));
	        	//audio.setDetail(rs.getString("a.audio_detail"));
	        	//audio.setChapter_id(rs.getInt("a.chapter_id"));
	        	//audio.setCountview(rs.getString("a.audio_countview"));
				//chapter.setChapter_id(rs.getInt("chapter_id"));
				
			
				//audios.add(audio);
				//books.add(book);
				//System.out.println(audios);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		request.setAttribute("audio", audio);
		//request.setAttribute("user", user);
		request.setAttribute("chapter", chapter);
		request.setAttribute("book", book);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
		dispatcher.forward(request, response);
	}

}
