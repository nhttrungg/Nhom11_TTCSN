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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
                    <li><a href="">Thông tin</a></li>
                    <li><a href="http://localhost:8080/user?action=myorder">Xem đơn hàng</a></li>
                    <li><a href="http://localhost:8080/login?action=login">Đăng xuất</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="sidebar">
    <a href="http://localhost:8080/user?action=home"><i class="left-menu-icon fas fa-home"></i></a>
    <a href="http://localhost:8080/user?action=cart"><i class="left-menu-icon fas fa-shopping-cart"></i></a>
    <a href="http://localhost:8080/user?action=books"><i class="left-menu-icon fa-solid fa-book"></i></a>
</div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2 bg-primary p-5" style="height: 250px !important;">
                <form action="http://localhost:8080/user?action=search" method="get">
                    <input value="search" name="action"style="display: none">
                    <div class="row flex-nowrap">
                        <input class="form-control w-75" name="search_text" placeholder="Search" aria-label="Tìm kiếm theo tên">
                    </div>
                    <div class="row">
                        <label>Phân loại</label>
                        <select name="category" class="form-select form-select-lg mb-3 " aria-label=".form-select-lg example" style="width: 100%; height: 38px; margin: 0 !important;">
                            <option value="" selected>Chọn loại sách</option>
                            <option value="mn">SÁCH MẦM NON</option>
                            <option value="tn">SÁCH THIẾU NHI</option>
                            <option value="kn">SÁCH KĨ NĂNG</option>
                            <option value="kd">SÁCH KINH DOANH</option>
                            <option value="mb">SÁCH MẸ VÀ BÉ</option>
                            <option value="vh">SÁCH VĂN HỌC</option>
                            <option value="tk">SÁCH THAM KHẢO</option>
                            <option value="nb">NOTEBOOK</option>
                        </select>
                    </div>
                    <div class="row mt-1">
                        <button class="btn btn-success" type="submit">Tìm kiếm</button>
                    </div>
                </form>
        </div>
        <div class="col-9 ml-5">
            <div class="row flex-nowrap">
                   <a class="btn btn-primary w-25" href="http://localhost:8080/user?action=sortDesc">Sắp xếp theo chiều tăng</a>
                   <a class="btn btn-primary w-25" href="http://localhost:8080/user?action=sortAsc">Sắp xếp theo chiều giảm</a>
            </div>
            <div class="row d-flex flex-wrap">
                <c:forEach var="item" items="${books}">
                    <form action="http://localhost:8080/user?action=addCart&bookId=${item.id}" method="post" style="width: 25%">
                        <div class="movie-list">
                            <div class="movie-list-item">
                                <a href="http://localhost:8080/user?action=detail&id=${item.id}">
                                    <img class="movie-list-item-img" src="./image1/${item.image}" alt="">
                                </a>
                                <span class="movie-list-item-title">${item.name}</span>
                                <p class="movie-list-item-desc">${item.author}</p>
                                <button class="movie-list-item-button" >Cho vào giỏ hàng</button>
                            </div>
                        </div>
                        <i class="fas fa-chevron-right arrow"></i>
                    </form>
                </c:forEach>
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