<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<%@ page import="java.util.List" %>
		<%@ page import="java.sql.*,
					javax.servlet.http.*,
					java.util.*,
					todo.dao.*,
		 			todo.model.*,
					javax.servlet.*,
					java.io.IOException" %>
	<html>

	<head>
		<title>User Management Application</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>

	<body>
		<header>
			<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
				<div>
					<a href="#" class="navbar-brand"> Todo
						App</a>
				</div>

				<ul class="navbar-nav">
					<li><a href="${pageContext.request.contextPath}/list" class="nav-link">Todos</a></li>
				</ul>

				<ul class="navbar-nav navbar-collapse justify-content-end">
					<li><a href="${pageContext.request.contextPath}/logout" class="nav-link">Logout</a></li>
				</ul>
			</nav>
		</header>
<h1>Todo Details</h1>
<% Todo todo=(Todo) request.getAttribute("todo"); %>
<div class="container col-md-5">
<div class="card">
				<div class="card-body">
					<% if (todo !=null) { %>
						<form action="update" method="post">
							<% } else { %>
								<form action="insert" method="post">
									<% } %>

										<caption>
											<h2>
												<% if (todo !=null) { %>
													Edit Todo
													<% } else { %>
														Add New Todo
														<% } %>
											</h2>
										</caption>

										<% if (todo !=null) { %>
											<input type="hidden" name="id" value="<%= todo.getId() %>">
											<% } %>

											<% if (todo !=null) { %>
												<input type="hidden" name="username" value="<%= todo.getUsername() %>">
												<% } %>

												<fieldset class="form-group">
													<label>Todo Title</label>
													<input type="text" class="form-control" name="title"
														required="required" minlength="5"
														value="<%= (todo != null) ? todo.getTitle() : "" %>">
												</fieldset>

												<fieldset class="form-group">
													<label>Todo Description</label>
													<input type="text" class="form-control" name="description"
														minlength="5"
														value="<%= (todo != null) ? todo.getDescription() : "" %>">
												</fieldset>

											<fieldset class="form-group">
												<label>Todo Status</label>
												<select class="form-control" name="isDone">

<option value="false" <%=(todo !=null && !todo.getStatus()) ? "selected" : "" %>>In Progress</option>
<option value="true" <%=(todo !=null && todo.getStatus()) ? "selected" : "" %>>Complete</option>

												</select>
											</fieldset>

											

												<fieldset class="form-group">
													<label>Todo Target Date</label>
													<input type="date" class="form-control" name="targetDate"
														required="required"
														value="<%= (todo != null) ? todo.getTargetDate() : "" %>">
												</fieldset>

												<button type="submit"  class="btn btn-success">Save</button>
								</form>
					</div>
				</div> 
			</div>

			 

	

		
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</body>

		</html>