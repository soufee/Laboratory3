<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <title>Hello Admin</title>
</head>
<body>
<%
    //allow access only if session exists
   // String user = (String) session.getAttribute("login");
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
<br>
User=${login}
<br>
<a href="/user/checkoutpage">Мой профайл</a>
<form action="/logout" method="get">
    <input type="submit" value="Logout" >
</form>

<br>
<%--<form action="/admin/helloadmin" method="post">--%>
<%--&lt;%&ndash;<% String message1 = user;%>&ndash;%&gt;--%>
<%--You entered as <%=message1%>--%>
<%--</form>--%>


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
    <c:forEach items="${questions}" var="item1">
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
                <form action="/delete" method="post" style="margin:0;">
                    <input type="hidden" name="idDel" value="${item1.q_id}"/>
                    <input type="hidden" name="table" value="question"/>

                    <input type="submit" value="Delete" width="100%"/>
                </form>
            </td>
        </tr>
        <tr style="display:none;" id="edit_${item1.q_id}">
            <td>
                <form action="/edit" method="post">
                    <input type="hidden" name="iId" value="${item1.q_id}"/>
                    <input type="hidden" name="table" value="question"/>
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
            <a href="/admin/addquestion.jsp">Add question</a>
        </td>
    </tr>
</table>

<table border="0" width="75%">
    <tr>
        <%--<% String message = (String) request.getAttribute("value");%>--%>
        <td colspan="7" align="center" style="font-weight:bold;font-size:15pt">Список пользователей
            <%--<%=message%>--%>
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
    <c:forEach items="${gamers}" var="item">
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
                <form action="/delete" method="post" style="margin:0;">
                    <input type="hidden" name="idDel" value="${item.rdr_id}"/>
                    <input type="hidden" name="table" value="user"/>

                    <input type="submit" value="Delete" width="100%"/>
                </form>
            </td>
        </tr>
        <tr style="display:none;" id="edit_${item.rdr_id}">
            <td>
                <form action="/edit" method="post">
                    <input type="hidden" name="iId" value="${item.rdr_id}"/>
                    <input type="hidden" name="table" value="user"/>
                    <input type="hidden" name="iNickname" value="${item.niackname}"/>
                    <input type="hidden" name="iCsore" value="${item.score}"/>
                    <input type="hidden" name="iPassword" value="${item.password}"/>
                    <input type="hidden" name="iEmail" value="${item.email}"/>
                    <%--<input type="hidden" name="iAdmin" value="${item.isadmin}"/>--%>

                        <%--<input type="hidden" name="iIsadmin" value="${item.isAdmin}"/>--%>
                        <%--<input type="hidden" name="iIsblocked" value="${item.isBlocked}"/>--%>
                    <input type="text" name="nickname" placeholder="nickname"/>
                    <input type="number" name="csore" placeholder="csore"/>
                    <input type="text" name="password" placeholder="password"/>
                    <input type="text" name="email" placeholder="email"/>
                    <%--<input type="checkbox" name="isadmin" placeholder="isadmin"/>--%>
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

                <a href="/admin/adduser.jsp">Add User</a>

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