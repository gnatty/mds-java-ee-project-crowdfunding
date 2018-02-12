<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
  <head>
    <title>ExosSupCommerce</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link 
      rel="stylesheet"
      href="https://getbootstrap.com/dist/css/bootstrap.min.css"
    />

    <style type="text/css">
.container-site {
  margin: 30px auto;
}

.projectPreview {
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
}

.projectPreview > .card > .fakeImg {
  background-color: rgb(90, 90, 90);
  width: 100%;
  height: 140px;
  display:flex;
  align-items: center;
  justify-content: center;
}

.projectPreview > .card > .fakeImg > .title  {
  font-size: 1.3rem;
  color: white;
  text-align: center;
}

.projectPreview > .card > .fakeImg > .curAmount {
  margin: 7px;
  padding: 7px 20px;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  position: absolute;
  top: 0;
  right: 0;
}

.projectPreview > .card {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  margin: 0 0 25px 0;
  border-radius: initial;
}

.projectPreview > .card > p {
  flex-grow: 1;
  margin: 7px;
}

.projectPreview > .card > a {
  margin: 7px;
}


.categoriesList {
  margin: 0 0 25px 0;
}

.projectInfo {
  width: 100%;
  height: 280px;
  background-color: rgb(90, 90, 90);
}

.projectInfo > .container {
  height: 100%; 
  width:100%;
  display:flex;
  align-items: center;
  justify-content: center;
}

.projectInfo > .container > .projectName {

}

.btnHelpSubmit {
  margin: 10px 0;
}

.curAmountProjectPage {
  width: 100%;
  padding: 15px 0;
  margin: 0 0 30px 0;
}
    </style>

  </head>
<body>

  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Crowdfunder</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="${projetDir}/">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${projetDir}/project/by-category">Categories</a>
        </li>

        <c:if test="${isUserLogged == false}">
          <li class="nav-item">
            <a class="nav-link" href="${projetDir}/login">Sign in</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${projetDir}/register">Sign up</a>
          </li>
        </c:if>

        <c:if test="${isUserLogged == true}">
          <li class="nav-item">
            <a class="nav-link" href="${projetDir}/project/create">Create a new project</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${projetDir}/user/profile/${userLogged.id}">My profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${projetDir}/user/profile-edit">Edit profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${projetDir}/logout">Sign out</a>
          </li>
        </c:if>

      </ul>
    </div>
  </nav>
  
  <jsp:doBody/>
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
  <script src="https://getbootstrap.com/dist/js/bootstrap.min.js"></script>

</body>
</html>