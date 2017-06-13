<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
        <title>Заказы</title>
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
                    <a href="${pageContext.request.contextPath}/shopcart/erase">Очистить корзину</a> | 
                    <a href="${pageContext.request.contextPath}/order/commit">Заказать</a>
                </c:if>
            </header>
            <main class="content">
                <article>
                    <c:if test="${orders == null}">
                        <h2>Вы не делали заказов</h2>
                    </c:if>
                    <c:if test="${orders != null}">
                        <table>
                            <caption><h2>Список совершённых заказов</h2></caption>
                            <tr>
                                <c:if test="${sessionScope.role == 0}">
                                    <th>UserID</th>
                                    </c:if>
                                <th>Номер</th>
                                <th>Список покупок</th>
                                <th>Стоимость</th>
                                <th>Статус</th>
                                <th>Опции</th>
                            </tr>          
                            <c:forEach var="order" items="${orders}" varStatus="loop">
                                <tr>
                                    <c:if test="${sessionScope.role == 0}">
                                        <td>${order.userid}</td>
                                    </c:if>
                                    <td><c:out value="${order.id}" /></td>
                                    <td>${order.items}</td>
                                    <td><c:out value="${order.price}" /></td> 
                                    <td>
                                        <c:if test="${order.status == -1}">
                                            В очереди на удаление
                                        </c:if>
                                        <c:if test="${order.status == 0}">
                                            На рассмотрении
                                        </c:if> 
                                        <c:if test="${order.status == 1}">
                                            Сборка заказа
                                        </c:if> 
                                        <c:if test="${order.status == 2}">
                                            В пути
                                        </c:if> 
                                        <c:if test="${order.status == 3}">
                                            Доставлен
                                        </c:if> 
                                        </br></br>
                                        <c:if test="${sessionScope.role == 0}">
                                            <form action="${pageContext.request.contextPath}/order/changestatus?orderId=${order.id}&managmentUserIdOrder=${order.userid}" method="post">
                                                <select required name="orderStatus"><option></option>
                                                    <option value="-1">Удаление</option>
                                                    <option value="0">Обработка</option>
                                                    <option value="1">Сборка</option>
                                                    <option value="2">В пути</option>
                                                    <option value="3">Доставлен</option></select>
                                                <input type="submit" value="ОК">
                                            </form>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${sessionScope.role != 0 && order.status != -1 && order.status != 3}">
                                            <a href="${pageContext.request.contextPath}/order/changestatus?orderId=<c:out value='${order.id}' />&orderStatus=-1">Отменить заказ</a>  
                                            </br>
                                            <a href="${pageContext.request.contextPath}/order/changestatus?orderId=<c:out value='${order.id}' />&orderStatus=3">Подтвердить доставку</a>
                                        </c:if>
                                        <c:if test="${order.status == -1 && sessionScope.role == 0}">
                                            <a href="${pageContext.request.contextPath}/order/delete?orderId=<c:out value='${order.id}' />">Удалить</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </article>
            </main>
            <footer align="right">Copyright &COPY; 2017</footer>
        </div>
    </body>
</html>