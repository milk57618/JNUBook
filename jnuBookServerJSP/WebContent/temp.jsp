<%@ page language="java" import="java.util.*" pageEncoding="euc-kr" %>
<% request.setCharacterEncoding("euc-kr"); %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter"%>

<%
UserDTO user= new UserDTO();

int user_id=0;
String user_pw = null;
boolean user_gender=false;
int user_grade=0;
String user_name = null;
boolean adminChecked=false;

if(request.getParameter("user_id") != null){
	user_id = Integer.parseInt(request.getParameter("user_id"));
}
if(request.getParameter("user_pw") != null){
	user_pw = request.getParameter("user_pw");
}
if(request.getParameter("user_gender") != null){
	user_gender = Boolean.parseBoolean(request.getParameter("user_gender"));
}
if(request.getParameter("user_grade") != null){
	user_grade = Integer.parseInt(request.getParameter("user_grade"));
}
if(request.getParameter("user_name") != null){
	user_name = request.getParameter("user_name");
}
if(request.getParameter("adminChecked") != null){
	adminChecked = Boolean.parseBoolean(request.getParameter("adminChecked"));
}

PrintWriter script=response.getWriter();
script.println(user_id);		
script.close();


%>
