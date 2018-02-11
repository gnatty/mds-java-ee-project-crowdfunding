<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="container container-site">

    <h1>Create a new project</h1>

    <div class="row">
      <div class="col-lg-6">
        <form method="POST" action="">

          <div class="form-group">
            <label for="inputAddress">Name</label>
            <input type="text" class="form-control" name="frmName">
          </div>

          <div class="form-group">
            <label for="inputAddress">Description</label>
            <textarea class="form-control" name="frmDescription" rows="5"></textarea>
          </div>

          <div class="form-group">
            <label for="inputAddress">Amount</label>
            <input type="text" class="form-control" name="frmAmount">
          </div>

          <div class="form-group">
            <label for="inputAddress">Category</label>
            <select class="custom-select my-1 mr-sm-2" name="frmCategory">
              <c:forEach items="${catList}" var="cat"> 
                <option value="${cat.id}">${cat.name}</option>
              </c:forEach>
            </select>
          </div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary">submit</button>
          </div>

        </form>
      </div>
    </div>
  </div>

</t:template>