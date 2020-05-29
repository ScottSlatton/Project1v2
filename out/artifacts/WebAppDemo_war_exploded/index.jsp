<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 5/19/2020
  Time: 7:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
  <jsp:include page="navBar.jsp"/>
<div class="container">
  <h1 class="row justify-content-center" >Monet & Bagges Financial</h1>
  <br/>

  <c:if test="${pageContext.request.requestURI.endsWith('/')}">
    <jsp:include page="customerLoginForm.jsp"/>
  </c:if>

<%--  <c:if test="${pageContext.request.requestURI.endsWith('/signup')}">--%>
<%--    <jsp:include page="signUpForm.jsp"/>--%>
<%--  </c:if>--%>

</div>

<div class="row justify-content-center">
  <a href="/employeelogin">Employee Portal</a>
</div>
</body>
</html>
