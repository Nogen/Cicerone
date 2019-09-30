<!DOCTYPE html>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - cpanel - visualizza profilo</title>
  <link rel="icon" href="image/icon.png">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/cpanel.css" rel="stylesheet">

  <script type="text/javascript" src="https://www.bing.com/api/maps/mapcontrol?key=AmEx2BMNQU-8ypVbNrAOhucG--62omkRJBeaXbko6DksjOofTVcva-iadW0um1vk"></script>

</head>

<body onload="focusMessage()">
  <nav class="navbar sticky-top navbar-expand-lg navbar-dark navbar-style">
    <a class="navbar-brand" href="#"><img alt="Image here" src="image/logo.png" class="d-inline-block align-top">Cicerone</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse ml-auto" id="navbarSupportedContent">
      <div class="navbar-nav ml-auto">
        <a class="nav-item nav-link" href="loggedHome" class="btn btn-outline-light">
          Ritorna alla home
        </a>
      </div>
    </div>
  </nav>

    <div class="fuckmargin">
      <div class="container-fluid" >
        <div class="row no-gutters">
          <div class="col-12 col-lg-12 mt-3 mb-3 text-center">
            <h2>Profilo:</h2>
          </div>
        </div>
        <div class="row separator" ${e:forHtmlAttribute(showOnLogin)}>
          <div class="col-lg-3">
            <div class="card shadow">
              <div class="card-header">
                Ultimo ruolo utilizzato: ${e:forHtml(ruolo)}
              </div>
              <img src="image/${e:forUriComponent(ruolo)}.png" class="card-img" alt="...">
            </div>
          </div>
          <div class="col-lg-9 mt-3 mb-3 text-left">

            <h4>${e:forHtml(nome)} ${e:forHtml(cognome)}</h4>
            <h6 class="text-muted"> ${e:forHtml(residenza)} </h6>
            <div class="row separator mt-5 mb-5">
            </div>

            <div>Informazioni di base </div>

            <div class="row mt-5 mb-3 ml-2 mr-2 justify-content-between">
              <div clas="col-lg-6">
                <label><strong>Email:</strong></label>
              </div>
              <div clas="col-lg-6">
                <label>${e:forHtml(email)}</label>
              </div>
            </div>
            <div class="row mt-3 mb-3 ml-2 mr-2 justify-content-between">
              <div clas="col-lg-6">
                <label><strong>Genere:</strong></label>
              </div>
              <div clas="col-lg-6">
                <label>${e:forHtml(genere)}</label>
              </div>
            </div>
            <div class="row mt-3 mb-5 ml-2 mr-2 justify-content-between">
              <div class="col-lg-6">
                <label><strong>Data di nascita:</strong></label>
              </div>
              <div clas="col-lg-6">
                <label>${e:forHtml(bday)}</label>
              </div>
            </div>

            <div>Abilit&agrave; </div>

            <div class="row mt-5 mb-5 ml-2 mr-2 justify-content-between">
              <div clas="col-lg-6">
                <label><strong>Lingue parlate:</strong></label>
              </div>
              <div clas="col-lg-6">
                <label>${e:forHtml(lingue)}</label>
              </div>
            </div>

          </div>
        </div>
        <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)} id="messageBox">
          <strong>${e:forHtml(message)} <a class="alert-link" href="${e:forUriComponent(link)}">${e:forHtml(linkmessage)}</a></strong>
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
      </div>
    </div>


    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap-input-spinner.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Bing map sdk -->

    <script src="js/bingMapApi.js"></script>

    <!-- Custom JavaScript for this theme -->

    <script src="js/grid-list-view.js"></script>
    <script src="js/focusMessage.js"></script>


  </body>

  </html>
