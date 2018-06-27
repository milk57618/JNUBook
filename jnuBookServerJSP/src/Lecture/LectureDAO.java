package Lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Lecture.LectureDTO;


public class LectureDAO {
   
   
   public JSONArray getboardData (){
	   
	   
	    String SQL="select * from lecture order by post_date desc";
	    Connection conn=null;
	    PreparedStatement pstmt= null;
		ResultSet rs=null;
		JSONArray resultItem = new JSONArray();
		
		try{
			
			
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JSONObject classInfoItem = new JSONObject();
				
				classInfoItem.put("lect_id",rs.getInt(1));
				classInfoItem.put("lect_name",rs.getString(2));
				classInfoItem.put("prof_name",rs.getString(3));
				classInfoItem.put("user_id",rs.getInt(4));
				classInfoItem.put("semester_id",rs.getString(5));
				classInfoItem.put("grade",rs.getInt(6));
				classInfoItem.put("major_id",rs.getInt(7));
				classInfoItem.put("title",rs.getString(8));
				classInfoItem.put("content",rs.getString(9));
				classInfoItem.put("post_date",rs.getString(10)); //날짜
				
				
				resultItem.add(classInfoItem);
			}
		
				
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
         if(conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
         if(pstmt != null) {
            try {
               pstmt.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
         if(rs != null) {
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
 		
 		String SQL = "SELECT lect_id From lecture ORDER BY lect_id DESC";
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

	public int summit(LectureDTO lecture) {
		String SQL = "insert into lecture values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, lecture.getLect_name());
			pstmt.setString(3, lecture.getProf_name());
			pstmt.setInt(4, lecture.getUser_id());
			pstmt.setString(5, lecture.getSemester());
			pstmt.setInt(6, lecture.getGrade());
			pstmt.setInt(7, lecture.getMajor_id());
			pstmt.setString(8, lecture.getTitle());
			pstmt.setString(9, lecture.getContent());
			pstmt.setString(10, getDate());

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
	

	public int update(LectureDTO lecture) {
		
		String SQL = "UPDATE lecture SET lect_name = ?, prof_name = ?, semester = ?, grade = ?, major_id = ?, title = ?, content =? WHERE lect_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();		
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,lecture.getLect_name());
			pstmt.setString(2, lecture.getProf_name());
			pstmt.setString(3, lecture.getSemester());
			pstmt.setInt(4, lecture.getGrade());
			pstmt.setInt(5,lecture.getMajor_id());
			pstmt.setString(6,lecture.getTitle());
			pstmt.setString(7,lecture.getContent());
			pstmt.setInt(8, lecture.getLect_id());

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
	
	public int delete_lecture(int lect_id) {
		String SQL = "delete from lecture where lect_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, lect_id);

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
		return -1; // 게시물 삭제 실패
	}

}