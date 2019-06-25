package org.ite.rvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ite.rvc.audio.AudioUser;
import org.ite.rvc.util.ConnectionManager;

public class AudioUserDAO {
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
	
	public static AudioUser insert(AudioUser bean) {
		
		Integer audioid=bean.getAudio_id();
		Integer userid=bean.getUser_id();
		
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("INSERT INTO TBL_AUDIOUSER (FAVORITE_AUDIO,USER_ID,AUDIO_ID) VALUES (?,?,?);");

			
			pst.setInt(1,0);
			pst.setInt(2,userid);
			//pst.setString(3, book_date);
			pst.setInt(3,audioid);
			
	
			
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
	
	public static AudioUser update(AudioUser bean) {
		// Integer userID = bean.getId();

		
		Integer favchap = bean.getFavorite_audio();
		Integer userid=bean.getUser_id();
		Integer audioid=bean.getAudio_id();
		
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_AUDIOUSER SET FAVORITE_AUDIO=(?) where USER_ID=(?) AND AUDIO_ID=(?);");

			pst.setInt(1,favchap);
			
			pst.setInt(2,userid);
			pst.setInt(3,audioid);

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
	
	public static AudioUser delete(AudioUser bean) {
        Integer userid = bean.getUser_id();
        Integer audioid = bean.getAudio_id();
        
        try {
            connection = ConnectionManager.getConnection();
            pst = connection.prepareCall("DELETE FROM tbl_audio WHERE user_id=(?) and id=(?) ;");
            pst.setInt(1,userid);
            pst.setInt(1,audioid);
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
	
	public static AudioUser updatePin(AudioUser bean) {
		// Integer userID = bean.getId();

		
		String pinaudio = bean.getPin_audio();
		Integer userid=bean.getUser_id();
		Integer audioid=bean.getAudio_id();
		
		// String registrationdate = bean.getRegistrationdate();

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_AUDIOUSER SET PIN_AUDIO=(?) where USER_ID=(?) AND AUDIO_ID=(?);");

			pst.setString(1,pinaudio);
			pst.setInt(2,userid);
			pst.setInt(3,audioid);

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
