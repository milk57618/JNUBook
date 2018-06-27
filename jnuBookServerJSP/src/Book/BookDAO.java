package Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Book.BookDTO;

public class BookDAO {
	public JSONArray getBookData() {

		String SQL = "select * from book order by post_date desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray resultItem = new JSONArray();

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				JSONObject bookItem = new JSONObject();

				bookItem.put("b_id", rs.getInt(1));
				bookItem.put("b_name", rs.getString(2));
				bookItem.put("lect_name", rs.getString(3));
				bookItem.put("magor_id", rs.getInt(4));
				bookItem.put("grade", rs.getInt(5));
				bookItem.put("title", rs.getString(6));
				bookItem.put("content", rs.getString(7));
				bookItem.put("state", rs.getInt(8));
				bookItem.put("user_id", rs.getInt(9));
				bookItem.put("post_date", rs.getString(10));
				bookItem.put("image_path", rs.getString(11));
				bookItem.put("price", rs.getInt(12));

				resultItem.add(bookItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return resultItem;

	}
	
	//다음번에 쓰여야 할 게시글의 id를 지정해준다. 
	public int getNext() {
		
		String SQL = "SELECT b_id From book ORDER BY b_id DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
		    pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1)+1;
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;//데이터베이스 오류
	}
	
	//게시판 작성시 현재 시간 가져오는 함수
		public String getDate() {
			String SQL= "SELECT NOW()";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DatabaseUtil.getConnection();
				pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getString(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return ""; //데이터 베이스 오류
		}
		
	public int summit(BookDTO book) {
		String SQL = "insert into book values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, book.getB_name());
			pstmt.setString(3, book.getLect_name());
			pstmt.setInt(4, book.getMajor_id());
			pstmt.setInt(5, book.getGrade());
			pstmt.setString(6, book.getTitle());
			pstmt.setString(7, book.getContent());
			pstmt.setInt(8, book.getState());
			pstmt.setInt(9, book.getUser_id());
			pstmt.setString(10, getDate());
			pstmt.setString(11, book.getImage_path());
			pstmt.setInt(12, book.getPrice());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return -1; // 게시물 등록 실패
	}
	

	public int update(BookDTO book) {
		String SQL = "UPDATE book SET b_name = ?, lect_name = ?, major_id = ?, grade = ?, title = ?, content =?, state = ?, price = ? WHERE b_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, book.getB_name());
			pstmt.setString(2, book.getLect_name());
			pstmt.setInt(3, book.getMajor_id());
			pstmt.setInt(4, book.getGrade());
			pstmt.setString(5, book.getTitle());
			pstmt.setString(6, book.getContent());
			pstmt.setInt(7, book.getState());
			pstmt.setInt(8, book.getPrice());
			//pstmt.setInt(9, book.getUser_id());
			//pstmt.setString(10, book.getPost_date());
			//pstmt.setString(11, book.getImage_path());
			pstmt.setInt(9, book.getB_id());

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return -1; // 게시물 수정 실패
	}

	public int delete_book(int b_id) {
		String SQL = "delete from book where b_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, b_id);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return -1; // 게시물 등록 실패
	}
}