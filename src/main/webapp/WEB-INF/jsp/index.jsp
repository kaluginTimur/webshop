<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
        <title>Главная</title>
    </head>
    <body>
        <nav class="col-1">
            <div class="category-wrap">
                <h3><a href="${pageContext.request.contextPath}/">Велосипеды</a></h3>
                <ul>
                    <li><a href="?action=1">Спортивные</a></li>
                    <li><a href="?action=2">Горные</a></li>
                    <li><a href="?action=3">Женские</a></li>
                    <li><a href="?action=4">Экстремальные</a></li>
                    <li><a href="?action=5">Городские</a></li>
                </ul>
            </div>
        </nav>
        <div class="col-2">
            <header>
                <div class="label">
                    <a href="${pageContext.request.contextPath}/">WebShop</a> </br>
                </div>
                <c:if test="${sessionScope.userid != null}">
                    Добро пожаловать, ${sessionScope.user}!<br>
                    <a href="${pageContext.request.contextPath}/cabinet">Личный кабинет</a> |
                    <a href="${pageContext.request.contextPath}/shopcart">Корзина</a> |
                    <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                </c:if>
                <c:if test="${sessionScope.userid == null}">
                    Добро пожаловать, гость!<br>
                    <a href="${pageContext.request.contextPath}/login">Войти</a> | 
                    <a href="${pageContext.request.contextPath}/registration">Регистрация</a> </br>
                    <a href="${pageContext.request.contextPath}/shopcart">Корзина</a>
                </c:if>
            </header>
            <main class="content">
                <article>
                    <c:if test="${bicycleList == null}">
                    </c:if>
                    <c:if test="${bicycleList != null}">
                        <table>
                            <caption><h2>Список товаров</a></caption>
                            <tr>
                                <th colspan="2">Модель</th>
                                <th>Тип</th>
                                <th>Вес</th>
                                <th>Диаметр колёс</th>
                                <th>Цена</th>
                                <th>Опции</th>
                            </tr>
                            <c:forEach var="bicycle" items="${bicycleList}" varStatus="loop">
                                <tr>
                                    <td><img src="${bicycle.image.path}"/></td>
                                    <td><c:out value="${bicycle.model}" /></td>
                                    <td><c:out value="${bicycle.type}" /></td>
                                    <td><c:out value="${bicycle.weight}" /></td>
                                    <td><c:out value="${bicycle.wheeldiam}" /></td>
                                    <td><c:out value="${bicycle.price}" /></td>
                                    <td>
                                        <c:if test="${messageid == bicycle.id}">
                                            ${message}
                                            </br>
                                        </c:if>
                                        <c:if test="${bicycle.stock.amount > 0}">
                                            <a href="shopcart/add?id=<c:out value='${bicycle.id}' />">Добавить в корзину</a>
                                            </br>
                                            Товара на складе: ${bicycle.stock.amount}
                                        </c:if>
                                        <c:if test="${bicycle.stock.amount == 0}">
                                            Товара нет на складе!
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </article>
            </main>
            <footer align="right">Copyright &COPY; 2018</footer>
        </div>
    </body>
</html>
