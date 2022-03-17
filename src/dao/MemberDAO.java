package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.MemberVO;

public class MemberDAO {

	private String url = "jdbc:mysql://localhost:3306/kblab?CharacaterEncoding=utf-8&serverTimezone=Asia/Seoul";
	private String user = "root";
	private String password = "smart";
	
	/*
	 * 트랜젝션 처리
	 * @param mvo
	 */
	public void create(MemberVO mvo) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		//트랙젝션 쿼리 2개 이용 여러테이블 동시 등록 pk 여부 확인필요
		String sql = " INSERT INTO member (id, pw, uname, regdate) VALUES (?, ?, ?, now()) ";
		String sql2 = " INSERT INTO login (id, pw) VALUES (?,?) ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false); //오토커밋여부
			
			stmt = conn.prepareStatement(sql);			
			stmt.setString(1, mvo.getId());
			stmt.setString(2, mvo.getPw());
			stmt.setString(3, mvo.getUname());			
			//member가입
			stmt.executeUpdate();
			
			//login 등록
			stmt2 = conn.prepareStatement(sql2);			
			stmt2.setString(1, mvo.getId());
			stmt2.setString(2, mvo.getPw());
			
			stmt2.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)conn.close();
				if(stmt != null)stmt.close();
				if(stmt2 != null)stmt2.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}	
		}
		
	}
}
