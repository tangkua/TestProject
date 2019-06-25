package org.ite.rvc.servlet.manageuser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.ite.rvc.dao.UserDAO;
import org.ite.rvc.user.User;


/**
 * Servlet implementation class InsertUserServlet
 */
@WebServlet("/InsertUserServlet")
@MultipartConfig(maxFileSize = 16177215)
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertUserServlet() {
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
		User user = new User();
		InputStream inputStream = null; // input stream of the upload file

		
		
		String username = request.getParameter("U");
		String password = request.getParameter("P");
		String repassword = request.getParameter("RP");
		String name = request.getParameter("N");
		String lastname = request.getParameter("L");
		String gender = request.getParameter("optionsRadiosGender");
		String email = request.getParameter("E");
		String usertype = request.getParameter("optionsUsertype");
		// Check User
		if (UserDAO.isUsernameExist(request.getParameter("U"))) {
			return;
		}

		// Validate Password
		if (!validatePassword(request.getParameter("P"), request.getParameter("RP"))) {
			response.sendRedirect("error.jsp?errorCode=t004");
		}

		// Validate Input
		Map<String, String> errors = new HashMap<String, String>();
		if (username.trim().length() == 0) {
			errors.put("username", "กรุณากรอกชื่อผู้ใช้");
		}
		if (password.trim().length() == 0) {
			errors.put("password", "กรุณากรอกรหัสผ่าน");
		}
		if (!password.equals(repassword)) {
			errors.put("repassword", "รหัสผ่านไม่ตรงกัน");
		}
		if (name.trim().length() == 0) {
			errors.put("name", "กรุณากรอกชื่อ");
		}
		if (lastname.trim().length() == 0) {
			errors.put("lastname", "กรุณากรอกนามสกุล");
		}
		if (name.trim().length() == 0) {
			errors.put("name", "กรุณากรอกชื่อ");
		}
		if (email.trim().length() == 0) {
			errors.put("E", "กรุณากรอกอีเมลล์");
		}
		if (gender==null) {
			errors.put("gender", "กรุณาเลือกเพศ");
		}
		if (usertype==null) {
			errors.put("usertype", "กรุณาเลือกประเภทของผู้ใช้");
		}
		
		//check error
		if(errors.size()>0) {
			RequestDispatcher rd;
			rd=request.getRequestDispatcher("registrationform.jsp");
			rd.forward(request, response);
			return;
		}
		
		//activate
		boolean activated = false;
		String activateCode=UUID.randomUUID().toString();
		
		
		
		// set data to bean
		user.setUsername(request.getParameter("U"));
		user.setPassword(request.getParameter("P"));
		user.setFirstName(request.getParameter("N"));
		user.setLastName(request.getParameter("L"));
		user.setGender(request.getParameter("optionsRadiosGender"));
		user.setRegistrationdate(dateFormat.format(date));
		user.setEmail(request.getParameter("E"));

		user.setUsertype(request.getParameter("optionsUsertype"));
		user.setActivated(activated);
		user.setActivate_code(activateCode);
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}
		user.setPhoto(inputStream);
		// Insert User Information
		UserDAO.insert(user);
	/*	 if(UserDAO.insert(user) != null) {
			UserDAO.deleteUnactivated();
			
		}
		else{
			errors.put("username", "ชื่อซ้พ");
		}
		
		if(errors.size()==0){
			String from = "tknatcha@gmail.com";
		} */
			 
		// If Insertion fail then redirect to error page

		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);

	}

	private boolean validatePassword(String pass1, String pass2) {
		return pass1.equals(pass2);
	}

}
