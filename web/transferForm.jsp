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
    <h1 class="row justify-content-center" > Transfer Funds Between Accounts</h1>
    <br/>
    <div class="card container-fluid">
        <div class="card-body">
            <form name="accountTransfer" action="transfer" method="post">
                <div class="form-group">
                    <c:forEach var="account" items="${customer.accounts}">
                        <div class="card container-fluid">
                            <div class="card-body">
                                <h3>Account: ${account.id}</h3>
                                <h3>Available Balance: $${account.balance}</h3>
                            </div>
                        </div>
                    </c:forEach>
                    <br/>
                    <label for="transferAmount">Enter Transfer Amount:</label>
                    <input type="number" class="form-control" id="transferAmount" placeholder="Enter a number:" name="transferAmount">
        <br/>
        <br/>

                    <label for="senderAccount">Transfer From Account: </label>
                    <select class="selectpicker" name="senderAccount" id="senderAccount">
<%--                    TODO for loop through customers accounts, adding options--%>
                    <c:forEach var="account" items="${customer.accounts}">
                        <option>${account.id}</option>
                    </c:forEach>
                    </select>
        <br/>
                    <label for="receiverAccount">Transfer to: </label>
                    <input type="text" class="form-control" id="receiverAccount" placeholder="Enter Account Number:" name="receiverAccount">
                </div>
                <button type="submit" class="btn btn-primary">Transfer</button>
            </form>
        <%-- Add Sign up link--%>
        </div>
    </div>
</div>