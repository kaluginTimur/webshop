<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
        <title>Авторизация</title>
    </head>
    <body>
        <nav class="col-1">
            <div class="category-wrap">
                <h3><a href="${pageContext.request.contextPath}/">Велосипеды</a></h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/?action=1">Спортивные</a></li>
                    <li><a href="${pageContext.request.contextPath}/?action=2">Горные</a></li>
                    <li><a href="${pageContext.request.contextPath}/?action=3">Женские</a></li>
                    <li><a href="${pageContext.request.contextPath}/?action=4">Экстремальные</a></li>
                    <li><a href="${pageContext.request.contextPath}/?action=5">Городские</a></li>
                </ul>
            </div>
        </nav>
        <div class="col-2">
            <header>
                <div class="label">
                    <a href="${pageContext.request.contextPath}/">WebShop</a> </br>
                </div>
                <c:if test="${sessionScope.userid == null}">
                    Добро пожаловать, гость!<br>
                    <a href="${pageContext.request.contextPath}/login">Войти</a> | 
                    <a href="${pageContext.request.contextPath}/registration">Регистрация</a> </br>
                    <a href="${pageContext.request.contextPath}/shopcart?action=list">Корзина</a>
                </c:if>
            </header>
            <main class="content">
                <article>
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <table width="105%" cellspacing="0" cellpadding="4">
                            <caption>
                                <h2>Авторизация в системе</h2>
                                <c:if test="${message != null}">
                                    ${message}
                                    </br>
                                </c:if>
                            </caption>
                            <tr> 
                                <td align="right" width="100">Логин:</td> 
                                <td><input type="text" name="user"></td>
                            </tr>
                            <tr>
                                <td align="right" width="100">Пароль:</td>
                                <td><input type="password" name="pwd"></td>
                            </tr>
                            <input type="hidden" name="role"/>
                            <tr> 
                                <td></td>
                                <td><input type="submit" value="Войти"></td>
                            </tr>
                        </table>
                    </form>
                </article>
            </main>
            <footer align="right">Copyright &COPY; 2018</footer>
        </div>
    </body>
</html>