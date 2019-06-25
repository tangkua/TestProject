package org.ite.rvc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ite.rvc.chapter.Chapter;
import org.ite.rvc.util.ConnectionManager;

public class ChapterDAO {
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
	
	public static Chapter insert(Chapter bean) {
		Integer book_id = bean.getBook_id();
		Integer user_id = bean.getUser_id();
		InputStream filechapter = bean.getChapter_path();
		String chapter_name = bean.getChapter_name();
		String chapter_detail = bean.getChapter_description();
		Long chapter_size=  bean.getChapter_size();
		
		
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("INSERT INTO tbl_chapter (CHAPTER_NAME,BOOK_ID,USER_ID,CHAPTER_SIZE,CHAPTER_DETAIL,CHAPTER_PATH) VALUES (?,?,?,?,?,?);");

			
			pst.setString(1, chapter_name);
			pst.setInt(2, book_id);
			//pst.setString(3, book_date);
			pst.setInt(3,user_id);
			pst.setLong(4, chapter_size);
			pst.setString(5, chapter_detail);
			pst.setBlob(6, filechapter);
	
			
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
	
	
	public static Chapter updateDetail(Chapter bean) {
		Integer chapterid = bean.getChapter_id();
		String chaptername = bean.getChapter_name();
		String chapterdetail = bean.getChapter_description();
		Integer bookid = bean.getBook_id();
		
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_CHAPTER SET CHAPTER_NAME=(?), CHAPTER_DETAIL=(?), BOOK_ID=(?) where CHAPTER_ID=(?) ;");

			pst.setString(1, chaptername);
			pst.setString(2, chapterdetail);
			pst.setInt(3, bookid);
			pst.setInt(4, chapterid);

		

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
	
	
	public static Chapter update(Chapter bean) {
		// Integer userID = bean.getId();

		InputStream file = bean.getChapter_path();
		Integer chapterid = bean.getChapter_id();
		

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_CHAPTER SET CHAPTER_PATH=(?) where CHAPTER_ID=(?) ;");

			pst.setBlob(1, file);

			pst.setInt(2, chapterid);

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
	
	public static Chapter delete(Chapter bean) {
        Integer chapterid = bean.getChapter_id();

        try {
            connection = ConnectionManager.getConnection();
            pst = connection.prepareCall("DELETE FROM tbl_chapter WHERE chapter_id=(?)");
            pst.setInt(1,chapterid);

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
