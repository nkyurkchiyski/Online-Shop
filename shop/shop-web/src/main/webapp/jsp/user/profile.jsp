<%@page import="com.example.shop.base.dto.ProductDto"%>
<%@page import="com.example.shop.base.dto.UserProfileDto"%>
<%@page import="java.util.List"%>
<%@page import="com.example.shop.base.dto.CategoryProductsDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    UserProfileDto user = (UserProfileDto)request.getAttribute("userProfile");
%>
<html>
<head>
<c:import url="../templates/head.jsp" />
</head>
<body>
	<header>
		<c:import url="../templates/nav.jsp" />
	</header>
	<div class="container-fluid my-5">
		<div class="text-center pb-4">
			<h2>
				Profile:
				<%=user.getEmail()%>
			</h2>
		</div>
		<div class="d-flex justify-content-center">
			<div style="width: 70%;">
				<div class="row">
					<div class="col-2">
						<div class="nav flex-column nav-pills" id="v-pills-tab"
							role="tablist" aria-orientation="vertical">
							<a class="nav-link active" id="v-pills-edit-tab"
								data-toggle="pill" href="#v-pills-edit" role="tab"
								aria-controls="v-pills-edit" aria-selected="true">Edit</a> <a
								class="nav-link" id="v-pills-delete-tab" data-toggle="pill"
								href="#v-pills-delete" role="tab" aria-controls="v-pills-delete"
								aria-selected="false">Delete</a>
						</div>
					</div>
					<div class="col-10">
						<div class="tab-content" id="v-pills-tabContent">
							<div class="tab-pane fade show active" id="v-pills-edit"
								role="tabpanel" aria-labelledby="v-pills-edit-tab">
								<div class="col-md-12">
									<div class="card shadow">
										<div class="card-header form-title bg-warning">
											<h4 class="m-0">Edit Profile</h4>
										</div>
										<div class="card-body">
											<form name="authenticate" class="form-horizontal"
												action="/online-shop/user/edit" method="post">
												<input type="hidden" id="user_email" class="form-control"
													name="email" value="<%=user.getEmail()%>" required>
												<div class="form-group row">
													<label class="col-md-4 col-form-label text-md-right">First
														Name</label>
													<div class="col-md-6">
														<input type="text" id="first_name"
															placeholder="First Name" class="form-control"
															name="firstName" value="<%=user.getFirstName()%>"
															required>
													</div>
												</div>
												<div class="form-group row">
													<label class="col-md-4 col-form-label text-md-right">Last
														Name</label>
													<div class="col-md-6">
														<input type="text" id="last_name" placeholder="Last Name"
															class="form-control" name="lastName"
															value="<%=user.getLastName()%>" required>
													</div>
												</div>
												<div class="form-group row">
													<label class="col-md-4 col-form-label text-md-right">Address</label>
													<div class="col-md-6">
														<input type="text" id="address" placeholder="Address"
															class="form-control" name="address"
															value="<%=user.getAddress()%>" required>
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
												<div class="row d-flex justify-content-center">
													<button type="submit" class="btn btn-lg btn-secondary">
														Submit</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="v-pills-delete" role="tabpanel"
								aria-labelledby="v-pills-delete-tab">
								<div class="col-md-12">
									<div class="card shadow">
										<div class="card-header form-title bg-warning">
											<h4 class="m-0">Delete Profile</h4>
										</div>
										<div class="card-body">
											<form name="authenticate" class="form-horizontal"
												action="/online-shop/user/delete" method="post">
												<input type="hidden" id="user_email" class="form-control"
													name="email" value="<%=user.getEmail()%>" required>
												<h3>If you delete your account:</h3>
												<ul>
													<li>You can't regain access to your account.</li>
													<li>It might take up to 90 days to delete data stored
														in backup systems. Your information isn't accessible
														during this time.</li>
													<li>Copies of some materials such as log records may
														remain in our database.</li>
													<li>For legal purposes (e.g. to address fraud and
														other illegal activity), we may preserve your information.
														Please refer to the Law and Protection section of our
														Privacy Policy for more information.</li>
												</ul>
												<div class="row d-flex justify-content-center">
													<button type="submit" class="btn btn-lg btn-secondary">
														Delete</button>
												</div>
											</form>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
		<c:import url="../templates/footer.jsp" />
	</div>
</body>
<c:import url="../templates/scripts.jsp" />
</html>