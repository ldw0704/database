package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Simple9JDBCDAO {

	public static void main(String[] args) {
		String paramVarchar = "varcharTestUpdate";
		
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=utf-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart"; 
		StringBuffer sql = new StringBuffer() //쿼리문이 길기때문에
				.append("DELETE FROM exam WHERE varcharTest = ?");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 1.드라이버 로드(Class.forName())
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.db연결(DriverManager.getConnection())
			conn = DriverManager.getConnection(url, user, password);
			// 3.sql문 작성(Statement, PreparedStatement).
			stmt = conn.prepareStatement(sql.toString()); //스트링버퍼를 스트링으로 바꿔줘야한다.

			stmt.setString(1, paramVarchar);
			

			// sql문 실행(select문 만 executeQuery()사용,나머지는 exectueUpdate()-int return)
			int res = stmt.executeUpdate();  // 수정개수가 됨
			if (res > 0) {
				System.out.println(res + "개 행이 삭제되었습니다.");
			} else {
				System.out.println("삭제실패했습니다.");
			}
			// 5.select문 만 resultSet 객체를 반환한다.
			// 나머진 int를 반환한다.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 6.닫기(close())
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
