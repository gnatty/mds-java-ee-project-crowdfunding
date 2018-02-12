<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="container container-site">

    <c:if test="${isUserLogged == true && user.id == userLogged.id}">
      <h1>My profile</h1>
      <a href="#" class="btn btn-secondary">Edit my profile</a>
    </c:if>

    <c:if test="${isUserLogged == false || user.id != userLogged.id}">
      <h1>User profile</h1>
    </c:if>

    <div class="row">
      <div class="col-lg-6">
        <form>

          <div class="form-group">
            <label for="inputAddress">Email</label>
            <input type="text" class="form-control" name="frmEmail" value="${user.mail}" disabled>
          </div>

          <div class="form-group">
            <label for="inputAddress">Firstname</label>
            <input type="text" class="form-control" name="frmFirstName" value="${user.firstname}" disabled>
          </div>

          <div class="form-group">
            <label for="inputAddress">Lastname</label>
            <input type="text" class="form-control" name="frmFirstName" value="${user.lastname}" disabled>
          </div>

          <div class="form-group">
            <label for="inputAddress">Username</label>
            <input type="text" class="form-control" name="frmUsername" value="${user.username}" disabled>
          </div>

          <c:if test="${isUserLogged == true && user.id == userLogged.id}">
            <div class="form-group">
              <label for="inputAddress">Amount</label>
              <input type="text" class="form-control" name="frmAmount" value="${user.credit}" disabled>
            </div>
          </c:if>

        </form>
      </div>
    </div>
  </div>

</t:template>