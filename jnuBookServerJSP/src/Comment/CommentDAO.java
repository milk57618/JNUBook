package Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Comment.CommentDTO;


public class CommentDAO {
   
   
   public JSONArray getCommentData (int board_num, int post_id){
      
      
       String SQL="select * from comment where board_num = ? and post_id = ? order by comment_date";
       Connection conn=null;
       PreparedStatement pstmt= null;
      ResultSet rs=null;
      JSONArray resultItem = new JSONArray();
      
      try{ 
         conn=DatabaseUtil.getConnection();
         pstmt=conn.prepareStatement(SQL);
		 pstmt.setInt(1, board_num);
		 pstmt.setInt(2, post_id);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            JSONObject commentItem = new JSONObject();
            
            commentItem.put("c_id",rs.getInt(1));
            commentItem.put("board_num",rs.getInt(2));
            commentItem.put("post_id",rs.getInt(3));
            commentItem.put("user_id",rs.getInt(4));
            commentItem.put("comment_date",rs.getString(5));
            commentItem.put("content",rs.getString(6));
            
            
            resultItem.add(commentItem);
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
   

	public int summit(CommentDTO comment) {
		String SQL="insert into comment values ( ?, ?, ?, ?, ?, ? )";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, comment.getC_id());
			pstmt.setInt(2, comment.getBoard_num());
			pstmt.setInt(3, comment.getPost_id());
			pstmt.setInt(4, comment.getUser_id());
			pstmt.setString(5, comment.getComment_date());
			pstmt.setString(6, comment.getContent());
			
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
		return -1; //댓글 등록 실패
	}
	

	public int update(CommentDTO comment) {
		String SQL="UPDATE comment SET content =? WHERE c_id = ?";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			//pstmt.setInt(2, comment.getBoard_num());
			//pstmt.setInt(3, comment.getPost_id());
			//pstmt.setInt(4, comment.getUser_id());
			//pstmt.setString(5, comment.getComment_date());
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getC_id());
			
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
		return -1; //댓글 수정 실패
	}

	public int delete_comment(int c_id) {
		String SQL="delete from comment values where c_id = ?";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, c_id);
			
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