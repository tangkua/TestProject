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
 * Servlet implementation class UpdateChapterServlet
 */
@WebServlet("/UpdateChapterServlet")
public class UpdateChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateChapterServlet() {
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
        
      
        Chapter chapter= new Chapter();
       
        
        
        try {
        	Integer chapterid = Integer.parseInt(request.getParameter("chapterid").trim());
        	//String username =request.getParameter("username");
            chapter.setChapter_id(chapterid);
            chapter.setBook_id(Integer.parseInt(request.getParameter("book_id")));
            chapter.setChapter_description(request.getParameter("chapterdescription"));
            chapter.setChapter_name(request.getParameter("chaptername"));
            
  
    		
            chapter = ChapterDAO.updateDetail(chapter);
      		System.out.println(chapter.getChapter_name()+"test");
      		
            if (chapter.isExecutionResult() == true) {
                response.sendRedirect("home.jsp");
                //response.sendRedirect("userdetail.jsp?username=" + user.getUsername());
            } else {
                response.sendRedirect("error.jsp?errorCode=c003");
            } 
        } catch (Throwable theException) {
            System.out.println(theException);
            
        } finally {
            out.close();
        }
		
	}

}
