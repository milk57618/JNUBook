package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.DatabaseUtil;

import user.UserDTO;

public class UserDAO {

	public UserDAO() {
		
	}

	   public JSONArray getUserData (int user_id){
	       String SQL="select * from user where user_id = ?";
	       Connection conn=null;
	       PreparedStatement pstmt= null;
	      ResultSet rs=null;
	      JSONArray resultItem = new JSONArray();
	      
	      try{
	         conn=DatabaseUtil.getConnection();
	         pstmt=conn.prepareStatement(SQL);
			 pstmt.setInt(1, user_id);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            
	            JSONObject userItem = new JSONObject();
	            
	            userItem.put("user_id",rs.getInt(1));
	            userItem.put("user_pw",rs.getString(2));
	            userItem.put("user_gender",rs.getBoolean(3));
	            userItem.put("user_grade",rs.getInt(4));
	            userItem.put("user_name",rs.getString(5));
	            userItem.put("adminChecked",rs.getBoolean(6));
	            
	            resultItem.add(userItem);
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
	   
	public int login(int user_id, String user_pw) {
		String SQL="select user_pw from user where user_id = ?";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(user_pw)) {
					return 1;  //로그인 성공
				}
				else
					return 0; //비밀번호 틀림
			}
			return -1; //아이디 존재하지 않음
			
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
		return -2; //데이터베이스 오류
	}
	
	public int join(UserDTO user) {
		String SQL="insert into user values ( ?, ?, ?, ?, ?, ? )";
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		
		try{
			conn=DatabaseUtil.getConnection();
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, user.getUser_id());
			pstmt.setString(2, user.getUser_pw());
			pstmt.setBoolean(3, user.isUser_gender()); //0: 여자 , 1 : 남자
			pstmt.setInt(4, user.getUser_grade());
			pstmt.setString(5, user.getUser_name());
			pstmt.setBoolean(6, user.isAdminChecked());
			
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
