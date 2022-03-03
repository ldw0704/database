package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.ExamVO;
import util.DbUtil;



public class ExamDAO extends DbUtil{

	/**
	 * C:create() :등록 접근지정자 : public param : 등록될값 return : 없음
	 */
	public void create(ExamVO Vo) {
		// 코드작성
		
		StringBuffer sql = new StringBuffer();
				sql.append("\n INSERT INTO exam ");
				sql.append("\n (varcharTest, charTest, doubleTest, dateTest, dateTimeTest) ");
				sql.append("\n VALUES (?, ?, ?, ?, ?) ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 1;
		
		try {
			conn = getConn();
//			conn =  new DbUtil().getConn();
			// preparedStatement(sql작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			
			//?에 값설정
			stmt.setString(idx++, Vo.getVarcharTest());
			stmt.setString(idx++, Vo.getCharTest());
			stmt.setDouble(idx++, Vo.getDoubleTest());
			stmt.setDate(idx++, new Date(Vo.getDateTest().getTime()));
			stmt.setTimestamp(idx++, Vo.getDateTimeTest());
			
			stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
			// 닫기
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);

		}

	}

	/**
	 * R:read() : 조회 접근지정자 : public param : 조회할값 return : List
	 */
	public List read() {
		// 코드작성
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM exam ");

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ExamVO> list = new ArrayList<ExamVO>();

		try {
			// 드라이버로드
			conn = getConn();
			// preparedStatement(sql작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			// 결과처리(Select문만 resutlSet객체 리턴)
			rs = stmt.executeQuery();
			while(rs.next()) { // next()는 해당열을 가져올수 잇는가 boolean return
				list.add(
						new ExamVO( 
								rs.getInt("num"), 
								rs.getString("varcharTest"),
								rs.getString("charTest"),
								rs.getDouble("doubleTest"), //문자열로 받을수 있음
								rs.getDate("dateTest"),
								rs.getTimestamp("dateTimeTest")
						)
				);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 닫기
			dbClose(conn, stmt, rs);
		}

		return list;
	}

	public ExamVO read(ExamVO vo){
		//코드작성
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM exam WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ExamVO examVo = null;
		
		try {
			//드라이버 로드
			conn = getConn();
			//preparedStatement(sql + 실행)
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, vo.getNum());
			
			//select는 ResultSet 객체생성
			rs = stmt.executeQuery();			
			if(rs.next()) {
				examVo = new ExamVO( 
						rs.getInt("num"), 
						rs.getString("varcharTest"),
						rs.getString("charTest"),
						rs.getDouble("doubleTest"), //문자열로 받을수 있음
						rs.getDate("dateTest"),
						rs.getTimestamp("dateTimeTest")
				);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			//닫기
			dbClose(conn, stmt, rs);		
		}
		//코드작성 끝
		return examVo;
	}
	
	/**
	 * U:update() :수정 접근지정자 : public param : 수정할값 return : 없음
	 */
	public void update(ExamVO vo) {
		// 코드작성
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE smart.exam ");
		sql.append(" SET varcharTest = ?, doubleTest = ? "); 
		sql.append(" WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 0;
		try {
			// 드라이버로드
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(++idx, vo.getVarcharTest());
			stmt.setDouble(++idx, vo.getDoubleTest());
			stmt.setInt(++idx, vo.getNum());
			
			// preparedStatement(sql작성 실행)
			int res = stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 닫기
			dbClose(conn, stmt, null);
		}
		
	}

	/**
	 * D:delete() :삭제 접근지정자 : public param : 삭제할값 return : 없음
	 */
	public void delete(ExamVO vo) {
//		System.out.println(vo);
		// 코드작성
		 
		 StringBuffer sql =  new StringBuffer();
		 	sql.append(" DELETE FROM exam ");
		 	sql.append(" WHERE num = ? ");
		 
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 
		try {
			// 드라이버로드
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, vo.getNum());
			
			// preparedStatement(sql작성 실행)
			int res = stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 닫기
			dbClose(conn, stmt, null);	
		}
			
	}

}
