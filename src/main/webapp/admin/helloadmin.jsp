<%--
  Created by IntelliJ IDEA.
  User: Shoma
  Date: 22.04.2017
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <title>Hello Admin</title>
</head>
<body>
<%
    //allow access only if session exists
    String user = (String) session.getAttribute("user");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h3>
<br>
User=<%=user %>
<br>
<a href="/user/checkoutpage.jsp">Checkout Page</a>
<form action="/logout" method="post">
    <input type="submit" value="Logout" >
</form>
<br>
<% String message1 = (String) request.getAttribute("user");%>
You entered as <%=message1%>

<table border="0" width="75%">
    <tr>
        <td colspan="7" align="center" style="font-weight:bold;font-size:15pt"> Список вопросов</td>
    </tr>
    <tr style="font-weight:bold">
        <td>id</td>
        <td>quest</td>
        <td>answer</td>
        <td>score</td>
        <td>hint</td>
        <td>edit</td>
        <td>delete</td>
    </tr>
    <c:forEach items="${requestScope.quests}" var="item1">
        <tr>
            <td width="5%"><c:out value="${item1.q_id}"/></td>
            <td width="10%"><c:out value="${item1.quest}"/></td>
            <td width="30%"><c:out value="${item1.answer}"/></td>
            <td width="5%"><c:out value="${item1.score}"/></td>
            <td width="30%"><c:out value="${item1.hint}"/></td>
            <td width="10%">
                <input type="button" value="Edit" width="100%" onclick="edit(${item1.q_id})"/>
            </td>
            <td width="10%">
                <form action="/adminservlet" method="post" style="margin:0;">
                    <input type="hidden" name="idDel" value="${item1.q_id}"/>
                    <input type="submit" value="Delete" width="100%"/>
                </form>
            </td>
        </tr>
        <tr style="display:none;" id="edit_${item1.q_id}">
            <td>
                <form action="/adminservlet" method="post">
                    <input type="hidden" name="iId" value="${item1.q_id}"/>
                    <input type="hidden" name="iQuestion" value="${item1.quest}"/>
                    <input type="hidden" name="iAnswer" value="${item1.answer}"/>
                    <input type="hidden" name="iScore" value="${item1.score}"/>
                    <input type="hidden" name="iHint" value="${item1.hint}"/>
                    <input type="text" name="question" placeholder="question"/>
                    <input type="number" name="score" placeholder="score"/>
                    <input type="text" name="answer" placeholder="answer"/>
                    <input type="text" name="hint" placeholder="hint"/>
                    <input type="button" value="Cancel" onclick="cancel(${item1.q_id})"/>
                    <input type="submit" value="Submit"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2">
            <form action="/main/webapp/admin/helloadmin.jsp" method="post">
                <input type="submit" value="AddQuestion"/>
            </form>
        </td>
    </tr>
</table>

<table border="0" width="75%">
    <tr>
        <% String message = (String) request.getAttribute("value");%>
        <td colspan="7" align="center" style="font-weight:bold;font-size:15pt"><%=message%>
        </td>
    </tr>
    <tr style="font-weight:bold">
        <td>id</td>
        <td>nickname</td>
        <td>score</td>
        <td>password</td>
        <td>email</td>
        <td>edit</td>
        <td>delete</td>
    </tr>
    <c:forEach items="${requestScope.list}" var="item">
        <tr>
            <td width="5%"><c:out value="${item.rdr_id}"/></td>
            <td width="15%"><c:out value="${item.niackname}"/></td>
            <td width="10%"><c:out value="${item.score}"/></td>
            <td width="10%"><c:out value="${item.password}"/></td>
            <td width="10%"><c:out value="${item.email}"/></td>
                <%--<td width="5%"><c:out value="${item.isAdmin}"/></td>--%>
                <%--<td width="5%"><c:out value="${item.isBlocked}"/></td>--%>
            <td width="10%">
                <input type="button" value="Edit" width="100%" onclick="edit(${item.rdr_id})"/>
            </td>
            <td width="10%">
                <form action="/adminservlet" method="post" style="margin:0;">
                    <input type="hidden" name="idDel" value="${item.rdr_id}"/>
                    <input type="submit" value="Delete" width="100%"/>
                </form>
            </td>
        </tr>
        <tr style="display:none;" id="edit_${item.rdr_id}">
            <td>
                <form action="/adminservlet" method="post">
                    <input type="hidden" name="iRdr_id" value="${item.rdr_id}"/>
                    <input type="hidden" name="iNickname" value="${item.niackname}"/>
                    <input type="hidden" name="iCsore" value="${item.score}"/>
                    <input type="hidden" name="iPassword" value="${item.password}"/>
                    <input type="hidden" name="iEmail" value="${item.email}"/>
                        <%--<input type="hidden" name="iIsadmin" value="${item.isAdmin}"/>--%>
                        <%--<input type="hidden" name="iIsblocked" value="${item.isBlocked}"/>--%>
                    <input type="text" name="nickname" placeholder="nickname"/>
                    <input type="number" name="csore" placeholder="csore"/>
                    <input type="text" name="password" placeholder="password"/>
                    <input type="text" name="email" placeholder="email"/>
                        <%--<input type="checkbox" name="isadmin" placeholder="isadmin"/>--%>
                        <%--<input type="checkbox" name="isblocked" placeholder="isblocked"/>--%>
                    <input type="button" value="Cancel" onclick="cancel(${item.rdr_id})"/>
                    <input type="submit" value="Submit"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2">
            <form action="/main/webapp/admin/helloadmin.jsp" method="post">
                <input type="submit" value="AddGamer"/>
            </form>
        </td>
    </tr>
</table>

<script>
    function cancel(id) {
        document.getElementById("edit_" + id).style.display = "none";
    }
    function edit(id) {
        document.getElementById("edit_" + id).style.display = "block";
    }
</script>
</body>
</html>