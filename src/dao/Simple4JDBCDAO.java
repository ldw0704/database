package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Simple4JDBCDAO {

	public static void main(String[] args) {
		double param = 100;
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=utf-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart"; // 정보가 틀리면 에러뜸.\
		String sql = "SELECT * FROM exam WHERE intTest = " + param;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// 1.드라이버 로드(Class.forName())
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.db연결(DriverManager.getConnection())
			conn = DriverManager.getConnection(url, user, password);// java.sql을 사용해야 범용성이 커진다.
			// 3.sql문 작성(Statement, PrepareStatement) statement는 해킹우려때문에 사용안함.
			stmt = conn.createStatement();
			// sql문 실행(executeQuert(),exectueUpdate())
			rs = stmt.executeQuery(sql);
			// 5.select문 만 resultSet 객체를 반환한다.
			// 나머진 int를 반환한다.
			while (rs.next()) {

				System.out.printf("varcharTest:%s,", rs.getString("varcharTest"));
				System.out.printf("charTest:%s,", rs.getString("charTest"));
				System.out.printf("intTest:%s,", rs.getString("intTest"));
				System.out.printf("dateTest:%s,", rs.getString("dateTest"));
				System.out.printf("dateTimeTest:%s%n", rs.getString("dateTimeTest"));

			}
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
