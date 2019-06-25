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
 * Servlet implementation class ShowAudioServlet
 */
@WebServlet("/ShowAudioServlet")
public class ShowAudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAudioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String audioid = request.getParameter("audioid");
		Audio audio = new Audio();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT AUDIO_PATH FROM TBL_AUDIO WHERE AUDIO_ID=(?);");
			pst.setString(1, audioid);
			rs = pst.executeQuery();
	        if (rs.next()) {
	            response.getOutputStream().write(rs.getBytes("AUDIO_PATH"));
	            audio.setFileaudio(rs.getBinaryStream("AUDIO_PATH"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("audio", audio);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("watchaudio.jsp");
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
