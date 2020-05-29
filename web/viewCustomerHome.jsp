<%@ page import="com.banking.models.Customer" %>
<%@ page import="com.banking.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="com.banking.models.Transaction" %><%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/19/2020
  Time: 7:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<%--<%Customer customer = (Customer) session.getAttribute("customer");--%>
<%-- List<Account> accounts = customer.getAccounts();--%>
<%--  for (Account account: accounts) {--%>
<%--    List<Transaction> transactions = account.getTransactions();--%>

<%--  }--%>
<%--%>--%>
<header>
  <jsp:include page="navBar.jsp"/>
</header>
<div class="container">
<%--<h1>Monet & Bagges Customer Home</h1>--%>
    <h1 class="row justify-content-center" >Welcome, ${customer.firstName}</h1>
    <br/>
  <div class="container-fluid">
    <div class="card">
        <c:if test="${customer.accounts == []}">
<%--            Only if customer has no accounts--%>
            <div class="container">
                <h1 class="row justify-content-center">Apply for New Bank Account</h1>
                <br/>
                <p class="row justify-content-center">Please select the type of account you'd like to apply for.</p>
                <p class="row justify-content-center">Account will be processed pending employee approval.</p>
                <div class="card container-fluid">
                    <div class="card-body">
                        <form name="accountApplicationForm" action="accountapply" method="post">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="accountType" id="accountType1" value="CHECKING" checked>
                                <label class="form-check-label" for="accountType1">
                                    New Checking Account
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="accountType" id="accountType2" value="SAVINGS" >
                                <label class="form-check-label" for="accountType2">
                                    New Savings Account
                                </label>
                            </div>
                            <br/>

                            <button type="submit" class="btn btn-primary">Apply</button>
                        </form>
                    </div>
                </div>
            </div>
            <br/>
        </c:if>

<%--  For Each loop through accounts--%>
  <c:forEach var="account" items="${customer.accounts}">
      <div class="card-body border">
          <h1 class="row justify-content-center">Account Summary</h1>
              <h2 class="row justify-content-around">Balance: $${account.balance} </h2>
              <h3 class="row justify-content-around">Type: ${account.accountType} </h3>
              <h4 class="row justify-content-around">Account#: ${account.id}</h4>

          <div class="card">
              <br/>
              <h2 class="row justify-content-center">Transaction History</h2>
              <br/>
              <div class="card-body">
                <table class="table">
                  <thead class="thead-dark">
                  <tr>
                    <th scope="col">Sender Account#</th>
                    <th scope="col">Receiver Account#</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Time</th>
                  </tr>
                  </thead>
                  <tbody>
        <%--          For Each loop through transactions--%>
                  <c:forEach var="transaction" items="${account.transactions}">
                  <tr>
                    <th scope="row">${transaction.sender.id}</th>
                    <td>${transaction.receiver.id}</td>
                    <td>$${transaction.amount}</td>
                      <fmt:formatDate type = "both" var="time" value="${transaction.timestamp}"/>
                    <td>${time}</td>
                  </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
          </div>
      </div>
  </c:forEach>
      </div>
    </div>
</div>
</div>
</body>
</html>
