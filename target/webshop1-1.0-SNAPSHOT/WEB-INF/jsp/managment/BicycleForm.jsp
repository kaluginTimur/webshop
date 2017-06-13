<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Cuprum" rel="stylesheet">
        <title>Администраторская панель</title>
    </head>
    <body style="font-family: 'Cuprum', serif;">
    <center>
        <h1>Менеджмент</h1>
        <h2>
            <a href="new">Добавить товар</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/managment/BicycleManagment">Список товаров</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${bicycle != null}">
            <form method="post" action="update?id=<c:out value='${bicycle.id}' />">
            </c:if>
            <c:if test="${bicycle == null}">
                <form method="post" action="create">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${bicycle != null}">
                                Редактировать товар
                            </c:if>
                            <c:if test="${bicycle == null}">
                                Добавить товар
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${bicycle != null}">
                        <input type="hidden" name="id" value="<c:out value='${bicycle.id}' />" />
                    </c:if>           
                    <tr>
                        <th>Модель: </th>
                        <td>
                            <input type="text" name="model" size="45"
                                   value="<c:out value='${bicycle.model}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Тип: </th>
                        <td>
                            <input type="text" name="type" size="10"
                                   value="<c:out value='${bicycle.type}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Вес: </th>
                        <td>
                            <input type="text" name="weight" size="5"
                                   value="<c:out value='${bicycle.weight}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Диаметр колеса: </th>
                        <td>
                            <input type="text" name="wheeldiam" size="5"
                                   value="<c:out value='${bicycle.wheeldiam}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Цена: </th>
                        <td>
                            <input type="text" name="price" size="5"
                                   value="<c:out value='${bicycle.price}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Кол-во на складе: </th>
                        <td>
                            <input type="text" name="amount" size="5"
                                   value="<c:out value='${bicycle.stock.amount}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Изображение (путь): </th>
                        <td>
                            <input type="text" name="path" size="45"
                                   value="<c:out value='${bicycle.image.path}' />"
                                   />
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