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
    <link rel="stylesheet" href="<c:url value="style.css"/>">
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
                    <li><a href="">Đăng nhập</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="sidebar">
    <i class="left-menu-icon fas fa-home"><a href="http://localhost:8080/login?action=showHome"></a></i>
    <i class="left-menu-icon fas fa-shopping-cart"></i>
</div>
<div class="row justify-content-center " style="margin-left: 100px">
    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

        <form class="mx-1 mx-md-4" method="post" action="http://localhost:8080/user?action=register">

            <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                    <input type="text" id="form3Example1c" class="form-control" name="ac-name"/>
                    <label class="form-label" for="form3Example1c">Họ tên</label>
                </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                    <input type="email" id="form3Example3c" class="form-control" name="ac-phonenumber"/>
                    <label class="form-label" for="form3Example3c">Số điện thoại</label>
                </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                    <input type="email" id="form3Example4c" class="form-control" name="ac-username"/>
                    <label class="form-label" for="form3Example4c">Tài khoản</label>
                </div>
            </div>

            <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                    <input type="password" id="form3Example5c" class="form-control" name="ac-pass"/>
                    <label class="form-label" for="form3Example5c">Mật khẩu</label>
                </div>
            </div>
            <div class="d-flex flex-row align-items-center mb-4">
                <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                <div class="form-outline flex-fill mb-0">
                    <input type="password" id="form3Example4cd" class="form-control" name="ac-repass"/>
                    <label class="form-label" for="form3Example4cd">Nhập lại mật khẩu</label>
                </div>
            </div>
            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                <button type="button" class="btn btn-primary btn-lg">Đăng kí</button>
            </div>
        </form>
    </div>
    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
        <img src="z5338521373577_a3ae573d4e53a6a3e3e9f9914e608a4a.jpg"
             class="img-fluid h-75" alt="Sample image">
    </div>
</div>


</div>
<script src="../app.js"></script>
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

