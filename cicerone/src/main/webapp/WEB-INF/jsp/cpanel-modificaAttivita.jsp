<!DOCTYPE html>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - cpanel</title>
  <link rel="icon" href="image/icon.png">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/cpanel.css" rel="stylesheet">
  <link href="css/sidnav.css" rel="stylesheet">
  <script type="text/javascript" src="https://www.bing.com/api/maps/mapcontrol?key=AmEx2BMNQU-8ypVbNrAOhucG--62omkRJBeaXbko6DksjOofTVcva-iadW0um1vk"></script>

</head>

<body onload="loadMap();">
  <nav class="navbar sticky-top navbar-expand-lg navbar-dark navbar-style">
    <a class="navbar-brand" href="#"><img alt="Image here" src="image/logo.png" class="d-inline-block align-top"> Cicerone</a>
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
        <div class="row no-gutters separator">
          <div class="col-12 col-lg-12 mt-3 mb-3 text-center">
            <h1>Modifica attivit&agrave;</h1>
          </div>
        </div>
        <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)}>
            <strong>${e:forHtml(message)} <a class="alert-link" href="${link}">${e:forHtml(linkmessage)}</a></strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
        <form name="modificaAttivita" action="modificaAttivita" method="post">
          <div class="row no-gutters separator">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">
              <div class="form-group row">
                <label for="Titolo"><strong>Titolo Attivit&agrave;:</strong></label>
                <input type="text" class="form-control-plaintext" value="${e:forHtmlAttribute(titoloAttivita)}" readonly>
              </div>
              <div class="form-group row ancora">
                <label for="Lingua"><strong>Lingua:</strong></label>
                <label class="form-control-plaintext" >${e:forHtml(lingue)}</label>
              </div>
              <div class="form-group row">
                <label for="Retribuzione-chooser"><strong>Retribuzione:</strong></label>
                <label class="form-control-plaintext">${e:forHtml(retribuzione)}</label>
                <label class="form-control-plaintext">${e:forHtml(valoreRetribuzione)}</label>€
                </div>
              </div>

            </div>
          </div>
          <div class="row no-gutters">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">
              <div class="card shadow h-100 mapcard mx-auto">
                <div id="myMap" class="mapstyle"></div>

                <div class="form-group row mt-1">
                  <label for="searchBox" class="col-lg-3 col-4 col-form-label"><strong>Luogo:</strong></label>
                  <div id="searchBoxContainer" class="col-lg-9 col-8">
                    <input type="text" class="form-control" placeholder="Cerca qui.." id= "searchBox" name="luogo" value="${e:forHtmlAttribute(luogo)}"/>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="searchBox" class="col-lg-6 col-6 col-form-label"><strong>Latitudine:</strong></label>
                  <div class="col-6">
                    <input type="search" readonly class="form-control-plaintext" id="lat" name="lat" value="${e:forHtmlAttribute(xIncontro)}"/>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="searchBox" class="col-lg-6 col-6 col-form-label"><strong>Longitudine:</strong></label>
                  <div class="col-6">
                    <input type="search" readonly class="form-control-plaintext" id="lon" name="lon" value="${e:forHtmlAttribute(yIncontro)}"/>
                  </div>
                </div>

              </div>
            </div>
          </div>

          <div class="row no-gutters">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">

              <label for="datepick"><strong>Data:</strong></label>
              <label class="form-control-plaintext">${e:forHtmlAttribute(dataIncontro)}</label>
              <label for="hours" class="mt-1"><strong>Orario:</strong></label>
              <div class="row no-gutters">
                <div class="col-12 col-lg-6">
                  <input  data-suffix="h" type="number" placeholder="Inserisci l'ora..." min="0" max="23" id="hours" name="ore">
                </div>
                <div class="col-12 col-lg-6">
                  <input  data-suffix="min" type="number" placeholder="Inserisci i minuti..." min="0" max="59" id="minutes" name="minuti">
                </div>
              </div>

            </div>
          </div>
          <div class="row no-gutters">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">
              <div class="form-group row mb-5">

                  <label for="Descrizione"><strong>Descrizione attivit&agrave;</strong></label>
                  <textarea class="form-control" id="Descrizione" maxlength="752" rows="5" name="descrizioneAttivita">${e:forHtml(descrizioneAttivita)}</textarea>



              </div>
              <input type="text" readonly hidden value="${idAttivita}" name="id">
              <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#exampleModal">
                Modifica attivit&agrave;
              </button>
            </div>
          </div>
          <div class="modal fade mt-5" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Conferma modifica attivit&agrave;</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  Sei sicuro di volere modificare questa attivit&agrave;?
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                  <input type="submit" class="btn btn-primary" value="Conferma">
                </div>
              </div>
            </div>
          </div>
        </form>
</div>


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap-input-spinner.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Bing map sdk -->

<script src="js/Maploader.js"></script>

<!-- Custom JavaScript for this theme -->

<script src="js/cpanel.js"></script>
<script src="js/grid-list-view.js"></script>
<script src="js/bingplaceApi.js"></script>
<script src="js/modificaAttivita.js"></script>


</body>

</html>
