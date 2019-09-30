<!DOCTYPE html>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - cpanel - modifica profilo</title>
  <link rel="icon" href="image/icon.png">
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.min.css" rel="stylesheet">
  <link href="vendor/jquery-ui/jquery-ui.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
  <link href="css/cpanel.css" rel="stylesheet">
  <link href="css/sidnav.css" rel="stylesheet">
  <script type='text/javascript' src='http://www.bing.com/api/maps/mapcontrol?callback=GetMap&key=AmEx2BMNQU-8ypVbNrAOhucG--62omkRJBeaXbko6DksjOofTVcva-iadW0um1vk' async defer></script>
</head>

<body>
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

        <div class="row filter">
          <div class="col mt-2 mb-2">
            <a href="/logout"><img alt="Image here" src="icons/logout.png" class="dimIcons mr-3">Esci</a>
          </div>
        </div>
      </div>
    </div>
    <div class="fuckmargin" id="main">
      <div class="container-fluid" >
        <div class="row no-gutters">
        <div class="row">
          <div class="col-12">
            <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)} id="messageBox">
              <strong>${e:forHtml(message)} <a class="alert-link" href="${link}">${e:forHtml(linkmessage)}</a></strong>
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
          </div>
        </div>
          <div class="col-12 col-lg-12 mt-3 mb-3 text-center">
            <h2>Modifica il tuo profilo:</h2>
          </div>
        </div>
        <form action="modificaProfilo" method="post" class="" ${e:forHtmlAttribute(showOnLogin)}>
          <div class="row separator">
            <div class="col-lg-3">
              <div class="card shadow">
                <div class="card-header">
                  Ultimo ruolo utilizzato: ${e:forHtml(ruolo)}
                </div>
                <img src="image/${e:forUriComponent(ruolo)}.png" class="card-img" alt="...">
              </div>
            </div>
            <div class="col-lg-9 mt-3 mb-3 text-left">
              <div class="row">
                <label>Nome:</label> <input type="text" value="${e:forHtmlAttribute(nome)}" name="nome" class="form-control">
              </div>
              <div class="row">
                <label>Cognome:</label> <input type="text" value="${e:forHtmlAttribute(cognome)}" name="cognome" class="form-control">
              </div>
              <div class="row">
                <div id="colonna" class="fullallrow">
                  <label>Residenza:</label>
                  <input type="text" value="${e:forHtmlAttribute(residenza)}" name="residenza" id="placeauto" class="form-control">
                </div>
              </div>
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
                  <label><strong>Nuova password:</strong></label>
                </div>
                <div clas="col-lg-6">
                  <div class="input-group" id="password_hider">
                    <input type="password" id="mainpsw" class="form-control" placeholder="Password" name="password">
                    <div class="input-group-append">
                      <a href="" class="btn btn-light"><i class="fa fa-eye" aria-hidden="true"></i></a>
                    </div>
                  </div>
                </div>
                </div>
              <div class="row mt-3 mb-3 ml-2 mr-2 justify-content-between">
                <div clas="col-lg-6">
                  <label><strong>Numero di telefono:</strong></label>
                </div>
                <div clas="col-lg-6">
                  <input type="text" name="ntelefono" value="${e:forHtmlAttribute(ntelefono)}" class="form-control">
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
                <div clas="col-lg-6">
                  <label><strong>Data di nascita:</strong></label>
                </div>
                <div clas="col-lg-6">
                  <input type="date" value="${e:forHtmlAttribute(bday)}" name="bday" class="form-control">
                </div>
              </div>

              <div>Abilit&agrave; </div>

              <div class="row mt-5 mb-5 ml-2 mr-2 justify-content-between">
                <div clas="col-lg-6">
                  <label><strong>Lingue parlate:</strong></label>
                </div>
                <div clas="col-lg-6">
                  <div class="row">
                    <textarea readonly name="lingue" class="form-control" id="Lingue-Aggiunte" hidden>${lingue}</textarea>
                  </div>
                  <div class="row">
                    <div class="col-12" id="lingueContainer">
                    </div>
                  </div>
                  <div class="row ancora mb-1">
                    <div class="input-group">
                      <input type="text" id="languagesauto" class="form-control">
                      <div class="input-group-append">
                        <button type="button" class="btn btn-outline-success" id="aggiungi">Aggiungi</button>
                      </div>
                    </div>
                  </div>

                </div>
              </div>

            </div>
          </div>
          <div class="row mt-3 mb-3">
            <div class="col-12">
              <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#exampleModal">
              Salva modifica
            </button>
              <a href="/profilo?id=${e:forUriComponent(id)}" class="btn btn-secondary">Visualizza profilo</a>
            </div>
          </div>
          <div class="modal fade mt-5" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Conferma modifica profilo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                Sei sicuro di volere modificare il tuo profilo?
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
    </div>


    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/jquery-ui/jquery-ui.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>



    <!-- Custom JavaScript for this theme -->

    <script src="js/cpanel.js"></script>
    <script src="js/grid-list-view.js"></script>
    <script src="js/lenguageAutoComplete.js"></script>
    <script src="js/autosuggestBing.js"></script>
    <script src="js/lenguageModifier.js"></script>
    <script src="js/validationscript.js"></script>
    <script src="js/passwordHider.js"></script>


  </body>

  </html>
