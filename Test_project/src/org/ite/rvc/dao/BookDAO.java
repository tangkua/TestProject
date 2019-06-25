package org.ite.rvc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ite.rvc.book.Book;
import org.ite.rvc.util.ConnectionManager;

public class BookDAO {
	static Connection connection = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;
	
	public static Book insert(Book bean) {
		String title = bean.getTitle();
		String author = bean.getAuthor();
		InputStream coverbook = bean.getImage_url();
		String publisher = bean.getPublisher();
		Integer categories = bean.getCategories_id();
		String book_detail=  bean.getDetail();
		String owner=bean.getOwner();
		
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("INSERT INTO tbl_book (TITLE,AUTHOR,BOOK_DESCRIPTION,PUBLISHER,CATEGORIES_ID,IMAGE_URL,OWNER) VALUES (?,?,?,?,?,?,?);");

			
			pst.setString(1, title);
			pst.setString(2, author);
			//pst.setString(3, book_date);
			pst.setString(3,book_detail);
			pst.setString(4, publisher);
			pst.setInt(5, categories);
			pst.setBlob(6, coverbook);
			pst.setString(7, owner);
	
			
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
	
	public static Book updateDetail(Book bean) {
		Integer bookid = bean.getBook_id();
		String title = bean.getTitle();
		String author = bean.getAuthor();
		String detail = bean.getDetail();
		String publisher = bean.getPublisher();
		Integer categories=bean.getCategories_id();
		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_BOOK SET TITLE=(?), AUTHOR=(?), BOOK_DESCRIPTION=(?), PUBLISHER=(?), CATEGORIES_ID=(?) where BOOK_ID=(?) ;");

			pst.setString(1, title);
			pst.setString(2, author);
			pst.setString(3, detail);
			pst.setString(4, publisher);
			pst.setInt(5, categories);
			pst.setInt(6, bookid);

		

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
	
	public static Book update(Book bean) {
		// Integer userID = bean.getId();

		InputStream file = bean.getImage_url();
		Integer bookid = bean.getBook_id();
		

		try {
			connection = ConnectionManager.getConnection();
			pst = connection.prepareCall("UPDATE TBL_BOOK SET IMAGE_URL=(?) where BOOK_ID=(?) ;");

			pst.setBlob(1, file);

			pst.setInt(2, bookid);

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
	
	public static Book delete(Book bean) {
        Integer bookid = bean.getBook_id();

        try {
            connection = ConnectionManager.getConnection();
            pst = connection.prepareCall("DELETE FROM tbl_book WHERE book_id=(?)");
            pst.setInt(1,bookid );

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
