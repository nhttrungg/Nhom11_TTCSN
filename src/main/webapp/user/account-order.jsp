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
    <link rel="stylesheet" href="<c:url value="/user/style.css"/>">
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
        @import url('https://fonts.googleapis.com/css2?family=Manrope:wght@200&display=swap');

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            padding: 20px;
            width: 450px;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border-radius: 6px;
            -moz-box-shadow: 0px 0px 5px 0px rgba(212, 182, 212, 1)
        }

        .comment-box{

            padding:5px;
        }



        .comment-area textarea{
            resize: none;
            border: 1px solid #ad9f9f;
        }


        .form-control:focus {
            color: #495057;
            background-color: #fff;
            border-color: #ffffff;
            outline: 0;
            box-shadow: 0 0 0 1px rgb(255, 0, 0) !important;
        }

        .send {
            color: #fff;
            background-color: #ff0000;
            border-color: #ff0000;
        }

        .send:hover {
            color: #fff;
            background-color: #f50202;
            border-color: #f50202;
        }


        .rating {
            display: flex;
            margin-top: -10px;
            flex-direction: row-reverse;
            margin-left: -4px;
            float: left;
        }

        .rating>input {
            display: none
        }

        .rating>label {
            position: relative;
            width: 19px;
            font-size: 25px;
            color: #ff0000;
            cursor: pointer;
        }

        .rating>label::before {
            content: "\2605";
            position: absolute;
            opacity: 0
        }

        .rating>label:hover:before,
        .rating>label:hover~label:before {
            opacity: 1 !important
        }

        .rating>input:checked~label:before {
            opacity: 1
        }

        .rating:hover>input:checked~label:before {
            opacity: 0.4
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
<form method="post" action="http://localhost:8080/user?action=postComment&id=${id}" class="position-fixed d-none " style="z-index: 3; width: 200px; height: 200px; left: 500px; top: 100px; ${display}">
    <div class="card">
        <div class="row">
            <div class="col-2">
                <h5>Tên tài khoản: ${name}</h5>
            </div>
            <div class="col-10">
                <div class="comment-box ml-2">
                    <h4>Bình luận</h4>
                    <div class="comment-area">
                        <textarea class="form-control" placeholder="Đánh giá của bạn?" rows="4" name="comment"></textarea>
                    </div>
                    <div class="comment-btns mt-2">
                        <div class="row">
                            <div class="col-6">
                                <div class="pull-left">
                                    <a class="btn btn-danger btn-sm">Hủy bỏ</a>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="pull-right">
                                    <button class="btn btn-success send btn-sm" type="submit">Bình luận<i class="fa fa-long-arrow-right ml-1"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="container gradient-custom">
    <div class="container mb-4 main-container gradient-custom">
        <div class="row">
            <div class="col-lg-4 pb-5">
                <!-- Account Sidebar-->
                <div class="author-card pb-3">
                    <div class="author-card-profile">
                        <div class="author-card-details">
                            <h5 class="author-card-name text-lg">Xin chào ${name}</h5>
                        </div>
                    </div>
                </div>
                <div class="wizard">
                    <nav class="list-group list-group-flush">
                        <a class="list-group-item active" href="#">
                            <div class="d-flex justify-content-between align-items-center">
                                <div><i class="fa fa-shopping-bag mr-1 text-muted"></i>
                                    <div class="d-inline-block font-weight-medium text-uppercase">Danh sách đơn hàng</div>
                                </div><span class="badge badge-secondary">${number}</span>
                            </div>
                        </a><a class="list-group-item" href="" target="__blank"><i class="fa fa-user text-muted"></i>Thông tin cá nhân</a>
                    </nav>
                </div>
            </div>
            <div class="col-lg-8 pb-5">
                <div class="d-flex justify-content-end pb-3">
                </div>
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                        <tr>
                            <th>Mã đơn hàng #</th>
                            <th>Ngày mua</th>
                            <th>Địa chỉ</th>
                            <th>Tổng tiền</th>
                            <th>Đánh giá</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${orders}">
                            <tr>
                                <td><a class="navi-link" href="http://localhost:8080/user?action=orderdetail&id=${item.id}" data-toggle="modal">${item.id}</a></td>
                                <td>${item.date}</td>
                                <td><span class="badge badge-danger m-0">${item.address}</span></td>
                                <td><span>${item.price}</span></td>
                                <td>
                                    <a class="btn btn-primary" href="http://localhost:8080/user?action=doComment&id=${item.id}">Đánh giá</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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