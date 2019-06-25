package org.ite.rvc.servlet.manageaudio;

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
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class EditAudioServlet
 */
@WebServlet("/EditAudioServlet")
public class EditAudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAudioServlet() {
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
		int audioid = Integer.parseInt(request.getParameter("audioid"));
		Audio audio = new Audio();
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM TBL_AUDIO WHERE AUDIO_ID=(?);");
			pst.setInt(1, audioid);
			rs = pst.executeQuery();
	        if (rs.next()) {
	        	audio.setAudiodate(rs.getString("audio_datetime"));
	        	audio.setCategories_id(rs.getInt("categories_id"));
	        	audio.setUser_id(rs.getInt("user_id"));
	        	audio.setName(rs.getString("audio_name"));
	        	audio.setDetail(rs.getString("audio_detail"));
	        	audio.setChapter_id(rs.getInt("chapter_id"));
	        	audio.setId(rs.getInt("audio_id"));
	        }
	        
	       
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("audio", audio);
	
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("editaudio.jsp");
		dispatcher.forward(request, response);
		
	}

}
