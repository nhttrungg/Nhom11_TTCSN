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


    </style>
</head>

<body>
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
                    <li><a href="http://localhost:8080/login?action=login">Đăng nhập</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="sidebar">
    <a href="http://localhost:8080/login?action=home"><i class="left-menu-icon fas fa-home"></i></a>
    <a href="http://localhost:8080/login?action=cart"><i class="left-menu-icon fas fa-shopping-cart"></i></a>

    <a href="http://localhost:8080/login?action=books"><i class="left-menu-icon fa-solid fa-book"></i></a>
</div>
<div class="container">
    <div class="content-container">
        <div class="featured-content"
             style="background: linear-gradient(to bottom, rgba(0,0,0,0), #151515), url('https://top1danhgia.com/uploads/2022/nha-sach-nha-nam-1.jpg');">
            <p class="featured-desc" style="font-size: 50px;">NHÀ SÁCH DƯƠNG TÂM - Nhà sách uy tín số một Bắc Giang</p>
        </div>
        <div class="movie-list-container">
            <h1 class="movie-list-title">Sách thiếu nhi</h1>
            <div class="movie-list-wrapper">
                <div class="movie-list">
                    <c:forEach var="item" items="${books1}">
                        <form>
                            <div class="movie-list">
                                <div class="movie-list-item">
                                    <a href="http://localhost:8080/user?action=detail&id=${item.id}">
                                        <img class="movie-list-item-img" src="./image1/${item.image}" alt="">
                                    </a>
                                    <span class="movie-list-item-title">${item.name}</span>
                                    <p class="movie-list-item-desc">${item.author} </p>
                                    <button class="movie-list-item-button" >
                                        <a href="">Mua ngay</a>
                                    </button>
                                    <button class="movie-list-item-button" >
                                        <a href="">Cho vào giỏ hàng</a>
                                    </button>
                                </div>
                            </div>
                            <i class="fas fa-chevron-right arrow"></i>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="movie-list-container">
            <h1 class="movie-list-title">Sách kinh doanh</h1>
            <div class="movie-list-wrapper">
                <div class="movie-list">
                    <c:forEach var="item" items="${books2}">
                        <form>
                            <div class="movie-list">
                                <div class="movie-list-item">
                                    <a href="http://localhost:8080/user?action=detail&id=${item.id}">
                                        <img class="movie-list-item-img" src="./image1/${item.image}" alt="">
                                    </a>
                                    <span class="movie-list-item-title">${item.name}</span>
                                    <p class="movie-list-item-desc">${item.category}</p>
                                    <button class="movie-list-item-button">
                                        <a href="">Mua ngay</a>
                                    </button>
                                    <button class="movie-list-item-button">
                                        <a href="">Cho vào giỏ hàng</a>
                                    </button>
                                </div>
                            </div>
                            <i class="fas fa-chevron-right arrow"></i>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="movie-list-container">
            <h1 class="movie-list-title">Sách tham khảo</h1>
            <div class="movie-list-wrapper">
                <div class="movie-list">
                    <c:forEach var="item" items="${books3}">
                        <form>
                            <div class="movie-list">
                                <div class="movie-list-item">
                                    <a href="http://localhost:8080/user?action=detail&id=${item.id}">
                                        <img class="movie-list-item-img" src="./image1/${item.image}" alt="">
                                    </a>
                                    <span class="movie-list-item-title">${item.name}</span>
                                    <p class="movie-list-item-desc">${item.category}</p>
                                    <button class="movie-list-item-button">
                                        <a href="">Mua ngay</a>
                                    </button>
                                    <button class="movie-list-item-button">
                                        <a href="">Cho vào giỏ hàng</a>
                                    </button>
                                </div>
                            </div>
                            <i class="fas fa-chevron-right arrow"></i>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
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