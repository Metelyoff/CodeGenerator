
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	 <link rel="stylesheet" type="text/css" href="css/style.css">
	 <title>logout Page</title>
</head>
<body>
	 <%		
		 session.removeAttribute("userMail");
		 session.removeAttribute("password");
		 session.invalidate();
	 %>
<center>
	 <h1>You have successfully logged out</h1>
	 To login again <a href="index.jsp">click here</a>.
</center>
</body>
</html>