package org.ite.rvc.servlet.managechapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class InsertChapterServlet
 */
@WebServlet("/InsertChapterServlet")
@MultipartConfig(maxFileSize = 16177215)
public class InsertChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertChapterServlet() {
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Chapter chapter = new Chapter();
		InputStream inputStream = null; // input stream of the upload file

		try {
			Part filePart = request.getPart("filechapter");
			chapter.setBook_id(Integer.parseInt(request.getParameter("book_id")));
			chapter.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			chapter.setChapter_datetime(dateFormat.format(date));
			chapter.setChapter_description(request.getParameter("chapterdescription"));
			chapter.setChapter_name(request.getParameter("chaptername"));
			System.out.println(chapter.getBook_id());
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			} else {
				System.out.println("file is null");
			}
			chapter.setChapter_path(inputStream);
			chapter.setChapter_size(filePart.getSize());
			ChapterDAO.insert(chapter);
			if (chapter.isExecutionResult() == true) {
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("error.jsp?errorCode=c002");
            }
		} catch (Throwable theException) {
			System.out.println(theException);
		} finally {
			out.close();
		}
	}

}
