package org.ite.rvc.servlet.managechapter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.dao.ChapterDAO;

/**
 * Servlet implementation class DeleteChapterServlet
 */
@WebServlet("/DeleteChapterServlet")
public class DeleteChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteChapterServlet() {
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
            Chapter chapter = new Chapter();
            int chapterId = Integer.parseInt(request.getParameter("chapterid"));

            //Set Attribute
            chapter.setChapter_id(chapterId);

            //Delete Course
            chapter = ChapterDAO.delete(chapter);
            System.out.println("is"+chapter.getBook_id());
            if (chapter.isExecutionResult() == true) {
                response.sendRedirect("showprofile.jsp");
            } else {
                response.sendRedirect("error.jsp?errorCode=c002");
            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
	}

}
