<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>login</title>
<link rel="stylesheet" type="text/css" href="login.css" />
</head>
<body>
<jsp:include page="../rcs/header.jsp"></jsp:include>

<form id="loginform" action="validate" method="post">

	<input type="text" name="uid" class="input" placeholder="Username"> <br>
	<input type="password" name="pwd" class="input" placeholder="Password"> <br>
	<input type="submit" value="Login" class="loginbutton">
	
</form>

${msg }
<jsp:include page="../rcs/footer.jsp" ></jsp:include>
</body>
</html>