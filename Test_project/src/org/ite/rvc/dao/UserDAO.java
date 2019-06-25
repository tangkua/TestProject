package org.ite.rvc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ite.rvc.user.User;
import org.ite.rvc.util.ConnectionManager;

public class UserDAO {
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	public static User insert(User bean) {
		String username = bean.getUsername();
		String password = bean.getPassword();
		String firstname = bean.getFirstName();
		String lastname = bean.getLastName();
		String gender = bean.getGender();
		String email = bean.getEmail();
		String usertype = bean.getUsertype();
		InputStream photo = bean.getPhoto();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("INSERT INTO TBL_USER (USERNAME,PASSWORD,F_NAME,L_NAME,GENDER,EMAIL,PHOTO,USERTYPE) VALUES (?,?,?,?,?,?,?,?);");

			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, firstname);
			pst.setString(4, lastname);
			pst.setString(5, gender);
			pst.setString(6, email);

			// fetches input stream of the upload file for the blob column
			pst.setBlob(7, photo);

			pst.setString(8, usertype);
			int i = pst.executeUpdate();
			if (i != 0) {
				bean.setExecutionResult(true);
			} else {
				bean.setExecutionResult(false);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}
		return bean;

	}

	public static User update(User bean) {
		// Integer userID = bean.getId();

		InputStream photo = bean.getPhoto();
		Integer userid = bean.getId();
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_USER SET  PHOTO=(?) where ID=(?) ;");

			pst.setBlob(1, photo);

			pst.setInt(2, userid);

			int i = pst.executeUpdate();
			if (i != 0) {
				bean.setExecutionResult(true);
			} else {
				bean.setExecutionResult(false);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}

		return bean;
	}

	public static User updateDetail(User bean) {
		// Integer userID = bean.getId();

		String password = bean.getPassword();
		String firstname = bean.getFirstName();
		String lastname = bean.getLastName();
		String gender = bean.getGender();
		String email = bean.getEmail();
		String usertype = bean.getUsertype();

		Integer userid = bean.getId();
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_USER SET PASSWORD=(?), F_NAME=(?), L_NAME=(?), GENDER=(?), EMAIL=(?), USERTYPE=(?) where ID=(?) ;");

			pst.setString(1, password);
			pst.setString(2, firstname);
			pst.setString(3, lastname);
			pst.setString(4, gender);
			pst.setString(5, email);
			pst.setString(6, usertype);

			pst.setInt(7, userid);

			int i = pst.executeUpdate();
			if (i != 0) {
				bean.setExecutionResult(true);
			} else {
				bean.setExecutionResult(false);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}

		return bean;
	}

	public static User getUserDetail(String username) {
		User bean = new User();
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM tbl_user WHERE username=(?);");
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()) {

				bean.setUsername(rs.getString("USERNAME"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setFirstName(rs.getString("F_NAME"));
				bean.setLastName(rs.getString("L_NAME"));
				bean.setGender(rs.getString("GENDER"));
				bean.setRegistrationdate(rs.getString("REGISTRATION_DATE"));
				// bean.setPhoto(rs.getBlob("photo"));
				bean.setEmail(rs.getString("EMAIL"));

				bean.setUsertype(rs.getString("USERTYPE"));
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}
		return bean;
	}

	public static boolean isUsernameExist(String username) {
		boolean result = false;
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("SELECT * FROM tbl_user WHERE username=(?);");
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}
		return result;
	}

	/*
	 * public static void deleteUnactivated() {
	 * 
	 * try { connection = ConnectionManager.getConnection(); pst = connection.prepareCall("DELETE FROM tbl_user WHERE activated=0"); pst.executeUpdate();
	 * 
	 * } catch (SQLException ex) { System.out.println(ex); } finally { releaseResource(); }
	 * 
	 * }
	 */

	private static void releaseResource() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
			rs = null;
		}

		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
			}
			pst = null;
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
			connection = null;
		}
	}
}
