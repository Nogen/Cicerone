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
        <a class="nav-item nav-link" href="loggedHome">
          <img alt="Image here" src="icons/home.png" class="dimIcons">
          <div class="collapse sameline" id="navbarSupportedContent">
            Ritorna alla home
          </div>
        </a>
      </div>
    </div>
  </nav>
      <div class="container-fluid" >
        <div class="row no-gutters separator">
          <div class="col-12 col-lg-12 mt-3 mb-3 text-center">
            <h1>Visualizza attivit&agrave;</h1>
          </div>
          <div class="col-12 col-lg-6 mt-3 mb-3 text-center">
            <strong>Data creazione:</strong>
          </div>
          <div class="col-12 col-lg-6 mt-3 mb-3 text-center">
            ${e:forHtml(dataApertura)}
          </div>
          <div class="col-12 col-lg-6 mt-3 mb-3 text-center">
            <strong>Ultima modifica:</strong>
          </div>
          <div class="col-12 col-lg-6 mt-3 mb-3 text-center">
            ${e:forHtml(dataModifica)}
          </div>
          <div class="col-12 col-lg-6 mt-3 mb-3 text-center">
            <strong>Creata da:</strong>
          </div>
          <div class="col-12 col-lg-6 mt-3 mb-3 text-center">
            <a href="profilo?id=${e:forUriComponent(idCreatore)}">${e:forHtml(nomeCreatore)} ${e:forHtml(cognomeCreatore)} </a>
          </div>
        </div>
        <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)}>
            <strong>${e:forHtml(message)} <a class="alert-link" href="${link}">${e:forHtml(linkmessage)}</a></strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="row no-gutters separator">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">
              <div class="form-group row">
                <label for="Titolo"><strong>Titolo Attivit&agrave;:</strong></label>
                <input type="text" class="form-control-plaintext" value="${e:forHtmlAttribute(titoloAttivita)}" readonly>
              </div>
              <div class="form-group row ancora">
                <label for="Lingua"><strong>Lingua:</strong></label>
                <input type="text" readonly class="form-control-plaintext" value="${e:forHtmlAttribute(lingue)}">
              </div>
              <div class="form-group row">
                <label for="Retribuzione-chooser"><strong>Retribuzione:</strong></label>
                <input class="form-control-plaintext" readonly value="${e:forHtmlAttribute(retribuzione)}">
                <input class="form-control-plaintext" readonly value="${e:forHtmlAttribute(valoreRetribuzione)}">
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
                    <input type="text" class="form-control-plaintext" value="${e:forHtmlAttribute(luogo)}" readonly id="luogo">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="searchBox" class="col-lg-6 col-6 col-form-label"><strong>Latitudine:</strong></label>
                  <div class="col-6">
                    <input type="search" readonly class="form-control-plaintext" id="lat" value="${e:forHtmlAttribute(xIncontro)}"/>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="searchBox" class="col-lg-6 col-6 col-form-label"><strong>Longitudine:</strong></label>
                  <div class="col-6">
                    <input type="search" readonly class="form-control-plaintext" id="lon"  value="${e:forHtmlAttribute(yIncontro)}"/>
                  </div>
                </div>

              </div>
            </div>
          </div>

          <div class="row no-gutters">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">

              <label for="datepick"><strong>Data:</strong></label>
              <input type="date" class="form-control-plaintext" readonly value="${e:forHtmlAttribute(dataIncontro)}">
              <label for="hours" class="mt-1"><strong>Orario:</strong></label>
              <div class="row no-gutters">
                <div class="col-12 col-lg-12">
                  <input class="form-control-plaintext" type="text"  readonly value="${e:forHtmlAttribute(oraIncontro)}">
                </div>
              </div>

            </div>
          </div>
          <div class="row no-gutters">
            <div class="col-12 col-lg-9 mt-3 mb-3 mx-auto">
              <div class="form-group row mb-5">

                  <label for="Descrizione"><strong>Descrizione attivit&agrave;</strong></label>
                  <textarea class="form-control" id="Descrizione" maxlength="752" rows="5" readonly>${e:forHtml(descrizioneAttivita)}</textarea>



              </div>
              <div class="row">
              <c:if test="${(not empty ruolo) && (ruolo == 'cicerone') && (statoAttivita != 'chiuso')}">
              <div class="col-12 col-lg-4 text-center mt-1">
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                  Cancella
                </button>
              </div>
              <div class="modal fade mt-5" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">Conferma cancellazione attivit&agrave;</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      Sei sicuro di volere cancellare questa attivit&agrave;?
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                      <a href="cancellaAttivita?id=${e:forUriComponent(idAttivita)}" class="btn btn-primary text-light">Conferma</a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-12 col-lg-4 text-center mt-1">
                <a href="modificaAttivita?id=${e:forUriComponent(idAttivita)}" class="btn btn-primary text-light">Modifica</a>
              </div>

              <div class="col-12 col-lg-4 text-center mt-1">
                <a href="partecipanti?id=${e:forUriComponent(idAttivita)}" class="btn btn-primary text-light">Partecipanti</a>
              </div>
              </c:if>
              <c:if test="${(not empty ruolo) && (ruolo == 'globetrotter') && (statoAttivita != 'chiuso')}">
              <div class="col-12 col-lg-12 mt-1 text-center">
                <a href="iscriviti?id=${e:forUriComponent(idAttivita)}" class="btn btn-primary text-light">Iscriviti</a>
              </div>
              </c:if>
            </div>
            </div>
          </div>
          <div class="row separator">
          	<h5>Feedback:</h5>
           </div>
          <c:forEach items="${listaFeedback}" var="feedback">
          <div class="row mt-5 no-gutters">
					<div class="col-12 col-lg-9 mb-2 mx-auto ">
						<div class="card text-left shadow">
							<div class="card-header">
								<div class="row no-gutters">
									<div class="col-6 text-left">
										<strong>Feedback id: </strong>${e:forHtml(feedback.idFeedback)}
									</div>
									<div class="col-6 text-right">
										<strong>Data di creazione: </strong>
										${e:forHtml(feedback.data)}
									</div>
								</div>
							</div>
							<div class="card-body">
								<div class="row no-gutters mb-1">
									<div class="col-12 col-lg-6 mt-1">
										<strong> Creatore:
										</strong>
									</div>
									<div class="col-12 col-lg-6 mt-1">
											${e:forHtml(feedback.autore.anagrafica.nome)} ${e:forHtml(feedback.autore.anagrafica.cognome)}
									</div>
								</div>
								<div class="row no-gutters mb-1">
									<textarea readonly class="form-control-plaintext">${e:forHtml(feedback.commento)}</textarea>
								</div>

							</div>
							<div class="card-footer">
							<div class="row">
										<div class="col-12 col-lg-6 mt-1">
											<strong>Valutazione:</strong>
										</div>
										<div class="col-12 col-lg-6 mt-1">
											${e:forHtml(feedback.valutazione)}/5
										</div>
									</div>
							</div>
						</div>
					</div>


				</div>
          </c:forEach>
          <c:if test="${not empty listaFeedback}">
          <nav aria-label="Page navigation">
				<ul class="pagination justify-content-center mt-3">
					<li
						class="page-item <c:if test="${pageIndex == '0'}"> disabled </c:if>"><a
						class="page-link"
						href="visualizzaAttivita?p=${pageIndex-1}&id=${idAttivita}">Previous</a></li>
					<c:forEach begin="1" end="${pageSize}" varStatus="loop">
						<li
							class="page-item  <c:if test="${pageIndex == loop.index - 1}"> active</c:if>"><a
							class="page-link"
							href="visualizzaAttivita?p=${loop.index-1}&id=${idAttivita}">${loop.index}</a></li>
					</c:forEach>
					<li
						class="page-item <c:if test="${pageIndex == (pageSize-1)}"> disabled </c:if>"><a
						class="page-link"
						href="visualizzaAttivita?p=${pageIndex+1}&id=${idAttivita}">Next</a></li>
				</ul>
			</nav>
          </c:if>
          </div>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap-input-spinner.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Bing map sdk -->

<script src="js/MapViewer.js"></script>

<!-- Custom JavaScript for this theme -->

<script src="js/cpanel.js"></script>
<script src="js/grid-list-view.js"></script>


</body>

</html>
