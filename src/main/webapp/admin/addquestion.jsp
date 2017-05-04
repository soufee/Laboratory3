<%--
  Created by IntelliJ IDEA.
  User: Shoma
  Date: 28.04.2017
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head>
    <title>Добавление вопроса</title>
</head>
<body>
<form action="/admin/addquestion" method="post">
    <table border="1">
        <tr> <td>Вопрос</td><td><input type="text" name="question" placeholder="question"/></td></tr>
        <tr> <td>Ответ</td><td><input type="text" name="answer" placeholder="answer"/></td></tr>
        <tr> <td>Подсказка</td><td><input type="text" name="hint" placeholder="hint"/></td></tr>
        <tr> <td>Очки</td><td><input type="number" name="score" placeholder="score"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="Add question" width="100%"/></td><tr>
    </table>
</form>
<a href="/admin/helloadmin">Back to admin page</a>
</body>
</html>
