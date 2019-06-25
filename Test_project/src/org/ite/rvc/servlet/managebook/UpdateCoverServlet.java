package org.ite.rvc.servlet.managebook;

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

import org.ite.rvc.book.Book;
import org.ite.rvc.dao.BookDAO;

/**
 * Servlet implementation class UpdateCoverServlet
 */
@WebServlet("/UpdateCoverServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateCoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCoverServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		InputStream inputStream = null;
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Book book = new Book();
		// Date date = new Date();

		try {
			int book_id = Integer.parseInt(request.getParameter("bookid").trim());

			book.setBook_id(book_id);

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("coverfile");
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}
			book.setImage_url(inputStream);

			book = BookDAO.update(book);

			if (book.isExecutionResult() == true) {
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
