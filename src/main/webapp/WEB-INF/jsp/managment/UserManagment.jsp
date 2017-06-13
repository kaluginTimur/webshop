<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
        <title>Администраторская панель</title>
    </head>
    <body style="font-family: 'Cuprum', serif;">
    <center>
        <h1>
            Менеджмент
            <br>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            <br><br><br>
            <a href="${pageContext.request.contextPath}/managment/BicycleManagment">Товары</a>
        </h1>
        <h2>
            <a href="customers">Список покупателей</a>
            &nbsp;&nbsp;&nbsp;
            <a href="managers">Список менеджеров</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/order/ordersondelete">Очередь заказов на удаление</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Список товаров</h2></caption>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Пароль</th>
                <th>Роль</th>
                <th>Опции</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.pass}" /></td>
                    <td><c:out value="${user.role}" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/order?managmentUserIdOrder=<c:out value='${user.id}' />">Список заказов</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="edit?id=<c:out value='${user.id}' />">Редактировать</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${user.id}' />">Удалить</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>