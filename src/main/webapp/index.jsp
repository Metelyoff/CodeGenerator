<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/indexstyle.css" />
		<title>Unlock your locks!</title>
	</head>
	<body style="background-image: url(images/index-background-picture.jpg);">
		<header class="page-header">CODE GENERATOR</header>
			<div class="login-block">
				<form method="post" action="LoginServlet">
					<h1 class="login-label">Login</h1>
					<input name="userLoginMail" type="text" title="Enter your mail" value="" size="45" maxlength="45" placeholder="Mail"/> 
					<input name="userLoginpassword" type="password" title="Enter your password" value="" size="30" maxlength="30" placeholder="Password"/> 
					<label id="login-label-image" for="login">Press on the lock to login</label>
					<input name="login" type="image" src="images/lock.png" title="Login" value="Login"/>
					<hr>
					<div class="signup-block">
						Don't have an account? <a href="signup.jsp">Sign up</a>
					</div>
				</form>
			</div>
			<div class="welcome-text">
				Welcome to the code generator site!</br>
				You can add your locks and generate all possible values for open it.</br>
				Can try right now. </br>
				Good luck!
			</div>
		<footer class="page-footer">
			<div class="copyright">
				<p><strong>Test site «Code Generator»</strong></p>
				<p>© Denys Mietielov </p>
			</div>
		</footer>
	</body>
</html>
