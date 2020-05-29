<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/28/2020
  Time: 8:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>M&B Financial</title>
    <link rel="stylesheet" type="text/css" href="./style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>

<%--<nav class="navbar navbar-dark bg-dark">--%>
<%--    <span class="navbar-brand mb-0 h1">Monet & Bagges</span>--%>
<%--    --%>
<%--&lt;%&ndash;    if session has a user, show logout link&ndash;%&gt;--%>
<%--    <% if()%>--%>
<%--    <%= <a class="navbar-brand" href="#">Navbar</a> %>--%>
<%--    --%>
<%--</nav>--%>
<%--<br/>--%>
<%--<br/>--%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <span class="navbar-brand">Monet & Bagges</span>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">

            <c:if test="${customer == null && employee == null}">
                <a href="${pageContext.request.contextPath}/" class="navbar-brand">Home</a>
            </c:if>


            <c:if test="${customer != null}">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/customerhome">Home <span class="sr-only">(current)</span></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Account
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/accountapply">Apply for Account</a>
                        <c:if test="${customer.accounts != []}">
                        <a class="dropdown-item" href="/withdraw">Withdraw</a>
                        <a class="dropdown-item" href="/deposit">Deposit</a>
                        <a class="dropdown-item" href="/transfer">Transfer</a>
                        </c:if>
                    </div>
                </li>


            </c:if>

            <c:if test="${customer == null && employee == null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/signup">SignUp</a>
                </li>
            </c:if>

            <c:if test="${customer != null || employee != null}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
            </c:if>



<%--            <li class="nav-item">--%>
<%--                <a class="nav-link disabled" href="#">Disabled</a>--%>
<%--            </li>--%>
        </ul>
    </div>
</nav>
<br/>
<br/>
