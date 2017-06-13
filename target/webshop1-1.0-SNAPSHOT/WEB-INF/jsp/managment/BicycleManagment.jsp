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
            <a href="UserManagment/customers">Пользователи</a></h1>
        </h1>
        <h2>
            <a href="BicycleManagment/new">Добавить товар</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Список товаров</h2></caption>
            <tr>
                <th>ID</th>
                <th>Модель</th>
                <th>Тип</th>
                <th>Вес</th>
                <th>Диаметр колёс</th>
                <th>Цена</th>
                <th>Кол-во на складе</th>
                <th>Опции</th>
            </tr>
            <c:forEach var="bicycle" items="${bicycleList}" varStatus="loop">
                <tr>
                    <td><c:out value="${bicycle.id}" /></td>
                    <td><c:out value="${bicycle.model}" /></td>
                    <td><c:out value="${bicycle.type}" /></td>
                    <td><c:out value="${bicycle.weight}" /></td>
                    <td><c:out value="${bicycle.wheeldiam}" /></td>
                    <td><c:out value="${bicycle.price}" /></td>
                    <td><c:out value="${bicycle.stock.amount}" /></td>
                    <td>
                        <a href="BicycleManagment/edit?id=<c:out value='${bicycle.id}' />">Редактировать</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="BicycleManagment/delete?id=<c:out value='${bicycle.id}' />">Удалить</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>