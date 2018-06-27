<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Lecture.LectureDTO"%>
<%@ page import="Lecture.LectureDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");



 int lect_id = -1;
 String lect_name = null;
 String prof_name = null;
 int major_id = 26;
 int grade=0; //nullable
 String semester = "1학기";
 String title = null;
 String content = null;
 int user_id = -1;
 String post_date = null;

 if(request.getParameter("prof_name") != null){
	 prof_name = request.getParameter("prof_name");
  }
 if(request.getParameter("lect_name") != null){
	 lect_name = request.getParameter("lect_name");
  }
 if(request.getParameter("semester") != null){
	 semester = request.getParameter("semester");
  }
 if(request.getParameter("title") != null){
	 title = request.getParameter("title");
  }
 if(request.getParameter("content") != null){
	 content = request.getParameter("content");
  }
 if(request.getParameter("post_date") != null){
	 post_date = request.getParameter("post_date");
  }
 if(request.getParameter("lect_id") != null){
     try{
    	 lect_id = Integer.parseInt(request.getParameter("lect_id"));
     }catch(Exception e){
        System.out.println("게시물 번호 데이터 오류");
     }
  }
 if(request.getParameter("major_id") != null){
     try{
    	 major_id = Integer.parseInt(request.getParameter("major_id"));
     }catch(Exception e){
        System.out.println("전공 데이터 오류");
     }
  }
 if(request.getParameter("grade") != null){
     try{
    	 grade = Integer.parseInt(request.getParameter("grade"));
     }catch(Exception e){
        System.out.println("학년 데이터 오류");
     }
  }
 if(request.getParameter("user_id") != null){
     try{
    	 user_id = Integer.parseInt(request.getParameter("user_id"));
     }catch(Exception e){
        System.out.println("학번 데이터 오류");
     }
  }
 
   if(title == null || content == null || user_id == -1){
      PrintWriter script = response.getWriter();
      script.println("값 받아오기 오류");      
      script.close();
      return;
   }
   
   if(lect_id == -1){
	   LectureDAO lectureDAO = new LectureDAO();
	   LectureDTO lectureDTO = new LectureDTO();
	   lectureDTO.setProf_name(prof_name);
	   lectureDTO.setContent(content);
	   lectureDTO.setGrade(grade);
	   lectureDTO.setLect_name(lect_name);
	   lectureDTO.setMajor_id(major_id);
	   lectureDTO.setPost_date(post_date);
	   lectureDTO.setTitle(title);
	   lectureDTO.setUser_id(user_id);   
	   lectureDTO.setSemester(semester);
	   
	   if(lectureDAO.summit(lectureDTO)!=-1){
	      PrintWriter script=response.getWriter();
	      script.println("등록 완료");      
	      script.close();
	      return;
	   } else {      
	      PrintWriter script=response.getWriter();
	      script.println("<script>");
	      script.println("alert('오류 발생');");
	      script.println("history.back();");
	      script.println("</script>");
	      script.close();
	      return;
	   } 
   }
   else{
	   LectureDAO lectureDAO = new LectureDAO();
	   LectureDTO lectureDTO = new LectureDTO();
	   lectureDTO.setLect_id(lect_id);
	   lectureDTO.setProf_name(prof_name);
	   lectureDTO.setContent(content);
	   lectureDTO.setGrade(grade);
	   lectureDTO.setLect_name(lect_name);
	   lectureDTO.setMajor_id(major_id);
	   lectureDTO.setPost_date(post_date);
	   lectureDTO.setTitle(title);
	   lectureDTO.setUser_id(user_id);   
	   lectureDTO.setSemester(semester);
	   
	   if(lectureDAO.summit(lectureDTO)!=-1){
	      PrintWriter script=response.getWriter();
	      script.println("수정 완료");      
	      script.close();
	      
	      return;
	   } else {      
	      PrintWriter script=response.getWriter();
	      script.println("<script>");
	      script.println("alert('오류 발생');");
	      script.println("history.back();");
	      script.println("</script>");
	      script.close();
	      return;
	   } 
	   
   }
   
%>