package org.ite.rvc.servlet.manageaudio;

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

import org.ite.rvc.audio.Audio;
import org.ite.rvc.dao.AudioDAO;

/**
 * Servlet implementation class InsertAudioServlet
 */
@WebServlet("/InsertAudioServlet")
@MultipartConfig(maxFileSize = 26177215)
public class InsertAudioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertAudioServlet() {
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
		Audio audio = new Audio();
		InputStream inputStream = null;

		try {
			
			int chap =Integer.parseInt(request.getParameter("selchap"));
			int chapid =Integer.parseInt(request.getParameter("chapter_id"));
			String categories = request.getParameter("categories");
			audio.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			audio.setName(request.getParameter("audioname"));
			audio.setDetail(request.getParameter("audiodescription"));
			audio.setAudiodate(dateFormat.format(date));
			if(chap==0){
				audio.setChapter_id(0);
			}
			if(chap==1){
				audio.setChapter_id(chapid);
			}
				
			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("fileaudio");
			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}
			System.out.println(categories);
			if (categories.equals("None")) {
				audio.setCategories_id(1);
			}
			else if (categories.equals("��áԨ ��Ҵ")) {
				audio.setCategories_id(2);
			}
			else if (categories.equals("������ ������ͧ")) {
				audio.setCategories_id(3);
			}
			else if (categories.equals("�ǧ �������� �ǧ����")) {
				audio.setCategories_id(4);
			}
			else if (categories.equals("�ͷ� ෤����� �ҹ¹��")) {
				audio.setCategories_id(5);
			}
			else if (categories.equals("��ͧ����� ����")) {
				audio.setCategories_id(6);
			}
			else if (categories.equals("��Ż� �����")) {
				audio.setCategories_id(7);
			}
			else if (categories.equals("����� �آ�Ҿ �������")) {
				audio.setCategories_id(8);
			}
			else if (categories.equals("��ʹ� ����ѵ���ʵ�� ��ǻ���ѵ�")) {
				audio.setCategories_id(9);
			}
			else if (categories.equals("�ɵ�")) {
				audio.setCategories_id(10);
			}
			else if (categories.equals("���ҵ�ҧ�����")) {
				audio.setCategories_id(11);
			}
			else if (categories.equals("��Ѫ�� �Ե�Է��")) {
				audio.setCategories_id(12);
			}
			else if (categories.equals("����ٹ")) {
				audio.setCategories_id(13);
			}
			else if (categories.equals("����֡��")) {
				audio.setCategories_id(14);
			}
			else if (categories.equals("˹ѧ�����")) {
				audio.setCategories_id(15);
			}
			else if (categories.equals("�����")) {
				audio.setCategories_id(16);
			}
			else if (categories.equals("Ὺ��")) {
				audio.setCategories_id(17);
			}
			else if (categories.equals("�Ե����")) {
				audio.setCategories_id(18);
			}
			else if (categories.equals("��ҹ ����觺�ҹ ������ǹ")) {
				audio.setCategories_id(19);
			}
			else if (categories.equals("ǧ��úѹ�ԧ")) {
				audio.setCategories_id(20);
			}
			else if (categories.equals("�Ѳ�ҵ���ͧ")) {
				audio.setCategories_id(21);
			}
			else if (categories.equals("�ǹ����")) {
				audio.setCategories_id(22);
			}

			audio.setFileaudio(inputStream);
			audio.setSize(filePart.getSize());
			// Insert User Information
			AudioDAO.insert(audio);

			if (audio.isExecutionResult() == true) {
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
