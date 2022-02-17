package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Simple6JDBCDAO {

	public static void main(String[] args) {
		String paramVarchar = "varcharTest"; // sql데이터 사용.
		String paramChar = "charTest";
		double paramInt = 200.001;
		Date paramDate = new Date(System.currentTimeMillis());
		Timestamp paramDateTime = new Timestamp(System.currentTimeMillis());

		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=utf-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart"; // 정보가 틀리면 에러뜸.\
		String sql = "INSERT INTO exam VALUES (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// 1.드라이버 로드(Class.forName())
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.db연결(DriverManager.getConnection())
			conn = DriverManager.getConnection(url, user, password);
			// 3.sql문 작성(Statement, PreparedStatement).
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, paramVarchar);
			stmt.setString(2, paramChar);
			stmt.setDouble(3, paramInt);
			stmt.setDate(4, paramDate); // i번째 물음표에 대입, set타입의 패러미터
			stmt.setTimestamp(5, paramDateTime);

			// sql문 실행(select문 만 executeQuery()사용,나머지는 exectueUpdate()-int return)
			int res = stmt.executeUpdate(); // 1개를 등록하면 1이 리턴.
			if (res > 0) {
				System.out.println(res + "개 행이 추가되었습니다.");
			} else {
				System.out.println("등록실패했습니다.");
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
