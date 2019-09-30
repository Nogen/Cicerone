<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="e"
	uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Cicerone- cpanel</title>
<link rel="icon" href="image/icon.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="vendor/jquery-ui/jquery-ui.css" rel="stylesheet">
<link href="vendor/jquery-ui/jquery-ui.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/cpanel.css" rel="stylesheet">
<link href="css/sidnav.css" rel="stylesheet">

</head>

<body>
	<nav
		class="navbar sticky-top navbar-expand-lg navbar-dark navbar-style">
		<a class="navbar-brand" href="#"><img alt="Image here"
			src="image/logo.png" class="d-inline-block align-top">Cicerone</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse ml-auto"
			id="navbarSupportedContent">
			<div class="navbar-nav ml-auto">
				<a class="nav-item nav-link " href="loggedHome"> <img
					alt="Image here" src="icons/home.png" class="dimIcons">
					<div class="collapse sameline" id="navbarSupportedContent">
						Home</div>
				</a> <a class="nav-item nav-link" href="modificaProfilo"> <img
					alt="Image here" src="icons/profile.png" class="dimIcons">
					<div class="collapse sameline" id="navbarSupportedContent">
						Modifica profilo</div>
				</a> <a class="nav-item nav-link" href="logout"> <img alt="Image here"
					src="icons/logout.png" class="dimIcons">
					<div class="collapse sameline" id="navbarSupportedContent">
						Log-out</div>
				</a>
			</div>
		</div>
	</nav>
	<a href="javascript:void(0)" class="leftnavbutton"
		onclick="opencloseNav()" id="sideOpenBtn"><img alt="Image here"
		src="icons/navclose.png" id="navImage"></a>
	<div id="mySidenav" class="sidenav">
		<div class="container topheader mb-3 text-center">
			<div class="row">
				<div class="col-3 mt-2 mb-2 text-left">
					<img alt="Image here" src="image/${e:forUriComponent(ruolo)}.png"
						class="avatarmain circle-form-border" alt="Cicerone is the way">
				</div>
				<div class="col-9 mt-2 mb-2">
					<div class="col-12">
						<strong> Benvenuto </strong>
					</div>
					<div class="col-12">${e:forHtml(nome)}</div>
					<div class="col-12">${e:forHtml(cognome)}</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row filter">
				<div class="col mt-2 mb-2">
					<a href="loggedHome"><img alt="Image here" src="icons/home.png"
						class="dimIcons mr-3">Home</a>
				</div>
			</div>
			<div class="row filter">
				<div class="col mt-2 mb-2">
					<a href="trovaAttivita"><img alt="Image here"
						src="icons/operations.png" class="dimIcons mr-3">Trova
						Attivit&#224;</a>
				</div>
			</div>
			<div class="row filter active">
				<div class="col mt-2 mb-2">
					<a href="gestioneRichieste"><img alt="Image here"
						src="icons/notification.png" class="dimIcons mr-3">Richieste</a>
				</div>
			</div>
			<div class="row filter">
				<div class="col mt-2 mb-2">
					<a href="modificaProfilo"><img alt="Image here"
						src="icons/profile.png" class="dimIcons mr-3">Modifica
						profilo</a>
				</div>
			</div>
			<div class="row filter">
				<div class="col mt-2 mb-2">
					<a href="chooseCicerone"><img alt="Image here"
						src="icons/switchglobetrotter.png" class="dimIcons mr-3"> a
						Cicerone</a>
				</div>
			</div>
			<div class="row filter">
				<div class="col mt-2 mb-2">
					<a href="logout"><img alt="Image here" src="icons/logout.png"
						class="dimIcons mr-3">Esci</a>
				</div>
			</div>
		</div>
	</div>
	<div class="fuckmargin" id="main">
		<div class="container-fluid">
			<div class="row mt-5 separator align-items-center pb-1">
				<div class="col-6 col-lg-6 text-right text-muted"
					id="numberOfSearch"> ${e:forHtml(totalSize)} richieste trovate</div>
				<div class="col-6 text-muted leftspacer" id="numberOfSearch">
					<div class="row align-items-center">
						<div class="col col-lg-3 text-center">Ordina:</div>

						<div class="col col-lg-9 text-left">
							<form action="gestioneRichieste" method="get">
								<select id="ordina" class="form-control selectorlist "
									name="orderBy" onchange="this.form.submit()">
									<option
										<c:if test = "${orderBy == 'statoRichiesta'}"> selected </c:if>
										value="statoRichiesta">Per Stato</option>
									<option value="titolo"
										<c:if test = "${orderBy == 'titolo'}"> selected </c:if>>Per
										titolo</option>
									<option value="dataRichiesta"
										<c:if test = "${orderBy == 'dataRichiesta'}"> selected </c:if>>Per
										data</option>
								</select>
							</form>
						</div>
					</div>
				</div>
			</div>

			<c:forEach items="${listaRichieste}" var="richiesta">
				<div class="row mt-5 no-gutters">
					<div class="col-12 col-lg-12 mb-2 mr-lg-0 ">
						<div class="card text-left shadow">
							<div class="card-header">
								<div class="row no-gutters">
									<div class="col-6 text-left">
										<strong>Richiesta id: </strong>${e:forHtml(richiesta.key.idRichiesta)}
									</div>
									<div class="col-6 text-right">
										<strong>Data di creazione: </strong>
										${e:forHtml(richiesta.key.dataRichiesta)}
									</div>
								</div>
							</div>
							<div class="card-body">
								<h5 class="card-title">
									<strong>${e:forHtml(richiesta.key.attivita.titolo)}</strong>
								</h5>
								<div class="row no-gutters mb-1">
									<div class="col-12 col-lg-6 mt-1">
										<strong> Hai chiesto di unirti alla attivit&agrave;:
										</strong>
									</div>
									<div class="col-12 col-lg-6 mt-1">
										<a class="underlines"
											href="visualizzaAttivita?id=${e:forUriComponent(richiesta.key.attivita.idAttivita)}">
											${e:forHtml(richiesta.key.attivita.titolo)}</a>
									</div>
								</div>
								<div class="row no-gutters mb-1">
									<div class="col-12 col-lg-6 mt-1">
										<strong>creata da: </strong>
									</div>
									<div class="col-12 col-lg-6 mt-1">
										<a class="underlines"
											href="profilo?id=${e:forUriComponent(richiesta.key.attivita.creatore.idAccount)}">
											${e:forHtml(richiesta.key.attivita.creatore.anagrafica.nome)}
											${e:forHtml(richiesta.key.attivita.creatore.anagrafica.cognome)}</a>
									</div>
								</div>
								<div class="row mb-1">
									<c:if test="${richiesta.key.statoRichiesta == 'accettata'}">

										<div class="col-12 mt-3">
											<strong>Contatti Cicerone:</strong>
										</div>


										<div class="col-12 col-lg-6 mt-3">
											<strong>Numero di telefono:</strong>
										</div>
										<div class="col-12 col-lg-6 mt-1">
											${e:forHtml(richiesta.key.attivita.creatore.anagrafica.numeroDiTelefono)}
										</div>


										<div class="col-12 col-lg-6 mt-1">
											<strong>Email:</strong>
										</div>
										<div class="col-12 col-lg-6 mt-1">
											${e:forHtml(richiesta.key.attivita.creatore.email)}</div>

									</c:if>
								</div>
								<c:if test="${not empty richiesta.value}">
									<div class="card shadow mx-auto">
									<div class="card-header"><strong>Feedback inviato</strong></div>
									<div class="row mt-1">
										<div class="col-12 mb-3">
											<strong>Commento:</strong>
										</div>
										<div class="col-12 mb-3">
											<textarea class="form-control-plaintext" readonly name="commento">${e:forHtml(richiesta.value.commento)}</textarea>
										</div>
									</div>
									<div class="card-footer">
									<div class="row">
										<div class="col-12 col-lg-6 mt-1">
											<strong>Valutazione:</strong>
										</div>
										<div class="col-12 col-lg-6 mt-1">
											${e:forHtml(richiesta.value.valutazione)}/5
										</div>
									</div>
									</div>
									</div>
								</c:if>
								<c:if test="${richiesta.key.attivita.statoAttivita == 'chiuso' && richiesta.key.statoRichiesta == 'accettata' && empty richiesta.value}">
								<form action="gestioneRichieste" method="post">
									<div class="row no-gutters mt-5">
										<div class="col-12">Lascia un feedback...</div>
									</div>
									<div class="row mt-5">
										<div class="col-12 mb-3">
											<strong>Inserisci qui il commento:</strong>
										</div>
										<div class="col-12 mb-3">
											<textarea class="form-control" name="commento"></textarea>
										</div>
										<input readonly hidden
											value="${richiesta.key.attivita.idAttivita}" name="idAttivita">
									</div>
									<div class="row mt-3">
										<div class="col-12 col-lg-6">
											<strong>Seleziona la tua valutazione:</strong>
										</div>
										<div class="col-12 col-lg-6">
											<select class="form-control selectorlist" name="valutazione">
												<option selected>1</option>
												<option selected>2</option>
												<option selected>3</option>
												<option selected>4</option>
												<option selected>5</option>
											</select>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-12">
											<input type="submit" class="btn btn-primary text-light">
										</div>
									</div>
									</form>
								</c:if>
							</div>
							<div class="card-footer">
								<div class="row no-gutters">
									<div class="col-6 text-left">
										<strong>stato: </strong>${e:forHtml(richiesta.key.statoRichiesta)}
									</div>
								</div>

							</div>
						</div>
					</div>


				</div>
			</c:forEach>
			<c:if test="${not empty listaRichieste}">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center mt-3">
					<li
						class="page-item <c:if test="${pageIndex == '0'}"> disabled </c:if>"><a
						class="page-link"
						href="gestioneRichieste?p=${pageIndex-1}&orderBy=${orderBy}">Previous</a></li>
					<c:forEach begin="1" end="${pageSize}" varStatus="loop">
						<li
							class="page-item  <c:if test="${pageIndex == loop.index - 1}"> active</c:if>"><a
							class="page-link"
							href="gestioneRichieste?p=${loop.index-1}&orderBy=${orderBy}">${loop.index}</a></li>
					</c:forEach>
					<li
						class="page-item <c:if test="${pageIndex == (pageSize-1)}"> disabled </c:if>"><a
						class="page-link"
						href="gestioneRichieste?p=${pageIndex+1}&orderBy=${orderBy}">Next</a></li>
				</ul>
			</nav>
			</c:if>
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
