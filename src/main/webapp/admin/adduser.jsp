<%@ taglib prefix="width" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Shoma
  Date: 28.04.2017
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление пользователя</title>

</head>
<body>
<table border="1" width="50%">
    <form action="/admin/adduser" method="get">
        <tr> <td>Ник</td><td><input type="text" name="nickname" placeholder="user nickname"/></td>></tr>
        <tr> <td>Пароль</td><td><input type="text" name="password" placeholder="password"/></td></tr>
        <tr> <td>email</td><td><input type="text" name="email" placeholder="email"/></td></tr>
        <tr> <td>Права админа</td><td><input type="checkbox" name="isadmin" placeholder="isAdmin"/></td></tr>
        <input type="submit" value="Add User" width="100%"/>
    </form>
    </table>
<a href="/admin/helloadmin">Back to admin page</a>
</form>
</body>
</html>
