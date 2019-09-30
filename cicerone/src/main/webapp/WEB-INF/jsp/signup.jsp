<!DOCTYPE html>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - Signup</title>
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
                <div class="card-block mt-5 shadow-lg">
                  <div class="text-center">
                    <img src="image/prova.png" class="card-img card-img-auto-size card-img-overflow-postion" alt="Cicerone is the way">
                  </div>
                  <div class="card-body pl-0 pr-0">
                    <form class="mr-lg-5 ml-lg-5 mt-lg-3 pb-lg-3 was-validated" action="signup" method="post">
                      <div class="row mt-3">
                        <div class="col-12 col-lg-6 align-self-end">
                          <label>Nome</label>
                          <input type="text" class="form-control" placeholder="Nome" oninput="validateNome(this)" name="nome" required>
                          <small class="form-text text-dark">Lettera iniziale maiuscola, lunghezza minima 2 caratteri</small>
                        </div>
                        <div class="col-12 col-lg-6 align-self-end">
                          <label>Cognome</label>
                          <input type="text" class="form-control" placeholder="Cognome" name="cognome" oninput="validateCognome(this)" required>
                        <small class="form-text text-dark">Lettera iniziale maiuscola, lunghezza minima 2 caratteri</small>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-12 col-lg-12 align-self-end">
                          <label>Email</label>
                          <input type="email" class="form-control" placeholder="Email" name="email" required>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-12 col-lg-6 align-self-end">
                          <label>Numero di telefono</label>
                          <input type="text" class="form-control" placeholder="Numero di telefono" name="ntelefono" oninput="validateNTelefono(this)" required>
                        <small class="form-text text-dark">10 cifre numeriche senza prefisso.</small>
                        </div>
                        <div class="col-12 col-lg-6 align-self-end" id="colonna">
                          <label>Citt&#224;</label>
                          <input type="text" class="form-control" placeholder="Citt&#224;" id="placeauto" name="residenza" required>
                           <small class="form-text text-dark">Campo autocompletato</small>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-12 col-lg-12 align-self-end">
                          <label>Password</label>
                          <div class="input-group" id="password_hider">
                            <input type="password" id="mainpsw" class="form-control" placeholder="Password" name="password" oninput="validatePassword(this)" required>
                            <div class="input-group-append">
                              <a href="" class="btn btn-light"><i class="fa fa-eye" aria-hidden="true"></i></a>
                            </div>
                          </div>
                          <small class="form-text text-dark">Lunghezza minima 8 massima 16, almeno una lettera maiuscola, minuscola, numere e un carattere speciale: @$!%*?&</small>
                        </div>
                        <div class="col-12 col-lg-12 align-self-end mt-3">
                          <label>Conferma password</label>
                          <div class="input-group" id="password_hider">
                            <input type="password" class="form-control" placeholder="Conferma Password"  oninput="validateDoublePassword(this, 'mainpsw')" required>
                            <div class="input-group-append">
                              <a href="" class="btn btn-light"><i class="fa fa-eye" aria-hidden="true"></i></a>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-6 col-lg-6 align-self-center">
                          <label class="form-check-label mr-5">Sesso:</label>
                        </div>
                        <div class="col-6 col-lg-6 align-self-end">
                          <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="genere" id="inlineRadio1" value="uomo" required>
                            <label class="form-check-label" for="inlineRadio1">Uomo</label>
                          </div>
                          <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="genere" id="inlineRadio2" value="donna" required>
                            <label class="form-check-label" for="inlineRadio2">Donna</label>
                          </div>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-12 col-lg-6 align-self-end">
                          <label>Data di nascita:</label>
                        </div>
                        <div class="col-12 col-lg-6 align-self-end">
                          <input type="date" name="bday" max="2300-12-31" min="1900-01-01" class="form-control"  required>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-12 col-lg-12">
                          <div class="form-check" required>
                            <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" required>
                            <label class="form-check-label" for="defaultCheck1">
                              Accetta i termini per la Privacy del tuo account e il contratto della piattaforma
                            </label>
                          </div>
                        </div>
                      </div>
                      <div class="row mt-3">
                        <div class="col-12 col-lg-12">
                          <button type="submit" class="btn btn-danger">Registrati</button>
                        </div>
                      </div>
                      <div class="row mt-3">
                          <div class="col text-center">
                            <a href="/login" class="underlines">Oppure Accedi</a>
                          </div>
                        </div>
                      
                      <div class="alert alert-${e:forHtmlAttribute(type)} alert-dismissible fade show mt-5" role="alert" ${e:forHtmlAttribute(hide)} id="messageBox">
                        <strong>${e:forHtml(message)} <a class="alert-link" href="${e:forUriComponent(link)}">${e:forHtml(linkmessage)}</a></strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom JavaScript for this theme -->
  <script src="js/scrolling-nav.js"></script>

  <script src="js/validationscript.js"></script>
  <script src="js/passwordHider.js"></script>
  <script src="js/autosuggestBing.js"></script>
  <script src="js/focusMessage.js"></script>
  <script type='text/javascript' src='http://www.bing.com/api/maps/mapcontrol?callback=GetMap&key=AmEx2BMNQU-8ypVbNrAOhucG--62omkRJBeaXbko6DksjOofTVcva-iadW0um1vk' async defer></script>
</body>

</html>
