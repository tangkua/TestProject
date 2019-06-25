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
import org.ite.rvc.audio.AudioUser;
import org.ite.rvc.dao.AudioUserDAO;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class SelectAudioServlet
 */
@WebServlet("/SelectAudioServlet")
public class SelectAudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectAudioServlet() {
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
		int audioid = Integer.parseInt(request.getParameter("audioid"));
		Audio audio = new Audio();
		AudioUser audiouser = new AudioUser();
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
	        
	        audiouser.setAudio_id(audioid);
			audiouser.setUser_id(Integer.parseInt(request.getParameter("userid")));
			AudioUserDAO.insert(audiouser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("audio", audio);
		request.setAttribute("audiouser", audiouser);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("displayaudio.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
