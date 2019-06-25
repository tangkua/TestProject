package org.ite.rvc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ite.rvc.audio.Audio;
import org.ite.rvc.util.ConnectionManager;

public class AudioDAO {

	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	public static Audio insert(Audio bean) {
		String audioname = bean.getName();
		String audiodetail = bean.getDetail();
		InputStream audiofile = bean.getFileaudio();
		long audiosize = bean.getSize();
		Integer user_id = bean.getUser_id();
		Integer categories = bean.getCategories_id();
		Integer chap_id=bean.getChapter_id();
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("INSERT INTO tbl_audio (AUDIO_NAME,AUDIO_DETAIL,AUDIO_PATH,AUDIO_SIZE,USER_ID,CATEGORIES_ID,CHAPTER_ID) VALUES (?,?,?,?,?,?,?);");

			//pst.setBlob(1, audiofile);
			pst.setString(1, audioname);
			//pst.setLong(3, audiosize);
			pst.setString(2, audiodetail);
			pst.setBlob(3, audiofile);
			pst.setLong(4, audiosize);
			pst.setInt(5, user_id);
			pst.setInt(6, categories);
			pst.setInt(7, chap_id);
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

	public static Audio update(Audio bean) {
		// Integer userID = bean.getId();

		InputStream audiofile = bean.getFileaudio();
		Integer audioid = bean.getId();
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_AUDIO SET  AUDIO_PATH=(?) where AUDIO_ID=(?) ;");

			pst.setBlob(1, audiofile);

			pst.setInt(2, audioid);

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

	public static Audio updateDetail(Audio bean) {
		// Integer userID = bean.getId();

		String audioname = bean.getName();
		String audiodetail = bean.getDetail();
		Integer categoriesid = bean.getCategories_id();
		Integer chapterid = bean.getChapter_id();
		Integer audioid = bean.getId();
		//Integer userid = bean.getUser_id();
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_AUDIO SET CHAPTER_ID=(?), AUDIO_NAME=(?), AUDIO_DETAIL=(?), CATEGORIES_ID=(?) where AUDIO_ID=(?) ;");

			pst.setInt(1, chapterid);
			pst.setString(2, audioname);
			pst.setString(3, audiodetail);
			pst.setInt(4, categoriesid);
			pst.setInt(5, audioid);
			

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

	
	public static Audio delete(Audio bean) {
        Integer audioid = bean.getId();

        try {
            connection = ConnectionManager.getConnection();
            pst = connection.prepareCall("DELETE FROM tbl_audio WHERE audio_id=(?)");
            pst.setInt(1,audioid);

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
	
	
	
	
	private static void releaseResource() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			rs = null;
		}

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
			}
			pst = null;
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
			connection = null;
		}
	}

}
