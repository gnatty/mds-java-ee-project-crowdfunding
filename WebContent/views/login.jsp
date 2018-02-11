<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="container container-site">

    <h1>Sign in</h1>

    <div class="row">
      <div class="col-lg-6">

        <c:if test="${formError.isErrorField('login')==true}">
          <div class="alert alert-danger" role="alert">
            <c:out value="${formError.getMessage('login', 'WRONG_CREDENTIALS')}" />
          </div>
        </c:if>

        <form method="POST" action="">

          <div class="form-group">
            <label for="inputAddress">Username</label>
            <input 
              type="text" 
              class="form-control <c:out default="" value="${formError.isErrorField('username')!=true?'':'is-invalid'}"/>"
              value="<c:out default="" value="${param.frmUsername}"/>"
              name="frmUsername">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('username', 'EMPTY_VALUE')}" />
           </div>
          </div>

          <div class="form-group">
            <label for="inputAddress">Password</label>
            <input 
              type="text" 
              class="form-control <c:out default="" value="${formError.isErrorField('password')!=true?'':'is-invalid'}"/>" 
              name="frmPassword">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('password', 'EMPTY_VALUE')}" />
            </div>
          </div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary">submit</button>
          </div>

        </form>
      </div>
    </div>
  </div>

</t:template>