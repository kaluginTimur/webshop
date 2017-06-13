<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
        <title>Личный кабинет</title>
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
            </header>
            <main class="content">
                <article>
                    <c:if test="${sessionScope.userid != null}">
                        <h3>Добро пожаловать, ${sessionScope.user} [ID: ${sessionScope.userid}].</h3>
                        <c:if test="${message != null}">
                            ${message}
                            <br>
                        </c:if>
                        <br>
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <input type="submit" value="Выйти" >
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.userid == null}">
                        <h3>Добро пожаловать, гость! [SessionID: ${pageContext.session.id}].</h3>
                        <form action="${pageContext.request.contextPath}/login" method="post">
                            <input type="submit" value="Войти" >
                        </form>
                    </c:if>                    
                </article>
            </main>
            <footer align="right">Copyright &COPY; 2017</footer>
        </div>
    </body>
</html>
