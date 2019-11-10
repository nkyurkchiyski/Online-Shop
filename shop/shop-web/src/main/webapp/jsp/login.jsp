<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<c:import url="templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="templates/nav.jsp" />
	</header>
	<div class="container-fluid m-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header form-title bg-warning">
						<h4 class="m-0">Login</h4>
					</div>
					<div class="card-body">
						<form class="form-horizontal"
							action="/online-shop/auth/login" method="post">
							<div class="form-group row">
								<label for="email"
									class="col-md-4 col-form-label text-md-right">E-Mail</label>
								<div class="col-md-6">
									<input type="text" id="email" name="email" placeholder="Email"
										class="form-control" required>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-4 col-form-label text-md-right"
									for="password">Password</label>
								<div class="col-md-6">
									<input type="password" class="form-control" id="password"
										   name="password" placeholder="Password" required>
								</div>
							</div>
							<div class="row d-flex justify-content-around">
								<a class="btn btn-lg btn-light"
									href="/online-shop/auth/register"> Register </a>
								<button type="submit" class="btn btn-lg btn-secondary">
									Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<c:import url="templates/footer.jsp" />
	</div>
</body>
<c:import url="templates/scripts.jsp" />
</html>