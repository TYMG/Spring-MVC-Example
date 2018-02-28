<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page session="false" %>
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>

	<title>Message</title>
</head>
<body class="container">
<h1>
	Create Message
</h1>

<div>
<p>  Please fill out the form to create and save a message </p>
</div>

<form:form action="/SpringMVCExample/messages/" method="post" modelAttribute="message">
<%-- <div class="form-group row">
    <div class="col-sm-6">
        <label for="id">Id</label>
        <form:input path="id" class="form-control" />
    </div>
  </div> --%>
 <div class="form-group row">
    <div class="col-sm-6">
        <label for="content">Message Text</label>
        <form:textarea path="content" class="form-control" rows="3" cols="30"/>
        <small id="emailHelp" class="form-text text-muted">100 Characters Max</small> 
    </div>
  </div>
  <button type="submit" class="btn btn-primary" value="Subimit">Create Message</button>
</form:form>

<script type="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
