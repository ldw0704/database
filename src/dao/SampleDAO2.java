package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.SampleVO;
import util.DbUtil;

public class SampleDAO2 extends DbUtil {

	public void create(SampleVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO sample ");
		sql.append(" (num, strData, sampleDate) ");
		sql.append(" VALUES(null, ?, curdate()) ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, vo.getStrData());
			int res = stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}
	
	public List<SampleVO> read() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  sample ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<SampleVO> list = new ArrayList<SampleVO>();
		SampleVO vo = null;
		try {
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(
					new SampleVO(rs.getInt("num"), 
							rs.getString("strData"), 
							rs.getDate("sampleDate"))
				);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}
		return list;
	}
	
	public SampleVO read(SampleVO vo2) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  sample ");
		sql.append(" WHERE 1=1 ");
		if(vo2.getNum() > 0) {
			sql.append(" AND num = ? ");
		}
		if(!"".equals(vo2.getStrData())) {
			sql.append(" AND strData = ? ");
		}
		if(!"".equals(vo2.getSampleDate())) {
			sql.append(" AND sampleDate = ? ");
		}

		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		SampleVO vo = null;
		int idx = 1;
		try {
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			if(vo2.getNum() > 0) {
				stmt.setInt(idx++, vo2.getNum());
			}
			if(!"".equals(vo2.getStrData())) {
				stmt.setString(idx++, vo2.getStrData());
			}
			if(!"".equals(vo2.getSampleDate())) {
				stmt.setDate(idx++, (Date)vo2.getSampleDate());
			}
			
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				vo = new SampleVO(rs.getInt("num"), 
							rs.getString("strData"), 
							rs.getDate("sampleDate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}
		return vo;
	}
	
	public void update(SampleVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sample SET strData = ?, sampleDate = curdate() ");
		sql.append(" WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, vo.getStrData());
			stmt.setInt(2, vo.getNum());
			
			int res = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}
	
	public void delete(SampleVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM sample ");
		sql.append(" WHERE num = ? ");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			stmt.setInt(1, vo.getNum());
			
			int res = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}
}
