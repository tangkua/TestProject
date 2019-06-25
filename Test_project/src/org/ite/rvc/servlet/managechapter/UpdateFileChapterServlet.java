package org.ite.rvc.servlet.managechapter;

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

import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.dao.ChapterDAO;

/**
 * Servlet implementation class UpdateFileChapterServlet
 */
@WebServlet("/UpdateFileChapterServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateFileChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFileChapterServlet() {
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
		Chapter chapter = new Chapter();
		// Date date = new Date();

		try {
			int chapterid = Integer.parseInt(request.getParameter("chapterid").trim());

			chapter.setChapter_id(chapterid);

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("chapterfile");
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}
			chapter.setChapter_path(inputStream);

			chapter = ChapterDAO.update(chapter);
			if (chapter.isExecutionResult() == true) {
				response.sendRedirect("home.jsp");
				// response.sendRedirect("userdetail.jsp?username=" +
				// user.getUsername());
			} /*
			 * else { response.sendRedirect("error.jsp?errorCode=c003"); }
			 */
		} catch (Throwable theException) {
			System.out.println(theException);

		} finally {
			out.close();
		}
		
	}

}
