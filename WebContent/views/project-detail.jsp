<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="projectInfo">
    <div class="container">
      <h1 class="projectName">${project.name}</h1>
    </div>
  </div>

  <div class="container container-site">

    <div class="row">
      <div class="col-lg-12">
        <span class="badge badge-pill badge-warning curAmountProjectPage">${amount}$ / ${project.amount}$ -- day left : ${project.dayLeft}</span>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-6">
        <h1>Description</h1>
        <p>${project.description}</p>
      </div>

      <div class="col-lg-6">

        <c:if test="${isUserLogged == true && project.dayLeft > 0}">
          <h1>Help this project</h1>

          <c:if test="${formError.isErrorField('amount')==true}">
            <div class="alert alert-danger" role="alert">
              <c:out value="${formError.getMessage('amount', 'CONTRIBUTION_LOWER')}" />
              <c:out value="${formError.getMessage('amount', 'AMOUNT_EMPTY_VALUE')}" />
            </div>
          </c:if>

          <form method="POST" action="">
            <div class="input-group input-group-lg">
              <input type="number" name="frmAmount" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
              <div class="input-group-append">
                <span class="input-group-text" id="inputGroup-sizing-lg">$</span>
              </div>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btnHelpSubmit">Submit</button>
            </div>
          </form>
        </c:if>

        <h1>Contribution</h1>
        <table class="table table-striped table-dark">
          <tbody>
            <c:forEach items="${projectContri}" var="contri"> 
              <tr>
                <th scope="row">${contri.amount}$</th>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      
    </div>

  </div>

</t:template>


