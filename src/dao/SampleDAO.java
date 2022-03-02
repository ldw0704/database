package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.SampleVO;
import util.DbUtil;

public class SampleDAO extends DbUtil{

	public void create(SampleVO vo) {
		// 코드작성
		
		StringBuffer sql = new StringBuffer();
				sql.append("\n INSERT INTO sample ");
				sql.append("\n (strData, sampleDate) ");
				sql.append("\n VALUES (?, ?) ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 1;
		
		try {
			conn = getConn();
//			conn =  new DbUtil().getConn();
			// preparedStatement(sql작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			
			//?에 값설정
			stmt.setString(idx++, vo.getStrData());			
			stmt.setDate(idx++, new Date(vo.getSampleDate().getTime()));
			
			
			stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
			// 닫기
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbclose(conn, stmt, null);

		}

	}

	/**
	 * R:read() : 조회 접근지정자 : public param : 조회할값 return : List
	 */
	public List read() {
		// 코드작성
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM sample ");

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SampleVO> list = new ArrayList<SampleVO>();

		try {
			// 드라이버로드
			conn = getConn();
			// preparedStatement(sql작성 실행)
			stmt = conn.prepareStatement(sql.toString());
			// 결과처리(Select문만 resutlSet객체 리턴)
			rs = stmt.executeQuery();
			while(rs.next()) { // next()는 해당열을 가져올수 잇는가 boolean return
				list.add(
						new SampleVO(
								rs.getInt("num"), 
								rs.getString("strData"), 
								rs.getDate("sampleDate")
								)
						);		
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 닫기
			dbclose(conn, stmt, rs);
		}

		return list;
	}

	public SampleVO read(SampleVO vo){
		//코드작성
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM sample WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SampleVO SampleVO = null;
		
		try {
			//드라이버 로드
			conn = getConn();
			//preparedStatement(sql + 실행)
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, vo.getNum());
			
			//select는 ResultSet 객체생성
			rs = stmt.executeQuery();			
			if(rs.next()) {
				SampleVO = new SampleVO( 
						rs.getInt("num"), 
						rs.getString("strData"), 
						rs.getDate("sampleDate")						
				);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			//닫기
			dbclose(conn, stmt, rs);		
		}
		//코드작성 끝
		return SampleVO;
	}
	
	/**
	 * U:update() :수정 접근지정자 : public param : 수정할값 return : 없음
	 */
	public void update(SampleVO vo) {
		// 코드작성
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sample ");
		sql.append(" SET strData = ?, sampleDate = ? "); 
		sql.append(" WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 0;
		try {
			// 드라이버로드
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setString(++idx, vo.getStrData());
			stmt.setDate(++idx, new Date(vo.getSampleDate().getTime()));
			stmt.setInt(++idx, vo.getNum());
			
			// preparedStatement(sql작성 실행)
			int res = stmt.executeUpdate();
			// 결과처리(Select문만 resutlSet객체 리턴
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			// 닫기
			dbclose(conn, stmt, null);
		}
		
	}

	/**
	 * D:delete() :삭제 접근지정자 : public param : 삭제할값 return : 없음
	 */
	public void delete(SampleVO vo) {
//		System.out.println(vo);
		// 코드작성
		 
		 StringBuffer sql =  new StringBuffer();
		 	sql.append(" DELETE FROM sample ");
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
			dbclose(conn, stmt, null);	
		}
			
	}

}
