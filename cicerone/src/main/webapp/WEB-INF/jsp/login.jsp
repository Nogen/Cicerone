<!DOCTYPE html>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - login</title>
  <link rel="icon" href="image/icon.png">
  <!-- Bootstrap core CSS -->
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/scrolling-nav.css" rel="stylesheet">
  <link href="css/login.css" rel="stylesheet">

</head>

<body onload="focusMessage()">
  <div class="container-fluid">
    <div class="row h-100 no-gutters">
      <div class="col-lg-3 mt-lg-3 mb-lg-3 leftbar-bg">
      </div>
      <div class="col-lg-9 bg-white-vivident mt-lg-3 mb-3">


            <div class="row mr-lg-5 ml-lg-5 mt-5 justify-content-md-center no-gutters">
              <div class="col-lg-8">
                <div class="card-block mt-lg-5 shadow-lg">
                  <div class="text-center">
                    <img src="image/prova.png" class="card-img card-img-auto-size card-img-overflow-postion" alt="Cicerone is the way">
                  </div>
                  <div class="card-body pl-0 pr-0">
                    <div class="tab-content" id="myTabContent">
                      <form class="mr-lg-5 ml-lg-5 mt-lg-3 pb-lg-5 was-validated" name="login" action="login" method="post">
                        <div class="row mt-3">
                          <div class="col">
                            <div class="form-group">
                              <label>Email</label>
                              <input type="email" class="form-control" placeholder="Email" name="email" required>
                            </div>
                          </div>
                        </div>

                        <div class="row mt-3">
                          <div class="col">
                            <div class="form-group">
                              <label>Password</label>
                              <div class="input-group" id="password_hider">
                                <input type="password" id="password" name="password" class="form-control" placeholder="Password"  data-toggle="password" name="username" required>
                                <div class="input-group-append">
                                  <a href="" class="btn btn-light"><i class="fa fa-eye" aria-hidden="true"></i></a>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row mt-3">
                          <div class="col">
                            <button type="submit" class="btn btn-danger">Login</button>
                            <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)} id="messageBox">
                              <strong>${e:forHtml(message)} <a class="alert-link" href="${e:forUriComponent(link)}">${e:forHtml(linkmessage)}</a></strong>
                              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                              </button>
                            </div>
                          </div>
                        </div>
                        <div class="row mt-3">
                          <div class="col text-center">
                            <a href="/signup" class="underlines">Oppure Iscriviti</a>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>


      </div>
    </div>
  </div>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="js/scrolling-nav.js"></script>
<script src="js/validationscript.js"></script>
<script src="js/passwordHider.js"></script>
<script src="js/focusMessage.js"></script>
</body>

</html>
