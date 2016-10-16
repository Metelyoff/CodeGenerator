<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/indexstyle.css" />
		<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
		<script type="text/javascript" src="js/signup.js"></script>
		<title>Unlock your locks!</title>
	</head>
	<body style="background-image: url(images/index-background-picture.jpg);">
		<header class="page-header">CODE GENERATOR</header>
		<div class="login-block">
			<form action="RegisterServlet" method="POST">
				<h1 class="login-label">Sign up</h1>
				<input name="userLoginName" type="text" title="Enter your name" size="15" maxlength="15" placeholder="Name" required/>     
				<input name="userLoginMail" type="text" title="Enter your mail" size="45" maxlength="45" placeholder="Mail" required/>  
				<input id="txtuserLoginpassword" name="userLoginpassword" type="password" title="Enter your password" size="30" maxlength="30" placeholder="Password" required/>  
				<input id="txtuserLoginpasswordConfirm" onChange="checkPasswordMatch();" name="userLoginpasswordConfirm" type="password" title="Confirm your password" size="30" maxlength="30" placeholder="Confirm your password" required/>    
				<div id="divCheckPasswordMatch" class="confirm-password"></div>
				<label id="login-label-image" for="login">Press on the lock to sign up</label>
				<input name="Signup" type="image" src="images/lock.png" title="Sign up"/>  
				<hr>
				<div class="signup-block">Have an account? 
					<a href="index.jsp">Login</a>
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
				<p>
					<strong>Test site «Code Generator»</strong>
				</p>
				<p>© Denys Mietielov </p>
			</div>
		</footer>
	</body>
</html>
