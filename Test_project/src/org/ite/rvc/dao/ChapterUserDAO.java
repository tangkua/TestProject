package org.ite.rvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ite.rvc.chapter.ChapterUser;
import org.ite.rvc.util.ConnectionManager;

public class ChapterUserDAO {
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
	
	public static ChapterUser insert(ChapterUser bean) {
		
		Integer chapid=bean.getChapter_id();
		Integer userid=bean.getUser_id();
		
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("INSERT INTO TBL_CHAPTERUSER (FAVORITE_CHAPTER,USER_ID,CHAPTER_ID) VALUES (?,?,?);");

			
			pst.setInt(1,0);
			pst.setInt(2,userid);
			//pst.setString(3, book_date);
			pst.setInt(3,chapid);
			
	
			
			int i = pst.executeUpdate();
			if (i != 0) {
				bean.setExecuteResult(true);
			} else {
				bean.setExecuteResult(false);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}
		return bean;

	}
	
	public static ChapterUser update(ChapterUser bean) {
		// Integer userID = bean.getId();

		
		Integer favchap = bean.getFavorite_chapter();
		Integer userid=bean.getUser_id();
		Integer chapid=bean.getChapter_id();
		Integer bookid=bean.getBook_id();
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_CHAPTERUSER SET FAVORITE_CHAPTER=(?),BOOK_ID=(?) where USER_ID=(?) AND CHAPTER_ID=(?);");

			pst.setInt(1,favchap);
			pst.setInt(2,bookid);
			pst.setInt(3,userid);
			pst.setInt(4,chapid);

			int i = pst.executeUpdate();
			if (i != 0) {
				bean.setExecuteResult(true);
			} else {
				bean.setExecuteResult(false);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}

		return bean;
	}
	

	public static ChapterUser updatePin(ChapterUser bean) {
		// Integer userID = bean.getId();

		
		String pinchap = bean.getPin_chapter();
		Integer userid=bean.getUser_id();
		Integer chapid=bean.getChapter_id();
		
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_CHAPTERUSER SET PIN_CHAPTER=(?) where USER_ID=(?) AND CHAPTER_ID=(?);");

			pst.setString(1,pinchap);
			pst.setInt(2,userid);
			pst.setInt(3,chapid);

			int i = pst.executeUpdate();
			if (i != 0) {
				bean.setExecuteResult(true);
			} else {
				bean.setExecuteResult(false);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			releaseResource();
		}

		return bean;
	}
	
	public static ChapterUser delete(ChapterUser bean) {
        Integer userid = bean.getUser_id();
        Integer chapterid = bean.getChapter_id();
        
        try {
            connection = ConnectionManager.getConnection();
            pst = connection.prepareCall("DELETE FROM tbl_audio WHERE user_id=(?) and chapter_id=(?) ;");
            pst.setInt(1,userid);
            pst.setInt(1,chapterid);
            int i = pst.executeUpdate();
            if (i != 0) {
                bean.setExecuteResult(true);
            } else {
                bean.setExecuteResult(false);
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
