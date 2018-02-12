<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="container container-site">

    <h1>Profile : Edit</h1>

    <div class="row">
      <div class="col-lg-6">

        <c:if test="${accDataUpdated}">
          <div class="alert alert-success" role="alert">
            Account successfully updated
          </div>
        </c:if>

        <c:if test="${formError.isErrorField('profileUpdate')==true}">
          <div class="alert alert-danger" role="alert">
            <c:out value="${formError.getMessage('profileUpdate', 'USERNAME_ALREADY_EXIST')}" />
            <c:out value="${formError.getMessage('profileUpdate', 'PASSWORD_NOT_MATCH')}" />
          </div>
        </c:if>

        <form method="POST" action="">

          <div class="form-group">
            <label for="inputAddress">Email</label>
            <input 
            class="form-control <c:out default="" value="${formError.isErrorField('email')!=true?'':'is-invalid'}"/>"
            value="<c:out default="" value="${userLogged.mail}"/>"
            type="text" name="frmEmail">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('email', 'EMPTY_VALUE')}" />
            </div>
          </div>

          <div class="form-group">
            <label for="inputAddress">Firstname</label>
            <input 
            class="form-control <c:out default="" value="${formError.isErrorField('firstname')!=true?'':'is-invalid'}"/>"
            value="<c:out default="" value="${userLogged.firstname}"/>"
            type="text" name="frmFirstname">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('firstname', 'EMPTY_VALUE')}" />
            </div>
          </div>

          <div class="form-group">
            <label for="inputAddress">Lastname</label>
            <input 
            class="form-control <c:out default="" value="${formError.isErrorField('lastname')!=true?'':'is-invalid'}"/>"
            value="<c:out default="" value="${userLogged.lastname}"/>"
            type="text" name="frmLastname">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('lastname', 'EMPTY_VALUE')}" />
            </div>
          </div>

          <hr>

          <div class="form-group">
            <label for="inputAddress">Username</label>
            <input 
            class="form-control <c:out default="" value="${formError.isErrorField('username')!=true?'':'is-invalid'}"/>"
            value="<c:out default="" value="${userLogged.username}"/>"
            type="text" name="frmUsername">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('username', 'EMPTY_VALUE')}" />
            </div>
          </div>

          <div class="form-group">
            <label for="inputAddress">Password</label>
            <input 
            class="form-control <c:out default="" value="${formError.isErrorField('password')!=true?'':'is-invalid'}"/>"
            type="text" name="frmPassword">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('password', 'EMPTY_VALUE')}" />
            </div>
          </div>

          <div class="form-group">
            <label for="inputAddress">Password - confirm</label>
            <input 
            class="form-control <c:out default="" value="${formError.isErrorField('passwordConfirm')!=true?'':'is-invalid'}"/>"
            type="text" name="frmPasswordConfirm">
            <div class="invalid-feedback">
              <c:out value="${formError.getMessage('passwordConfirm', 'EMPTY_VALUE')}" />
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