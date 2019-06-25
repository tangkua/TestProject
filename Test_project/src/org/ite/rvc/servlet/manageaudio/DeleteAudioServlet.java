package org.ite.rvc.servlet.manageaudio;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ite.rvc.audio.Audio;
import org.ite.rvc.dao.AudioDAO;

/**
 * Servlet implementation class DeleteAudioServlet
 */
@WebServlet("/DeleteAudioServlet")
public class DeleteAudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAudioServlet() {
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
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Create Bean
            Audio audio = new Audio();
            int audioId = Integer.parseInt(request.getParameter("audioid"));

            //Set Attribute
            audio.setId(audioId);

            //Delete Course
            audio = AudioDAO.delete(audio);
            System.out.println("is"+audio.getId());
            if (audio.isExecutionResult() == true) {
                response.sendRedirect("showprofile.jsp");
            } else {
                response.sendRedirect("error.jsp?errorCode=c002");
            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
	}

}
