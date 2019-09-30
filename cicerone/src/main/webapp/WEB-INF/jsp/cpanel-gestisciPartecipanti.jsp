<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone- cpanel</title>
  <link rel="icon" href="image/icon.png">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/cpanel.css" rel="stylesheet">
  <link href="css/sidnav.css" rel="stylesheet">

</head>

<body>
  <nav class="navbar sticky-top navbar-expand-lg navbar-dark navbar-style">
    <a class="navbar-brand" href="#"><img alt="Image here" src="image/logo.png" class="d-inline-block align-top">Cicerone</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse ml-auto" id="navbarSupportedContent">
      <div class="navbar-nav ml-auto">
        <a class="nav-item nav-link " href="loggedHome">
          <img alt="Image here" src="icons/home.png" class="dimIcons">
          <div class="collapse sameline" id="navbarSupportedContent">
            Home
          </div>
        </a>
        <a class="nav-item nav-link" href="modificaProfilo">
          <img alt="Image here" src="icons/profile.png" class="dimIcons">
          <div class="collapse sameline" id="navbarSupportedContent">
            Modifica profilo
          </div>
        </a>
        <a class="nav-item nav-link" href="logout">
          <img alt="Image here" src="icons/logout.png" class="dimIcons">
          <div class="collapse sameline" id="navbarSupportedContent">
            Log-out
          </div>
        </a>
      </div>
    </div>
  </nav>
  <a href="javascript:void(0)" class="leftnavbutton" onclick="opencloseNav()" id="sideOpenBtn"><img alt="Image here" src="icons/navclose.png" id="navImage"></a>
  <div id="mySidenav" class="sidenav">
    <div class="container topheader mb-3 text-center">
      <div class="row">
        <div class="col-3 mt-2 mb-2 text-left">
          <img alt="Image here" src="image/${e:forUriComponent(ruolo)}.png" class="avatarmain circle-form-border" alt="Cicerone is the way">
        </div>
        <div class="col-9 mt-2 mb-2">
					<div class="col-12">
						<strong>
							Benvenuto
						</strong>
					</div>
					<div class="col-12">
						 ${e:forHtml(nome)} 
					</div>
					<div class="col-12">
						${e:forHtml(cognome)}
					</div>
				</div>
      </div>
      </div>
      <div class="container">
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="loggedHome"><img alt="Image here" src="icons/home.png" class="dimIcons mr-3">Home</a>
          </div>
        </div>
        <div class="row filter active">
          <div class="col mt-2 mb-2">
            <a href="attivitaCreate"><img alt="Image here" src="icons/operations.png" class="dimIcons mr-3">Attivit&#224; create</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="creaAttivita"><img alt="Image here" src="icons/notification.png" class="dimIcons mr-3">Nuova Attivit&#224;</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="modificaProfilo"><img alt="Image here" src="icons/profile.png" class="dimIcons mr-3">Modifica profilo</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="chooseGlobetrotter"><img alt="Image here" src="icons/switchglobetrotter.png" class="dimIcons mr-3"> a Globetrotter</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="logout"><img alt="Image here" src="icons/logout.png" class="dimIcons mr-3">Esci</a>
          </div>
        </div>
      </div>
    </div>
    <div class="fuckmargin" id="main">
      <div class="container-fluid" >
        <div class="row mt-5 separator align-items-center pb-1">
          <div class="col-12">
            <h1>Gestione partecipanti per l'attivit&agrave;: <a href="visualizzaAttivita?id=${e:forUriComponent(titolo)}"> ${e:forHtml(titolo)}</a></h1>
          </div>
        </div>
        <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)}>
          <strong>${e:forHtml(message)} <a class="alert-link" href="${link}">${e:forHtml(linkmessage)}</a></strong>
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="row mt-5 no-gutters">
          <div class="col-12 separator mt-3 mb-3">
            <strong>Partecipanti:</strong>
          </div>
          <c:forEach items="${listaPartecipanti}" var="partecipante">
          <div class="col-12 viewclass mb-2 mr-lg-0 ">
            <div class="card text-left shadow">
              <div class="row mt-2 mb-2">
                <div class="col-12 text-left">
                  <strong>Partecipante:</strong> <a class="underlines" href="profilo?id=${e:forUriComponent(partecipante.idAccount)}"> ${e:forHtml(partecipante.anagrafica.nome)} ${e:forHtml(partecipante.anagrafica.cognome)}</a>
                </div>
              </div>
              <div class="row mt-2 mb-2">
                <div class="col-6">
                  <strong>Email:</strong>
                </div>
                <div class="col-6">
                  <label class="form-control-plaintext">${e:forHtml(partecipante.email)}</label>
                </div>
              </div>
              <div class="row mt-2 mb-2">
                <div class="col-6">
                  <strong>Numero di telefono:</strong>
                </div>
                <div class="col-6">
                  <label class="form-control-plaintext">${e:forHtml(partecipante.anagrafica.numeroDiTelefono)}</label>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>

        <div class="col-12 separator mt-3 mb-3">
          <strong>Richieste d'iscrizione:</strong>
        </div>
        <c:forEach items="${listaIscrizioni}" var="richiesta">
        <c:if test="${richiesta.statoRichiesta == 'in sospeso'}">
        <div class="col-12 viewclass mb-2 mr-lg-0 ">
          <div class="card text-left shadow">
            <div class="row">
              <div class="col-12 text-left mt-3 mb-3">
                <strong>Partecipante:</strong> <a class="underlines" href="profilo?id=${e:forUriComponent(richiesta.mittente.idAccount)}"> ${e:forHtml(richiesta.mittente.anagrafica.nome)} ${e:forHtml(richiesta.mittente.anagrafica.cognome)}</a>
              </div>
            </div>
            <div class="card-footer">
              <div class="row ">
                <div class="col-12 col-lg-6 text-center">
                  <a href="accetta?id=${e:forUriComponent(richiesta.idRichiesta)}" role="button" class="btn btn-success text-light">Accetta</a>
                </div>
                <div class="col-12 col-lg-6 text-center">
                  <a href="rifiuta?id=${e:forUriComponent(richiesta.idRichiesta)}" class="btn btn-danger text-light"role="button">Rifiuta</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:if>
      </c:forEach>
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
  <script src="js/cpanel.js"></script>
  <script src="js/grid-list-view.js"></script>
  <script src="js/bingplaceApi.js"></script>

</body>


</html>
