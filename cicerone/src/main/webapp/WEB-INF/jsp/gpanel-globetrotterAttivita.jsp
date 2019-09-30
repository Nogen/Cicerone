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
			<div class="row filter active">
				<div class="col mt-2 mb-2">
					<a href="trovaAttivita"><img alt="Image here"
						src="icons/operations.png" class="dimIcons mr-3">Trova
						Attivit&#224;</a>
				</div>
			</div>
			<div class="row filter">
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
			<form>
				<div class="row mt-5 justify-content-center">

					<div class="col-lg-9 col-12">
						<div class="input-group input-group-lg">
							<input type="text" class="form-control" placeholder="Ricerca"
								aria-label="Ricerca" aria-describedby="basic-addon1" name="byKey" value="${e:forHtmlAttribute(byKey)}">
							<div class="input-group-append">
								<button class="btn btn-info border-left-0 border" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-5 justify-content-center">
					<div class="col-12 col-lg-8 mb-2">

						<div class="form-group">
							<div class="form-row mb-3">
								<div class="col-12 col-lg-6">
									<label for="placeauto">Luogo:</label> <input type="text"
										class="form-control" placeholder="Completamento automatico..."
										id="placeauto" name="byLuogo"
										value="${e:forHtmlAttribute(byLuogo)}">
								</div>
								<div class="col-12 col-lg-6 ancora">
									<label for="languagesauto">Lingua:</label> <input type="text"
										class="form-control" placeholder="Completamento automatico..."
										id="languagesauto" name="byLingua"
										value="${e:forHtmlAttribute(byLingua)}">
								</div>
							</div>
							<div class="form-row mb-3">
								<div class="col-12 col-lg-6 mb-3">
									<label for="placeauto">Da questa data:</label> <input
										type="date" class="form-control" max="3000-12-31" id="dateUi"
										name="byDate" value="${e:forHtmlAttribute(byDate)}">
								</div>

							</div>
						</div>

					</div>
				</div>
				<div class="row mt-5 justify-content-lg-center">
					<div class="col-12 col-lg-4 mb-3">
						<input type="submit" class="btn btn-info text-light"
							value="Imposta filtri">
					</div>
					<div class="col-12 col-lg-4 mb-3">
						<input type="reset" class="btn btn-info text-light"
							value="Pulisci filtri">
					</div>
				</div>


				<div class="row mt-5 separator align-items-center pb-1">
					<div class="col text-right text-muted" id="numberOfSearch">
						${totalSize} Attivit&#224; trovate</div>
					<div class="col text-muted leftspacer" id="numberOfSearch">
						<div class="row align-items-center">
							<div class="col col-lg-3 text-center">Ordina:</div>
							<div class="col col-lg-9 text-left">
								<select id="ordina" class="form-control selectorlist "
									name="orderBy" onchange="this.form.submit()">
									<option
										<c:if test = "${orderBy == 'dataIncontro'}"> selected </c:if>
										value="dataIncontro">Per data</option>
									<option value="titolo"
										<c:if test = "${orderBy == 'titolo'}"> selected </c:if>>Per
										titolo</option>
									<option value="valoreRetribuzione"
										<c:if test = "${orderBy == 'valoreRetribuzione'}"> selected </c:if>>Per
										retribuzione</option>
								</select>

							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="row mt-5 no-gutters">
				<div
					class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5"
					role="alert" ${e:forHtmlAttribute(hide)}>
					<strong>${e:forHtml(message)} <a class="alert-link"
						href="${link}">${e:forHtml(linkmessage)}</a></strong>
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<c:forEach items="${listaAttivita}" var="attivita">
					<div class="col-12 col-lg-12 cardiolist mb-2 mx-auto ">
						<div class="card text-left shadow">
							<div class="card-header">
								<div class="row">
									<div class="col-12 col-lg-6 mt-2">
										<div class="row no-gutters">
											<div class="col-12 col-lg-6 mt-1">
												<strong>Creatore: </strong>
											</div>
											<div class="col-12 col-lg-6 mt-1">
												<a class="underlines"
													href="profilo?id=${e:forUriComponent(attivita.creatore.idAccount)}">
													${e:forHtml(attivita.creatore.anagrafica.nome)}
													${e:forHtml(attivita.creatore.anagrafica.cognome)}</a>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-6 mt-2">
										<div class="row no-gutters">
											<div class="col-12 col-lg-6 mt-2">
												<strong>Data apertura:</strong>
											</div>
											<div class="col-12 col-lg-6  mt-2">
												${e:forHtml(attivita.dataApertura)}</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-12">
									<h5 class="card-title">
										<strong>Titolo:</strong>
									</h5>
								</div>
								<div class="col-12 text-center">
									<strong>${e:forHtml(attivita.titolo)} </strong>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-12 col-lg-6">
									<strong>Luogo d'incontro:</strong>
								</div>
								<div class="col-12 col-lg-6">
									<label class="form-control-plaintext">${e:forHtml(attivita.luogo)}</label>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-12 col-lg-6">
									<strong>Data d'incontro:</strong>
								</div>
								<div class="col-12 col-lg-6">
									<label class="form-control-plaintext">${e:forHtml(attivita.dataIncontro)}</label>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-12 col-lg-6">
									<strong>Ora d'incontro:</strong>
								</div>
								<div class="col-12 col-lg-6">
									<label class="form-control-plaintext">${e:forHtml(attivita.oraIncontro)}</label>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-12 col-lg-6">
									<strong>Valore retribuzione</strong>
								</div>
								<div class="col-12 col-lg-6">
									<label class="form-control-plaintext">${e:forHtml(attivita.valoreRetribuzione)}
										€</label>
								</div>
							</div>
							<div class="card-body">
								<div class="row no-gutters mb-1">
									<textarea class="form-control" rows="6" readonly>${e:forHtml(attivita.descrizioneAttivita)}</textarea>
								</div>
							</div>
							<div class="card-footer">
								<div class="row no-gutters justify-content-between">
									<c:if test="${attivita.statoAttivita != 'chiuso'}">
										<div class="col-12 col-lg-6 mt-1 text-center">
											<a href="iscriviti?id=${attivita.idAttivita}"
												class="btn btn-primary text-light">Iscriviti</a>
										</div>
									</c:if>
									<div class="col-12 col-lg-6 mt-1 text-center">
										<a
											href="visualizzaAttivita?id=${e:forUriComponent(attivita.idAttivita)}"
											class="btn btn-primary text-light">Dettagli</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:if test="${not empty listaAttivita}">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center mt-3">
					<li
						class="page-item <c:if test="${pageIndex == '0'}"> disabled </c:if>"><a
						class="page-link"
						href="trovaAttivita?p=${pageIndex-1}&orderBy=${orderBy}&byLingua=${e:forUriComponent(byLingua)}&byLuogo=${e:forUriComponent(byLuogo)}&byDate=${e:forUriComponent(byDate)}&byKey=${e:forUriComponent(byKey)}">Previous</a></li>
					<c:forEach begin="1" end="${pageSize}" varStatus="loop">
						<li
							class="page-item  <c:if test="${pageIndex == loop.index - 1}"> active</c:if>"><a
							class="page-link"
							href="trovaAttivita?p=${loop.index-1}&orderBy=${orderBy}&byLingua=${e:forUriComponent(byLingua)}&byLuogo=${e:forUriComponent(byLuogo)}&byDate=${e:forUriComponent(byDate)}&byKey=${e:forUriComponent(byKey)}">${loop.index}</a></li>
					</c:forEach>
					<li
						class="page-item <c:if test="${pageIndex == (pageSize-1)}"> disabled </c:if>"><a
						class="page-link"
						href="trovaAttivita?p=${pageIndex+1}&orderBy=${orderBy}&byLingua=${e:forUriComponent(byLingua)}&byLuogo=${e:forUriComponent(byLuogo)}&byDate=${e:forUriComponent(byDate)}&byKey=${e:forUriComponent(byKey)}">Next</a></li>
				</ul>
			</nav>
			</c:if>
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
