<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="formError" class="utils.FormErrorUtils">
 <jsp:setProperty name="formError" property="initFrom" value="${empty errors ? null : errors}" />
</jsp:useBean>

<t:template>

  <div class="container container-site">

    <h1>Categories</h1>

    <div class="row">
      <div class="col-lg-4">
        <ul class="list-group categoriesList">
          <li class="list-group-item">
              <a href="${projetDir}/project/by-category/">Show all</a>
          </li>
          <c:forEach items="${catList}" var="cat"> 
            <li class="list-group-item">
              <a href="${projetDir}/project/by-category/${cat.label}">${cat.name}</a>
            </li>
          </c:forEach>
        </ul>
      </div>

      <div class="col-lg-8">
        <div class="row">
          <c:forEach items="${projectList}" var="project"> 
            <div class="col-lg-12 projectPreview">
              <div class="card">
                <div class="fakeImg">
                  <h1 class="title">${project.name}</h1>
                  <span class="badge badge-pill badge-warning curAmount">100%</span>
                </div>
                <p>${project.description}</p>
                <a href="${projetDir}/project/detail/${project.id}" class="btn btn-secondary">More details</a>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>


  </div>

</t:template>