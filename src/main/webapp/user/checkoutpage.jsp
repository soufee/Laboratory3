<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Профайл пользователя</title>
</head>
<body>
<%
    //allow access only if session exists
//    String user = (String) session.getAttribute("login");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login")) userName = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<h3>Hi <%=userName %>, Login successful.
    Your Session ID=<%=sessionID %>
</h3>
<h3>Вы вошли как ${login}, Хотите выйти?</h3>
<br>
<form action="/logout" method="get">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
