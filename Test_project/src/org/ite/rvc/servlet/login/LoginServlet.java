package org.ite.rvc.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ite.rvc.user.User;
import org.ite.rvc.util.ConnectionManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("U");
		String password = request.getParameter("P");

		System.out.println("username = " + username);
		System.out.println("password = " + password);

		User user = new User();
		boolean loginValid = false;
		// ที่เพิ่มใหม่สำหรับ session
		HttpSession session = request.getSession();

		// Validate Input
		Map<String, String> errors = new HashMap<String, String>();

		if (username.trim().length() == 0) {
			errors.put("username", "กรุณากรอกชื่อผู้ใช้");
		}

		if (password.trim().length() == 0) {
			errors.put("password", "กรุณากรอกรหัสผ่าน");
		}

		try {
			connection = ConnectionManager.getConnection();
			pst = connection
					.prepareCall("SELECT * FROM TBL_USER WHERE USERNAME=(?) AND PASSWORD=(?);");
			pst.setString(1, username);
			pst.setString(2, password);

			rs = pst.executeQuery();
			boolean more = rs.next();

			if (more) {
				loginValid = true;
				user.setId(Integer.parseInt(rs.getString("ID")));
				user.setFirstName(rs.getString("F_NAME"));
				user.setLastName(rs.getString("L_NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhoto(rs.getUnicodeStream("PHOTO"));
				user.setUsername(rs.getString("USERNAME"));
				user.setUsertype(rs.getString("USERTYPE"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setId(rs.getInt("ID"));

			}
		} catch (Exception e) {
			System.out
					.println("Log In failed: An Exception has occurred! " + e);
		}
		// เพิ่มgetSession() สำหรับsession
		// request.getSession().setAttribute("user", user); same
		session.setAttribute("user", user);
		session.setAttribute("loginValid", loginValid);
		if (user.getUsertype().equals("Normal User")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}
		else if (user.getUsertype().equals("Blind User")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/hometest.jsp");
			dispatcher.forward(request, response);
		}

	}

}
