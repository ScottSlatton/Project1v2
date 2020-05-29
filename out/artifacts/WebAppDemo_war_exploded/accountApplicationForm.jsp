<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/28/2020
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% response.setHeader("Cache-Control", "no-cache,no-store, must-revalidate");
    if(session.getAttribute("customer") == null) response.sendRedirect("index.jsp");
%>

<jsp:include page="navBar.jsp"/>
<div class="container">
    <h1 class="row justify-content-center" >Apply for New Bank Account</h1>
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
                        <input class="form-check-input" type="radio" name="accountType" id="accountType2" value="SAVINGS" checked>
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