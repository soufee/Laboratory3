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
<a href="/index.jsp" class="link_black_novisit link b-inline link_logout" tabindex="3"><div class="b-inline" align="right">Log out</div></a>
<br>
<table border="0" width="75%">

    <tr>
        <% String message = (String) request.getAttribute("value");%>
        <td colspan="7" align="center" style="font-weight:bold;font-size:15pt"> <%=message%></td>

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
            <form action="/helloadmin.jsp" method="post">
                <input type="submit" value="Add"/>
            </form>
        </td>
    </tr>
</table>
<script>
    function cancel(id) {
        document.getElementById("edit_"+id).style.display = "none";
    }
    function edit(id) {
        document.getElementById("edit_"+id).style.display = "block";
    }
</script>
</body>
</html>
