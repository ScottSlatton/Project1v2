<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/28/2020
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% response.setHeader("Cache-Control", "no-cache,no-store, must-revalidate");
    if(session.getAttribute("customer") == null) response.sendRedirect("/");
%>

<jsp:include page="navBar.jsp"/>
<div class="container">
    <h1 class="row justify-content-center" >Deposit Funds</h1>
    <br/>
    <div class="card container-fluid">
        <div class="card-body">
            <form name="accountDeposit" action="deposit" method="post">
                <div class="form-group">
    <%-- text, deposit, and deposit buttons--%>
                    <label for="depositAmount">Enter Deposit Amount:</label>
                    <input type="number" class="form-control" id="depositAmount" placeholder="Enter a number:" name="depositAmount">
        <br/>
        <br/>
                    <label for="depositAccount">Account: </label>
                    <select class="selectpicker" name="depositAccount" id="depositAccount">
<%--                    TODO for loop through customers accounts, adding options--%>
                    <c:forEach var="account" items="${customer.accounts}">
                        <option>${account.accountType}: ${account.id}</option>
                    </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Deposit</button>
            </form>
        <%-- Add Sign up link--%>
        </div>
    </div>
</div>