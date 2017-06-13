<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
        <title>Корзина</title>
    </head>
    <body>
        <nav class="col-1">
            <div class="category-wrap">
                <h3>Навигация</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/">На главную</a></li>
                        <c:if test="${sessionScope.role == 0}">
                        <li><a href="${pageContext.request.contextPath}/managment/BicycleManagment">Менеджмент</a></li>
                        </c:if>
                    <li><a href="${pageContext.request.contextPath}/shopcart">Корзина</a></li>
                        <c:if test="${sessionScope.userid != null}">
                        <li><a href="${pageContext.request.contextPath}/order">История заказов</a></li>
                        </c:if>
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
                    <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                </c:if>
                <c:if test="${sessionScope.userid == null}">
                    Добро пожаловать, гость!<br>
                    <a href="${pageContext.request.contextPath}/login">Войти</a> | 
                    <a href="${pageContext.request.contextPath}/registration">Регистрация</a>
                </c:if>
                <c:if test="${itemList != null}">
                    </br>
                    <a href="${pageContext.request.contextPath}/shopcart/erase">Очистить корзину</a>
                </c:if>
            </header>
            <main class="content">
                <article>
                    <c:if test="${itemList == null}">
                        <h2>Корзина пуста</h2>
                    </c:if>  
                    <c:if test="${itemList != null}">
                        <c:set var="ordercost" value="0"/>
                        <table>
                            <caption><h2>Список покупок</h2></caption>
                            <tr>
                                <th colspan="2">Товар</th>
                                <th>Цена</th>
                                <th>Кол-во</th>
                                <th>Итого</th>
                                <th>Опции</th>
                            </tr>          
                            <c:forEach var="item" items="${itemList}" varStatus="loop">
                                <c:set var="ordercost" value="${ordercost = ordercost + item.price * amounts[loop.count - 1]}"/>
                                <tr>
                                    <td><img src="${item.image.path}" /></td>
                                    <td><c:out value="${item.model}" /></td>
                                    <td><c:out value="${item.price}" /></td>
                                    <td><c:out value="${amounts[loop.count - 1]}" /></td> 
                                    <td><c:out value="${item.price * amounts[loop.count - 1]}" /></td>
                                    <td>
                                        <c:if test="${item.stock.amount > 0}">
                                            <a href="${pageContext.request.contextPath}/shopcart/increase?id=<c:out value='${item.id}' />">+</a>
                                            /
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/shopcart/decrease?id=<c:out value='${item.id}' />">-</a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="${pageContext.request.contextPath}/shopcart/delete?id=<c:out value='${item.id}' />">Удалить позицию</a>                     
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        Итого: ${ordercost} | 
                        <a href="${pageContext.request.contextPath}/order/commit">Сделать заказ</a>
                    </c:if> 
                </article>
            </main>
            <footer align="right">Copyright &COPY; 2017</footer>
        </div>
    </body>
</html>