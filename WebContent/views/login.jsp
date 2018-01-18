<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>

<div class="container col-5">

<h1>Log In</h1>

<form method="POST" action="">

<div class="form-group">
 <label for="username">Username</label>
 <input 
  class="form-control <c:out default="" value="${empty errorUsername?'':'is-invalid'}"/>" 
  type="text" 
  name="userjjname" 
  value="<c:out default="" value="${param.username}"/>"
   />
 <div class="invalid-feedback">
  Empty username
 </div>
</div>

<div class="form-group">
 <label for="username">Password</label>
 <input 
  class="form-control <c:out default="" value="${empty errorPassword?'':'is-invalid'}"/>" 
  type="text" 
  name="password" 
   />
 <div class="invalid-feedback">
  Empty password
 </div>
</div>

<div class="form-group">
 <button 
  type="submit" 
  class="btn btn-primary">Submit</button>
</div>

</form>

</div>

</t:template>