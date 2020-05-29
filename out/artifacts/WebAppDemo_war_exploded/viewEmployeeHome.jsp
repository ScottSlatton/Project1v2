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
    <h1 class="row justify-content-center" >Welcome, ${employee.firstName}</h1>
    <br/>
  <div class="container-fluid">
    <div class="card">
<%--  <h2>Welcome, ${customer.firstName} ${customer.lastName}</h2>--%>
<%--      <jsp:include page="withdrawForm.jsp"/>--%>

<%--  For Each loop through accounts that need approval--%>
  <c:forEach var="account" items="${customer.accounts}">
      <div class="card-body border">
          <h3 class="row justify-content-center">Account Summary</h3>
              <h3 class="row">Balance: $${account.balance} </h3>
              <h3 class="row">Account# ${account.id}</h3>

          <div class="card">
              <h4 class="row justify-content-center">Transaction History</h4>
              <br/>
  <%--  Add table for transaction history here.--%>
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
                    <td>${transaction.timestamp}</td>
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


<%--<table>--%>
<%--    <tr><td><button class="editbtn">edit</button></td></tr>--%>
<%--    <tr><td><button class="editbtn">edit</button></td></tr>--%>
<%--    <tr><td><button class="editbtn">edit</button></td></tr>--%>
<%--    <tr><td><button class="editbtn">edit</button></td></tr>--%>
<%--</table>--%>

</div>
</body>
</html>
