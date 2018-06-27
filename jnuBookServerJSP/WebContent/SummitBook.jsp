<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Book.BookDTO"%>
<%@ page import="Book.BookDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
   request.setCharacterEncoding("UTF-8");


 int b_id = -1;
 String b_name = null;
 String lect_name = null;
 int major_id = -1;
 int grade=0; //nullable
 String title = null;
 String content = null;
 int state = -1;
 int user_id = -1;
 String post_date = "";
 String image_path = null; //nullable
 int price = 0;

 if(request.getParameter("b_id") != null){
	 b_id = Integer.parseInt(request.getParameter("b_id"));
  }
 
 if(request.getParameter("b_name") != null){
	 b_name = request.getParameter("b_name");
  }
 if(request.getParameter("lect_name") != null){
	 lect_name = request.getParameter("lect_name");
  }
 if(request.getParameter("title") != null){
	 title = request.getParameter("title");
  }
 if(request.getParameter("content") != null){
	 content = request.getParameter("content");
  }

 if(request.getParameter("major_id") != null){
     try{
    	 major_id = Integer.parseInt(request.getParameter("major_id"));
     }catch(Exception e){
        System.out.println("전공 데이터 오류");
     }
  }
 
 if(request.getParameter("state") != null){
     try{
    	 state = Integer.parseInt(request.getParameter("state"));
     }catch(Exception e){
        System.out.println("평점 데이터 오류");
     }
  }
 if(request.getParameter("user_id") != null){
     try{
    	 user_id = Integer.parseInt(request.getParameter("user_id"));
     }catch(Exception e){
        System.out.println("학번 데이터 오류");
     }
  }
 if(request.getParameter("price") != null){
     try{
    	 price = Integer.parseInt(request.getParameter("price"));
     }catch(Exception e){
        System.out.println("가격 데이터 오류");
     }
  }
 
 
 if(title == null || content == null || user_id == -1){
     PrintWriter script = response.getWriter();
     script.println("값 받아오기 오류");      
     script.close();
     return;
  }
 
 if(b_id == -1){   
   BookDAO bookDAO = new BookDAO();
   BookDTO bookDTO = new BookDTO();
   bookDTO.setB_name(b_name);
   bookDTO.setContent(content);
   bookDTO.setGrade(grade);
   bookDTO.setImage_path(image_path);
   bookDTO.setLect_name(lect_name);
   bookDTO.setMajor_id(major_id);
   bookDTO.setPost_date(post_date);
   bookDTO.setState(state);
   bookDTO.setTitle(title);
   bookDTO.setUser_id(user_id);   
   bookDTO.setPrice(price);
   
   if(bookDAO.summit(bookDTO)!=-1){
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
	 	BookDAO bookDAO = new BookDAO();
	   BookDTO bookDTO = new BookDTO();
	   bookDTO.setB_id(b_id);
	   bookDTO.setB_name(b_name);
	   bookDTO.setContent(content);
	   bookDTO.setGrade(grade);
	   bookDTO.setImage_path(image_path);
	   bookDTO.setLect_name(lect_name);
	   bookDTO.setMajor_id(major_id);
	   bookDTO.setPost_date(post_date);
	   bookDTO.setState(state);
	   bookDTO.setTitle(title);
	   bookDTO.setUser_id(user_id);     
	   bookDTO.setPrice(price);
	   
	   if(bookDAO.update(bookDTO)!=-1){
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