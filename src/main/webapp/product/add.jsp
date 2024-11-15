<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/26/2024
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="header bg-dark">
    <div class="container">
       <div class="row">
           <div class="col-9"></div>
          <div class="col-3">
              <a href="http://localhost:8080/Gradle___com_example___hauiProject_1_0_SNAPSHOT_war__exploded_/home?action=login" class="btn d-flex justify-content-center text-light btn-secondary" style="height: 40px">
                  <ion-icon name="person-circle-outline" style="width: 30px;height: 30px;"></ion-icon>
                  <p style="font-size: 20px" class="ml-1">${user_name}</p>
              </a>
          </div>
       </div>
       <div class="row">
          <div class="col-12">
              <ul class="nav nav-tabs text-center border-0 bg-primary ">
                  <li class="nav-item w-25">
                      <a class="nav-link text-dark active" href="#">Active</a>
                  </li>
                  <li class="nav-item w-25">
                      <a class="nav-link text-dark" href="#">Link</a>
                  </li>
                  <li class="nav-item w-25">
                      <a class="nav-link text-dark" href="#">Link</a>
                  </li>
                  <li class="nav-item w-25">
                      <a class="nav-link text-dark" href="#">Disabled</a>
                  </li>
              </ul>
          </div>
       </div>
    </div>
</div>
<div class="body">
    <div class="container">
        <section class="vh-100">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-12 col-xl-11">
                        <div class="card text-black" style="border-radius: 25px; background: cornsilk">
                            <div class="card-body p-md-5">
                                <div class="row justify-content-center">
                                    <div class="col-md-10 col-lg-10 col-xl-10 order-2 order-lg-1">
                                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Thêm sách mới</p>
                                        <form action="http://localhost:8080/Gradle___com_example___hauiProject_1_0_SNAPSHOT_war__exploded_/admin?action=addBook" method="post" enctype="multipart/form-data">
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0" style="width: 100%">
                                                    <input type="text" id="1" name="1" class="form-control" name="ac-name"/>
                                                    <label class="form-label" for="1">Tên sách</label>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0" style="width: 100%">
                                                    <input type="text" id="2" name="2" class="form-control" name="ac-name"/>
                                                    <label class="form-label" for="2">Tác giả</label>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0" style="width: 100%">
                                                    <select id="3" name="3" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" style="width: 100%; height: 38px; margin: 0 !important;">
                                                        <option selected>Loại sách</option>
                                                        <option value="mn">SÁCH MẦM NON</option>
                                                        <option value="tn">SÁCH THIẾU NHI</option>
                                                        <option value="kn">SÁCH KĨ NĂNG</option>
                                                        <option value="kd">SÁCH KINH DOANH</option>
                                                        <option value="mb">SÁCH MẸ VÀ BÉ</option>
                                                        <option value="vh">SÁCH VĂN HỌC</option>
                                                        <option value="tk">SÁCH THAM KHẢO</option>
                                                        <option value="nb">NOTEBOOK</option>
                                                    </select>
                                                    <label class="form-label" for="3">Loại sách</label>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0" style="width: 100%">
                                                    <input type="number" id="4" name="price" class="form-control" name="ac-name"/>
                                                    <label class="form-label" for="4">Giá tiền</label>
                                                </div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center mb-4">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0" style="width: 100%">
                                                    <input type="file" id="5" name="5" class="form-control" name="ac-name"/>
                                                    <label class="form-label" for="5">Thêm ảnh</label>
                                                </div>
                                                <div class="form-outline flex-fill mb-0" style="min-height: 100px;">
                                                    <p>${adding_image}</p>
                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                <button type="submit" class="btn btn-primary btn-lg w-50">Thêm sản phẩm</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>
