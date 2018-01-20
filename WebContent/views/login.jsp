<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

<div class="container col-5">

<h1>Log In</h1>

<form method="POST" action="">

<div class="form-group">
 <label for="username">UserName</label>
 <input 
  class="form-control <c:out default="" value="${formError.isErrorField('username')!=true?'':'is-invalid'}"/>" 
  type="text" 
  name="username"
  value="<c:out default="" value="${param.username}"/>"
   />
 <div class="invalid-feedback">
  <c:out value="${formError.getMessage('username', 'EMPTY_VALUE')}" />
 </div>
</div>

<div class="form-group">

 <label for="username">Password</label>
 <input 
  class="form-control <c:out default="" value="${formError.isErrorField('password')!=true?'':'is-invalid'}"/>" 
  type="text" 
  name="password" 
   />
 <div class="invalid-feedback">
  <c:out value="${formError.getMessage('password', 'EMPTY_VALUE')}" />
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