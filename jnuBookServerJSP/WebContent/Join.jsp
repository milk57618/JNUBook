<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");

   int user_id = -1;
   String user_pw = null;
   boolean user_gender = false;
   int user_grade = -1;
   String user_name = null;
   
   if(request.getParameter("user_id") != null){
      try{
         user_id = Integer.parseInt(request.getParameter("user_id"));
      }catch(Exception e){
         System.out.println("학번 데이터 오류");
      }
   }
   if(request.getParameter("user_pw") != null){
      user_pw = request.getParameter("user_pw");
   }
   if(request.getParameter("user_gender") != null){
      try{
         user_gender = Boolean.parseBoolean(request.getParameter("user_gender"));
      }catch(Exception e){
         System.out.println("성별 데이터 오류");
      }
   }
   if(request.getParameter("user_grade") != null){
      try{
         user_grade = Integer.parseInt(request.getParameter("user_gender"));
      }catch(Exception e){
         System.out.println("학년 데이터 오류");
      }
   }
   if(request.getParameter("user_name") != null){
      user_name = request.getParameter("user_name");
   }
   if(user_id == -1 || user_pw == null){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }
   
   UserDAO userDAO = new UserDAO();
   UserDTO userDTO = new UserDTO();
   userDTO.setUser_id(user_id);
   userDTO.setAdminChecked(false);
   userDTO.setUser_pw(user_pw);
   userDTO.setUser_gender(user_gender);
   userDTO.setUser_name(user_name);
   userDTO.setUser_grade(user_grade);
   
   
   if(userDAO.join(userDTO)!=-1){
      PrintWriter script=response.getWriter();
      script.println("회원가입 완료");      
      script.close();
      
      return;
   } else {      
      PrintWriter script=response.getWriter();
      script.println("<script>");
      script.println("alert('이미 존재하는 아이디 입니다.');");
      script.println("history.back();");
      script.println("</script>");
      script.close();
      return;
   } 
   
%>