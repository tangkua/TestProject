package org.ite.rvc.servlet.managebook;

import java.io.IOException;
import java.io.InputStream;
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

import org.ite.rvc.book.Book;
import org.ite.rvc.dao.BookDAO;

/**
 * Servlet implementation class InsertBookServlet
 */
@WebServlet("/InsertBookServlet")
@MultipartConfig(maxFileSize = 16177215)
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBookServlet() {
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Book books = new Book();
		InputStream inputStream = null; // input stream of the upload file
	
		try{
			Part filePart = request.getPart("cover");
			books.setTitle(request.getParameter("title"));
			books.setAuthor(request.getParameter("author"));
			books.setBookdate(dateFormat.format(date));
			books.setDetail(request.getParameter("description"));
			books.setPublisher(request.getParameter("publisher"));
			books.setCategories_id(Integer.parseInt(request.getParameter("categories")));
			books.setOwner(request.getParameter("user_name"));
			
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}else
			{System.out.println("file is null");}
			books.setImage_url(inputStream);
			
			BookDAO.insert(books);
			 if (books.isExecutionResult() == true) {
	                response.sendRedirect("home.jsp");
	            } else {
	                response.sendRedirect("error.jsp?errorCode=c002");
	            }
		}catch (Throwable theException) {
            System.out.println(theException);
        }
		
		}

}
