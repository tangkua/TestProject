package org.ite.rvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ite.rvc.audio.Audio;
import org.ite.rvc.util.ConnectionManager;

public class SearchDAO {
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
	
	
	public static List<org.ite.rvc.audio.Audio> getAudioDetail(Audio bean) {
        String audioname = bean.getName();
        List<org.ite.rvc.audio.Audio> audios = new ArrayList<>();
        try {
            connection = ConnectionManager.getConnection();
            pst = connection.prepareCall("select * from tbl_audio where AUDIO_NAME LIKE ?");
            pst.setString(1,"%" + audioname + "%");

            rs = pst.executeQuery();
            while (rs.next()) {
                Audio audio = new Audio();

                audio.setName(rs.getString("audio_name"));
                audio.setDetail(rs.getString("audio_detail"));
                audio.setAudiodate(rs.getString("audio_datetime"));
                audio.setFileaudio(rs.getBinaryStream("audio_path"));
                audios.add(audio);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            releaseResource();
        }

        return audios;
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
