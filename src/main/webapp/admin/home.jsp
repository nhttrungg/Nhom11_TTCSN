<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/7/2024
  Time: 11:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<c:url value="/style.css"/>">
  <link
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&family=Sen:wght@400;700;800&display=swap"
          rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
  <title>Movie Design</title>
  <style type="text/css">
    .profile-options {
      display: none;
      position: absolute;
      top: 35px;
      right: 0;
      background-color: #fff;
      box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
      z-index: 1;
    }

    .profile-options ul {
      list-style-type: none;
      padding: 0;
    }

    .profile-options ul li {
      padding: 10px;
    }

    .profile-options ul li a {
      text-decoration: none;
      color: #333;
      display: block;
    }

    .profile-options ul li a:hover {
      background-color: #f2f2f2;
    }

    .fas  {
      cursor: pointer;
    }
    .logo{
      cursor:pointer;
    }
    .profile-text{
      cursor: pointer;
    }

    .gradient-custom {
      /* fallback for old browsers */
      background: #6a11cb;

      /* Chrome 10-25, Safari 5.1-6 */
      background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));

      /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
      background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1))
    }
    .function a{
       color: white;
       border: 1px solid white;
    }
    .function a:hover{
        background: blueviolet;
        color: burlywood;
    }
    .card-book {
      height: 20%;
      width: 20%;
      min-height: 20%;
      max-width: 20%;
      min-width: 20%;
      margin: 10px;
    }
  </style>
</head>

<body class="gradient-custom">
<div class="navbar">
  <div class="navbar-container">
    <div class="logo-container">
      <h1 class="logo">Sách Dương Tâm</h1>
    </div>
    <div class="profile-container">
      <div class="profile-text-container" onclick="toggleProfileOptions()">
        <span class="profile-text" cursor="pointer">Tài khoản</span>
        <i class="fas fa-caret-down"></i>
      </div>
      <div class="profile-options" id="profileOptions">
        <ul>
          <li><a href="">Thông tin</a></li>
          <li><a href="http://localhost:8080/login?action=login">Đăng xuất</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="sidebar">
  <a href="http://localhost:8080/admin?action=add"> <i class="left-menu-icon fas fa-solid fa-book"></i></a>
  <i class="left-menu-icon fas fa-solid fa-truck"></i>
</div>
<div class="container gradient-custom mt-5 d-flex flex-wrap">
    <c:forEach var="item" items="${books}">
      <div class="card card-book">
        <img src="./image1/${item.image}" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">${item.name}</h5>
          <p class="card-text">${item.category}<i class="fa-solid fa-star"></i></p>
          <div class="row">
            <div class="col-6"></div>
            <div class="col-3">
                <button class="btn btn-primary text-white"><a href="http://localhost:8080/admin?action=edit&id=${item.id}">Sửa</a></button>
            </div>
            <div class="col-3">
              <form action="http://localhost:8080/admin?action=delete&id=${item.id}" method="post">
                <button class="btn btn-danger">Xóa</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
<script src="app.js"></script>
<script type="text/javascript">
  function toggleProfileOptions() {
    var profileOptions = document.getElementById("profileOptions");
    if (profileOptions.style.display === "none" || profileOptions.style.display === "") {
      profileOptions.style.display = "block";
    } else {
      profileOptions.style.display = "none";
    }
  }
</script>
</body>
</html>
