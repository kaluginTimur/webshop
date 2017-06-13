<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
        <title>Администраторская панель</title>
    </head>
    <body style="font-family: 'Cuprum', serif;">
    <center>
        <h1>Менеджмент</h1>
        <h2>
            <a href="customers">Список пользователей</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${user != null}">
            <form action="update?id=<c:out value='${user.id}' />" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        Редактировать пользователя
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>           
                <tr>
                    <th>Имя: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${user.name}' />"
                               />
                    </td>
                </tr>
                <tr>
                    <th>Пароль: </th>
                    <td>
                        <input type="text" name="pass" size="10"
                               value="<c:out value='${user.pass}' />"
                               />
                    </td>
                </tr>
                <tr>
                    <th>Роль: </th>
                    <td>
                        <select name="role">
                            <option value="${user.role}">Текущая</option>
                            <option value="0">Менеджер</option>
                            <option value="1">Клиент</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Сохранить" />
                    </td>
                </tr>
            </table>
        </form>
    </div>   
</body>
</html>