package Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DatabaseUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Comment.CommentDTO;
import Reply.ReplyDTO;

public class ReplyDAO {
   
   
   public JSONArray getReplyData (int c_id){
      
      
       String SQL="select * from reply where c_id = ? order by reply_date";
       Connection conn=null;
       PreparedStatement pstmt= null;
      ResultSet rs=null;
      JSONArray resultItem = new JSONArray();
      
      try{
         
         
         conn=DatabaseUtil.getConnection();
         pstmt=conn.prepareStatement(SQL);
		 pstmt.setInt(1, c_id);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
            JSONObject replyItem = new JSONObject();
            
            replyItem.put("r_id",rs.getInt(1));
            replyItem.put("c_id",rs.getInt(2));
            replyItem.put("user_id",rs.getInt(3));
            replyItem.put("reply_date",rs.getString(4));
            replyItem.put("content",rs.getString(5));
            
            
            resultItem.add(replyItem);
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
   

	public int summit(ReplyDTO reply) {
		String SQL="insert into reply values ( ?, ?, ?, ?, ? )";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, reply.getR_id());
			pstmt.setInt(2, reply.getC_id());
			pstmt.setInt(3, reply.getUser_id());
			pstmt.setString(4, reply.getReply_date());
			pstmt.setString(5, reply.getContent());
			
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
		return -1; //답글 등록 실패
	}

	public int update(ReplyDTO reply) {
		String SQL="UPDATE reply SET content =? WHERE r_id = ?";
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
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getR_id());
			
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
		return -1; //답글 수정 실패
	}

	public int delete_reply(int r_id) {
		String SQL="delete from reply values where r_id = ?";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, r_id);
			
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