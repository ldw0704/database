package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.NoticeVO;

public class NoticeDAO {

	public void create(NoticeVO vo) {

		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer()
				.append(" INSERT INTO notice ")
				.append(" (no, title, content, inputDate) ")
				.append(" VALUES (null, ?, ?, now()) ");

		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());			

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public List<NoticeVO> read(){
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer()
				.append("SELECT * FROM notice " );
		
		PreparedStatement stmt = null;
		Connection conn = null;
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		ResultSet rs =  null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new NoticeVO(
						rs.getInt("no"), 
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("inputDate")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
