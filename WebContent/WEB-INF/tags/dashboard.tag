<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>

<div class="container col-9">
	<nav class="navbar navbar-light bg-light" style="margin: 25px 0px;">
	  <a class="navbar-brand">Dashboard</a>
	</nav>
	<div class="row">
		<div class="col-3">
			<ul class="list-group">
				<li class="list-group-item list-group-item-dark">Product</li>
				<li class="list-group-item"><a href="${projetDir}/dashboard/product/list">product list</a></li>
				<li class="list-group-item"><a href="${projetDir}/dashboard/product/detail">product detail</a></li>
				<li class="list-group-item"><a href="${projetDir}/dashboard/product/add">add new product</a></li>
				<li class="list-group-item list-group-item-dark">Category</li>
				<li class="list-group-item"><a href="${projetDir}/dashboard/category/add">add new category</a></li>
				<li class="list-group-item list-group-item-dark">User account</li>
				<li class="list-group-item"><a href="${projetDir}/logout">Log-out</a></li>
			</ul>	
		</div>
		<div class="col-9">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">page_name</h4>
					<div class="card-text">
						<jsp:doBody/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</t:template>

