<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/28/2020
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="navBar.jsp"/>
<div class="container">
    <h1 class="row justify-content-center" >New Customer Sign Up</h1>
    <br/>
        <div class="card container-fluid">
            <div class="card-body">
                <form name="signUpForm" action="signup" method="post">
                    <div class="form-group">
                        <label for="inputEmail">Email address</label>
                        <input type="email" class="form-control" id="inputEmail" placeholder="Enter email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="inputFirstName">First Name</label>
                        <input type="text" class="form-control" id="inputFirstName" placeholder="Enter First Name" name="firstName">
                    </div>
                    <div class="form-group">
                        <label for="inputFirstName">Last Name</label>
                        <input type="text" class="form-control" id="inputLastName" placeholder="Enter Last Name" name="lastName">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
</div>