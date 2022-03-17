package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BoardVO;
import domain.BoardDTO;

public class BoardDAO {
	
	private String url = "jdbc:mysql://localhost:3306/kblab?CharacaterEncoding=utf-8&serverTimezone=Asia/Seoul";
	private String user = "root";
	private String password = "smart";
	
	public void create(BoardVO vo) {
		
		StringBuffer query = new StringBuffer();
		query.append(" INSERT INTO board (board_id, writer, title, content, regdate, hit) ")
			.append(" VALUES(null,?,?,?,now(),0) ");
		String sql = query.toString();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.prepareStatement(sql);
									
			stmt.setString(1, vo.getWriter());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getContent());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {			
			try {
				if(conn != null)conn.close();
				if(stmt != null)stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		
	}
	
	public List<BoardVO> read(){
				
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO bvo = null;
		
		StringBuffer query = new StringBuffer();
//		query.append(" SELECT board_id, title, writer, regdate, hit ")
//				.append(" FROM board")
		query.append(" SELECT b.board_id, b.title, m.uname as writer, b.regdate, b.hit ")
				.append(" FROM board b")
				.append(" JOIN member m ")
				.append(" ON m.id = b.writer ")
				.append(" ORDER BY b.board_id DESC");
//				.append(" ORDER BY board_id DESC");
		String sql = query.toString();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				bvo = new BoardVO();
				bvo.setBoard_id(rs.getInt("board_id"));
				bvo.setTitle(rs.getString("title"));
				bvo.setWriter(rs.getString("writer"));
				bvo.setRegdate(rs.getTimestamp("regdate"));
				bvo.setHit(rs.getInt("hit"));
				list.add(bvo);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)conn.close();
				if(stmt != null)stmt.close();
				if(rs != null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
		return list;
	}
	
	public List<BoardVO> read(BoardDTO dto){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO bvo = null;
		
		StringBuffer query = new StringBuffer();
		query.append(" SELECT b.board_id, b.title, m.uname as writer, b.regdate, b.hit ")
			.append( "FROM board b JOIN member m ")
			.append(" ON m.id = b.writer ");			
			if("title".equals(dto.getSfl())) {
				query.append( "where b.title LIKE ? ");
			}
			if("content".equals(dto.getSfl())) {
				query.append( "where b.content LIKE ? ");
			}
			if("title|content".equals(dto.getSfl())) {
				query.append( "where b.title LIKE ? OR b.content LIKE ? ");
			}
			query.append( "ORDER BY b.board_id DESC ");
			
		String sql = query.toString();
		int idx = 1;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.prepareStatement(sql);
			
			if("title".equals(dto.getSfl())) {
				stmt.setString(idx++,"%"+dto.getKeyword()+"%");
			}
			if("content".equals(dto.getSfl())) {
				stmt.setString(idx++,"%"+dto.getKeyword()+"%");
			}
			if("title|content".equals(dto.getSfl())) {
				stmt.setString(idx++,"%"+dto.getKeyword()+"%");
				stmt.setString(idx++,"%"+dto.getKeyword()+"%");
			}
						
			rs = stmt.executeQuery();			
			while(rs.next()) {
				bvo = new BoardVO();
				bvo.setBoard_id(rs.getInt("board_id"));
				bvo.setTitle(rs.getString("title"));
				bvo.setWriter(rs.getString("writer"));
				bvo.setRegdate(rs.getTimestamp("regdate"));
				bvo.setHit(rs.getInt("hit"));
				list.add(bvo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)conn.close();
				if(stmt != null)stmt.close();
				if(rs != null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return list;
	}
	
	public BoardVO read(BoardVO bvo){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		
		StringBuffer query = new StringBuffer();
		query.append(" SELECT b.board_id, b.title, b.content, m.uname as writer, b.regdate, b.hit ")
		.append(" FROM board b")
		.append(" JOIN member m ")
		.append(" ON m.id = b.writer ")
		.append(" WHERE b.board_id = ? ")
		.append(" ORDER BY b.board_id DESC");
		
		String sql = query.toString();
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, bvo.getBoard_id());
						
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVO();
				vo.setBoard_id(rs.getInt("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)conn.close();
				if(stmt != null)stmt.close();
				if(rs != null)rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return vo;
	}
	
}
