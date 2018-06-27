package Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Notice.NoticeDTO;


public class NoticeDAO {
   
   
   public JSONArray getNoticeData (){
      
      
       String SQL="select * from notice order by post_date desc";
       Connection conn=null;
       PreparedStatement pstmt= null;
      ResultSet rs=null;
      JSONArray resultItem = new JSONArray();
      
      try{
         
         
         conn=DatabaseUtil.getConnection();
         pstmt=conn.prepareStatement(SQL);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            JSONObject noticeItem = new JSONObject();
            
            noticeItem.put("n_id",rs.getInt(1));
            noticeItem.put("post_date",rs.getString(2));
            noticeItem.put("title",rs.getString(3));
            noticeItem.put("content",rs.getString(4));
            
            
            resultItem.add(noticeItem);
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
		
		String SQL = "SELECT n_id From notice ORDER BY b_id DESC";
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
		

	public int summit(NoticeDTO notice) {
		String SQL="insert into notice values ( ?, ?, ?, ? )";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, getDate());
			pstmt.setString(3, notice.getTitle());
			pstmt.setString(4, notice.getContent());
			
			return pstmt.executeUpdate();
			
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
		return -1; //공지사항 등록 실패
	}
	

	public int update(NoticeDTO notice) {
		String SQL="UPDATE notice SET title = ?, content =? WHERE n_id = ?";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getN_id());
			
			return pstmt.executeUpdate();
			
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
		return -1; //공지사항 수정 실패
	}

	public int delete_notice(int n_id) {
		String SQL="delete from notice where n_id = ?";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, n_id);
			
			return pstmt.executeUpdate();
			
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
		return -1; //회원가입 실패
	}
  
   
}