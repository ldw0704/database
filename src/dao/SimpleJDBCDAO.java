package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleJDBCDAO {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=utf-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart"; // 정보가 틀리면 에러뜸.\
		String sql = "SELECT * FROM exam";

		// 1.드라이버 로드(Class.forName())
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2.db연결(DriverManager.getConnection())
		Connection conn = DriverManager.getConnection(url, user, password);// java.sql을 사용해야 범용성이 커진다.
		// 3.sql문 작성(Statement, PrepareStatement) statement는 해킹우려때문에 사용안함.
		Statement stmt = conn.createStatement();
		// sql문 실행(executeQuert(),exectueUpdate())
		ResultSet rs = stmt.executeQuery(sql);
		// 5.select문 만 resultSet 객체를 반환한다.
		//나머진 int를 반환한다.
		rs.next();//첫번째 row값만
//		rs.next();
		System.out.println(rs.getString("varchartest"));
		System.out.println(rs.getString("intTest"));
		
		//6.닫기(close())
		rs.close();
		stmt.close();
		conn.close();
	}

}
