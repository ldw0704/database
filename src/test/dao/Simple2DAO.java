package test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Simple2DAO {

	/**
	 * C:create() :등록 접근지정자 : public param : 등록될값 return : 없음
	 */
	public void create() {
		// 코드작성
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		sql.append("\n INSERT INTO exam");
		sql.append("\n (varcharTest, charTest, doubleTest, dateTest, dateTimeTest)");
		sql.append("\n VALUES ('가변폭문자열', '고정폭문자열', 1.1, curdate(), now())");
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			// preparedStatement(sql작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
			// 닫기
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * R:read() : 조회 접근지정자 : public param : 조회할값 return : List
	 */
	public List read() {
		// 코드작성
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM exam ");

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			// preparedStatement(sql작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			// 결과처리(Select문만 resutlSet객체 리턴
			rs = stmt.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 닫기
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	/**
	 * U:update() :수정 접근지정자 : public param : 수정할값 return : 없음
	 */
	public void update() {
		// 코드작성
		String url = "jdbc:mysql://localhost:3306/smart?characterEncodign=utf-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";
		StringBuffer sql = new StringBuffer();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql.toString());
			// preparedStatement(sql작성 실행)
			int res = stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 닫기
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
			
		}
		
	}

	/**
	 * D:delete() :삭제 접근지정자 : public param : 삭제할값 return : 없음
	 */
	public void delete() {
		// 코드작성
		 String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=utf-8&serverTimezone=Asia/Seoul";
		 String user = "root";
		 String password = "smart";
		 StringBuffer sql =  new StringBuffer();
		 sql.append(" DELETE FROM exam ");
		 
		 Connection conn = null;
		 PreparedStatement stmt = null;
		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB연결
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.prepareStatement(sql.toString());
			// preparedStatement(sql작성 실행)
			int res = stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 닫기
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
						
		}
			
	}

}
