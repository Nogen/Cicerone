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

  <title>Cicerone- gpanel</title>
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
          <img alt="Image here" src="image/${ruolo}.png" class="avatarmain circle-form-border" alt="Cicerone is the way">
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
        <div class="row filter active">
          <div class="col mt-2 mb-2">
            <a href="loggedHome"><img alt="Image here" src="icons/home.png" class="dimIcons mr-3">Home</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="trovaAttivita"><img alt="Image here" src="icons/operations.png" class="dimIcons mr-3">Trova Attivit&#224;</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="gestioneRichieste"><img alt="Image here" src="icons/notification.png" class="dimIcons mr-3">Richieste</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="modificaProfilo"><img alt="Image here" src="icons/profile.png" class="dimIcons mr-3">Modifica profilo</a>
          </div>
        </div>
        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="chooseCicerone"><img alt="Image here" src="icons/switchglobetrotter.png" class="dimIcons mr-3"> a Cicerone</a>
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
          <div class"container-fluid" >
            <div class="row">
              <div class="col-12 col-lg-5 mt-3">
                <div class="card shadow mt-5 h-100">
                  <div class="card-header">
                    <strong><h5>Sezione Trova Attivit&agrave; </h5></strong>
                  </div>
                  <div class="card-body pb-0">
                      <div class="row text-center">
                        Una volta pianificato il tuo prossimo viaggio, potrai ricercare le attività disponibili in funzione della meta e della data di soggiorno mediante la sezione "Trova Attivit&agrave;".
                      </div>
                  </div>
                  <div class="card-footer"><a href="trovaAttivita"><button  class="btn btn-primary form-control">TROVA ATTIVIT&Agrave;</button></a></div>
                </div>
              </div>
              <div class="col-12 col-lg-7 mt-3">
                <div class="card shadow mt-5 h-100">
                  <div class="card-header">
                    <strong><h5>Sezione Richieste</h5></strong>
                  </div>
                  <div class="card-body pb-0">
                      <div class="row text-center">
                       	Potrai, inoltre, inviare una richiesta d'iscrizione all'attivit&agrave; che desideri mediante un semplice clic sul pulsante "Iscriviti" dell'attivit&agrave; desiderata e, 
                       	nel caso di risposta affermativa, potrai visualizzare i contatti del Cicerone che ha creato l'attivit&agrave; alla quale desideri partecipare nella sezione "Richieste". 
                       	Potrai, ancora in "Richieste", ordinarle a tuo piacimento.
                      </div>
                  </div>
                  <div class="card-footer text-center"><a href="gestioneRichieste"><button  class="btn btn-primary form-control">RICHIESTE</button></a></div>
                </div>
              </div>
              <div class="col-12 col-lg-4 mt-3">
                <div class="card shadow mt-5 h-100">
                  <div class="card-header">
                    <strong><h5>Sezione Modifica Profilo</h5></strong>
                  </div>
                  <div class="card-body pb-0">
                      <div class="row text-center">
                        Qui puoi modificare e arricchire il profilo del tuo account. 
                      </div>
                  </div>
                  <div class="card-footer"><a href="modificaProfilo"><button  class="btn btn-primary form-control">MODIFICA PROFILO</button></a></div>
                </div>
              </div>
              <div class="col-12 col-lg-4 mt-3">
                <div class="card shadow mt-5 h-100">
                  <div class="card-header">
                    <strong><h5>Sezione Passa a Cicerone</h5></strong>
                  </div>
                  <div class="card-body pb-0">
                      <div class="row text-center">
                        Stanco di essere Globetrotter? Passa a Cicerone per creare nuovi itinerari!
                      </div>
                  </div>
                  <div class="card-footer"><a href="chooseCicerone"><button  class="btn btn-primary form-control">VAI A CICERONE</button></a></div>
                </div>
              </div>
              
                 <div class="col-12 col-lg-4 mt-3">
              <div class="card shadow mt-5 h-100">
                <div class="card-header">
                  <strong><h5>Sezione Logout</h5></strong>
                </div>
                <div class="card-body pb-0">
                    <div class="row text-center">
                      Prenditi una pausa dalle attivit&agrave; ed effettua il logout. 
                    </div>
                </div>
                <div class="card-footer"><a href="logout"><button  class="btn btn-primary form-control">ESCI</button></a></div>
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
  <script src="js/cpanel.js"></script>

</body>


</html>
