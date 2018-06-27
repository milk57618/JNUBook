<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

	String userID = null;
	String userPW = null;
	
	if(request.getParameter("user_id") != null){
		userID = request.getParameter("user_id");
	}
	if(request.getParameter("user_pw") != null){
		userPW = request.getParameter("user_pw");
	}
	if(userID == null || userPW == null){
		PrintWriter script=response.getWriter();
		script.println("-3");		
		script.close();
		return;
	}
	
	UserDAO userDAO = new UserDAO();	
	int result = userDAO.login(Integer.parseInt(userID), userPW);
	
	if(result == 1){
		session.setAttribute("userID", userID);
		PrintWriter script=response.getWriter();
		//script.println(1);
		script.println(userDAO.getUserData(Integer.parseInt(userID)));	
		script.close();
		//PrintWriter script=response.getWriter();
		//script.println("<script>");
		//script.println("location.href = 'index.jsp'");
		//script.println("</script>");
		//script.close();
		
		return;
	} else if(result == 0){		
		PrintWriter script=response.getWriter();
		script.println("0");		
		script.close();
		//PrintWriter script=response.getWriter();
		//script.println("<script>");
		//script.println("alert('비밀번호가 일치하지 않습니다.');");
	//	script.println("history.back();");
	//	script.println("</script>");
	//	script.close();
		return;
	} else if(result == -1){
		PrintWriter script=response.getWriter();
		script.println("-1");		
		script.close();
	//	PrintWriter script=response.getWriter();
	//	script.println("<script>");
	//	script.println("alert('존재하지 않는 아이디 입니다.');");
	//	script.println("history.back();");
	//	script.println("</script>");
	//	script.close();
		return;
	} else if(result == -2){
		PrintWriter script=response.getWriter();
		script.println("-2");		
		script.close();
	//	PrintWriter script=response.getWriter();
	//	script.println("<script>");
	//	script.println("alert('데이터베이스 오류가 발생했습니다.');");
	//	script.println("history.back();");
	//	script.println("</script>");
	//	script.close();
		return;
	}
	
%>