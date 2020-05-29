<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/28/2020
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="navBar.jsp"/>
<div class="container">
    <h1 class="row justify-content-center" >Withdraw Funds</h1>
    <br/>
    <div class="card container-fluid">
        <div class="card-body">
            <form name="accountWithdraw" action="withdraw" method="post">
                <div class="form-group">
    <%-- text, withdraw, and deposit buttons--%>
                    <label for="withdrawAmount">Enter Withdraw Amount:</label>
                    <input type="text" class="form-control" id="withdrawAmount" placeholder="Enter a number with a decimal" name="withdrawAmount">
        <br/>
        <br/>
                    <label for="withdrawAccount">Account: </label>
                    <select class="selectpicker" name="withdrawAccount" id="withdrawAccount">
<%--                    TODO for loop through customers accounts, adding options--%>
                    <c:forEach var="account" items="${customer.accounts}">
                        <option>${account.id}</option>
                    </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Withdraw</button>
            </form>
        <%-- Add Sign up link--%>
        </div>
    </div>
</div>