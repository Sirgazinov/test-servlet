<%@ page import="ru.ibs.logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: ASirgazinov
  Date: 20.01.2022
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите ID пользователя (0 - для вывода всего списка пользователей)
  <br/>
  Доступно: <%
    Model model = Model.getInstance();
    out.print(model.getModel().size());
  %>
  <form method="get" action="get">
    <label>ID:
      <input type="text" name="id">
    </label>
    <button type="submit">Поиск</button>
  </form>
  <a href="addUser.html">Создать нового пользователя</a>
  </body>
</html>
