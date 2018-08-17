<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp"%>
</head>
<style>
label {
  background-color: pink;
  color: black;
  font-weight: bold;
  padding: 4px;
  text-transform: uppercase;
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: xx-small;
}

</style>
<body>
<form name="form1" method="post"
	action="${path}/member/insert.do">
  <div class="form-group">
    <label for="exampleInputEmail1">ID</label>
    <input  class="form-control" id="exampleInputEmail1" name="userid" aria-describedby="emailHelp" placeholder="Enter ID">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="passwd" placeholder="Password">
  </div>
    <div class="form-group">
    <label for="exampleInputPassword1">Name</label>
    <input  class="form-control" id="exampleInputPassword1" name="name" placeholder="name">
  </div>
    <div class="form-group">
    <label for="exampleInputPassword1">email</label>
    <input type="email" class="form-control" name="email" id="exampleInputPassword1" placeholder="email">
  </div>
  	<input type="submit" class="btn btn-primary" value="가입하기">
</form>
</body>
</html> 