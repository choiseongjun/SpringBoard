
<html>
<head>
	<title>Home</title>
</head>
<body>
<script>
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};
</script>
<%@ include file="Header.jsp" %>
<%@ include file="Center.jsp" %>
</body>
</html>
