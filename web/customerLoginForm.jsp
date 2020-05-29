<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/27/2020
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <br/>
    <h1 class="row justify-content-center">Customer Login</h1>
    <div class="card-body">
    <form name="loginForm" action="login" method="post">
        <div class="form-group">
            <label for="inputEmail">Email address</label>
            <input type="email" class="form-control" id="inputEmail" placeholder="Enter email" name="email">
        </div>
        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

<%-- Add Sign up link--%>
    </div>
</div>
