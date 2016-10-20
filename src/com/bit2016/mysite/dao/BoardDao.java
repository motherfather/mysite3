package com.bit2016.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.mysite.action.board.ListAction;
import com.bit2016.mysite.vo.BoardVo;

public class BoardDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}
	
	public int getCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql = "select count(*) from board";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}
	
	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			if (ListAction.kwd != null) {
				String sql = "select * from(select rownum as rn, no, title, hit, reg_date, name, users_no, "
						+ "depth from (select a.no, a.title, a.hit, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, "
						+ "b.name, a.USERS_NO, a.DEPTH from board a, users b where a.users_no = b.no "
						+ " and (title like ? or content like ?) order by group_no desc, "
						+ "order_no asc)) where (?-1)*?+1 <= rn and rn <= ?*?";
				
				int page = (int)(Math.ceil(getCount() / ListAction.LIST_SIZE));
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "ListAcion.kwd");
				pstmt.setString(2, "ListAcion.kwd");
				pstmt.setInt(3, page);
				pstmt.setInt(4, ListAction.LIST_SIZE);
				pstmt.setInt(5, page);
				pstmt.setInt(6, ListAction.LIST_SIZE);
				
				rs = pstmt.executeQuery();
			} else {
				String sql = "select * from(select rownum as rn, no, title, hit, reg_date, name, users_no, "
						+ "depth from (select a.no, a.title, a.hit, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, "
						+ "b.name, a.USERS_NO, a.DEPTH from board a, users b where a.users_no = b.no "
						+ " order by group_no desc, order_no asc)) where (?-1)*?+1 <= rn and rn <= ?*?";
				
				int page = (int)(Math.ceil(getCount() / ListAction.LIST_SIZE));
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, page);
				pstmt.setInt(2, ListAction.LIST_SIZE);
				pstmt.setInt(3, page);
				pstmt.setInt(4, ListAction.LIST_SIZE);
				
				rs = pstmt.executeQuery();
				
			}
			
			while (rs.next()) {
				Long no = rs.getLong(2);
				String title = rs.getString(3);
				Long hit = rs.getLong(4);
				String reg_date = rs.getString(5);
				String users_name = rs.getString(6);
				Long users_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setUsers_name(users_name);
				vo.setUsers_no(users_no);
				vo.setDepth(depth);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return list;
	}
	
	public BoardVo getView(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		
		try {
			conn = getConnection();
			String sql = "select title, content, users_no, group_no, order_no, depth from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString(1);
				String content = rs.getString(2);
				Long users_no = rs.getLong(3);
				Long group_no = rs.getLong(4);
				Long order_no = rs.getLong(5);
				Long depth = rs.getLong(6);
				
				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUsers_no(users_no);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return vo;
	}
	
	public void delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	
	public void hitCount(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "update board set hit = hit + 1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
			
			String sql2 = "select no, title, content, hit from board where no = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long hit = rs.getLong(4);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHit(hit);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	
	public void write(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			if (vo.getGroup_no() == null) {
			String sql = "insert into board values(board_seq.nextval, ?, ?, sysdate, 0, "
								+ "nvl((select max(group_no) from board), 0) + 1, 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			
			pstmt.executeUpdate();
			
			} else if (vo.getGroup_no() != null) {
				String sql = "insert into board "
						+ "values(board_seq.nextval, ?, ?, sysdate, 0, "
						+ "?, ?+1, "
						+ "?+1, ?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setLong(3, vo.getGroup_no());
				pstmt.setLong(4, vo.getOrder_no());
				pstmt.setLong(5, vo.getDepth());
				pstmt.setLong(6, vo.getUsers_no());
			
				pstmt.executeUpdate();
				
				String sql2 = "update board set order_no = order_no + 1 "
						+ "where group_no = ? and order_no > ?";
				pstmt = conn.prepareStatement(sql2);
				
				pstmt.setLong(1, vo.getGroup_no());
				pstmt.setLong(2, vo.getOrder_no());
				
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
	
	public void modify(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board set title=?, content=? where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		} 
	}
}
