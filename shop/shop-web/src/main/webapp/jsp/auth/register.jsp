<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>
	<div class="container-fluid my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header form-title bg-warning">
						<h4 class="m-0">Register</h4>
					</div>
					<div class="card-body">
						<form name="authenticate" class="form-horizontal"
							action="/online-shop/auth/register" method="post">
							<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right">E-Mail</label>
								<div class="col-md-6">
									<input type="text" id="user_email" placeholder="Username/Email"
										class="form-control" name="email" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right">First
									Name</label>
								<div class="col-md-6">
									<input type="text" id="first_name" placeholder="First Name"
										class="form-control" name="firstName" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right">Last
									Name</label>
								<div class="col-md-6">
									<input type="text" id="last_name" placeholder="Last Name"
										class="form-control" name="lastName" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right">Address</label>
								<div class="col-md-6">
									<input type="text" id="address" placeholder="Address"
										class="form-control" name="address" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right">Password</label>
								<div class="col-md-6">
									<input type="password" class="form-control" id="password"
										placeholder="Password" name="password" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right">Confirm
									Password</label>
								<div class="col-md-6">
									<input type="password" class="form-control"
										id="comfirm_password" placeholder="Confirm Password"
										name="confirmPassword" required>
								</div>
							</div>
							<div class="row d-flex justify-content-around">
								<a class="btn btn-lg btn-light" href="/online-shop/auth/login">
									Login </a>
								<button type="submit" class="btn btn-lg btn-secondary">
									Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>