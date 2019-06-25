package org.ite.rvc.servlet.manageaudio;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.ite.rvc.audio.Audio;
import org.ite.rvc.dao.AudioDAO;

/**
 * Servlet implementation class UpdateAudioFileServlet
 */
@WebServlet("/UpdateAudioFileServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateAudioFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAudioFileServlet() {
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
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        InputStream inputStream = null;
       // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Audio audio = new Audio();
       // Date date = new Date();
        
        
        try {
        	int audioid = Integer.parseInt(request.getParameter("audioid").trim());
        	
            audio.setId(audioid);
            //user.setUsername(request.getParameter("U"));
    		
    	
    		
    		
    		System.out.println("is"+audioid);
    		
    		
    		
    		// obtains the upload file part in this multipart request
    		Part filePart = request.getPart("audiofile");
    		if (filePart != null) {
    			// prints out some information for debugging
    			System.out.println(filePart.getName());
    			System.out.println(filePart.getSize());
    			System.out.println(filePart.getContentType());

    			// obtains input stream of the upload file
    			inputStream = filePart.getInputStream();
    		}
    		audio.setFileaudio(inputStream);
            
            audio = AudioDAO.update(audio);

            if (audio.isExecutionResult() == true) {
                response.sendRedirect("home.jsp");
                //response.sendRedirect("userdetail.jsp?username=" + user.getUsername());
            } /*else {
                response.sendRedirect("error.jsp?errorCode=c003");
            } */
        } catch (Throwable theException) {
            System.out.println(theException);
            
        } finally {
            out.close();
        }
	}

}
