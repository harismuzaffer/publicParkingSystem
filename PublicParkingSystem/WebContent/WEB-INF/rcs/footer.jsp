<html>
<head>
<title>Footer</title>
</head>
<body >

<%

//HttpSession ss=request.getSession();
//if(session.getAttribute("uname")==null)
	//response.sendRedirect("index.jsp");

%>
<style>

#footer {
   position:fixed;
   left:0px;
   bottom:0px;
   height:30px;
   width:100%;
   background:#999;
}

</style>
<p id="footer">
<%= new java.util.Date().toString()%>
</p>
</body>
</html>