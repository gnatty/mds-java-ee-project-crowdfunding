<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="container container-site">

    <h1>Current projects</h1>

    <div class="row">
      <c:forEach items="${projectList}" var="project"> 
        <div class="col-lg-4 projectPreview">
          <div class="card">
            <div class="fakeImg">
              <h1 class="title">${project.name}</h1>
              <span class="badge badge-pill badge-warning curAmount">Needs ${project.amount}$ -- day left : ${project.dayLeft}</span>
            </div>
            <p>${project.description}</p>
            <a href="${projetDir}/project/detail/${project.id}" class="btn btn-secondary">More details</a>
          </div>
        </div>
      </c:forEach>

    </div>
  </div>

</t:template>