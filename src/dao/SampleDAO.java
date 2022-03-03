package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.SampleVO;
import util.DbUtil;

public class SampleDAO extends DbUtil {

	public void create(SampleVO vo) {

		StringBuffer sql = new StringBuffer()
							.append(" INSERT INTO sample ")
							.append(" (num, strData, sampleDate) ")
							.append(" VALUES(null, ?, curdate())");

		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 1;

		try {

			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(idx++, vo.getStrData());

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}

	}

	public List<SampleVO> read() {

		StringBuffer sql = new StringBuffer().append(" SELECT * FROM sample ");

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		try {

			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(new SampleVO(rs.getInt("num"),
									rs.getString("strData"),
									rs.getDate("sampleDate")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}
		return list;
	}

	public SampleVO read(SampleVO vo2) {

		StringBuffer sql = new StringBuffer()
							.append(" SELECT * FROM sample ")
							.append(" WHERE num= ? ");
//							.append(" WHERE strData= ? ");
		
//		sql.append(" WHERE 1=1 ");
//		if(vo2.getNum() > 0) {
//			sql.append(" AND num = ? ");
//		}
//		if(!"".equals(vo2.getStrData())) {
//			sql.append(" AND strData = ? ");
//		}
//		if(!"".equals(vo2.getSampleDate())) {
//			sql.append(" AND sampleDate = ? ");
//		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		List<SampleVO> list = new ArrayList<SampleVO>();
		SampleVO vo = null;
		int idx = 1;
		
		try {

			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(idx++, vo2.getNum());
//			stmt.setString(idx++, vo2.getStrData());
			
//			if(vo2.getNum() > 0) {
//				stmt.setInt(idx++, vo2.getNum());
//			}
//			if(!"".equals(vo2.getStrData())) {
//				stmt.setString(idx++, vo2.getStrData());
//			}
//			if(!"".equals(vo2.getSampleDate())) {
//				stmt.setDate(idx++, (Date)vo2.getSampleDate());
//			}
//			stmt.setInt(1, vo2.getNum());
			rs = stmt.executeQuery();

			while (rs.next()) {
				 vo = new SampleVO(rs.getInt("num"),
									rs.getString("strData"),
									rs.getDate("sampleDate"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}
		return vo;
	}

	public void update(SampleVO vo) {

		StringBuffer sql = new StringBuffer()
							.append(" UPDATE sample ")
							.append(" SET strData= ?, sampleDate = curdate() ")
							.append(" WHERE num= ? ");

		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 1;
		try {

			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(idx++, vo.getStrData());
			stmt.setInt(idx++, vo.getNum());
			stmt.setDate(idx++, (Date)vo.getSampleDate());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}

	public void delete(SampleVO vo) {
		StringBuffer sql = new StringBuffer()
							.append(" DELETE FROM sample ")
							.append(" WHERE num= ? ");

		Connection conn = null;
		PreparedStatement stmt = null;
		int idx = 1;
		try {

			conn = getConn();
			stmt = conn.prepareStatement(sql.toString());

			stmt.setInt(idx++, vo.getNum());

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}

	}
}
