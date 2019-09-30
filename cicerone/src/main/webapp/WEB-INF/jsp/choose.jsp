<!DOCTYPE html>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - Choose</title>
  <link rel="icon" href="image/icon.png">
  <!-- Bootstrap core CSS -->
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/scrolling-nav.css" rel="stylesheet">
  <link href="css/choose.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Exo+2|Cute+Font|Cinzel:700&display=swap" rel="stylesheet">

</head>

<body>
  <div class="container-fluid">
    <div class="row h-100 no-gutters">
      <div class="col-lg-12 mt-lg-3 mb-lg-3 text-center">
        <div class="card bg-white-vivident h-100 mb-lg-3">
          <div class="row mb-5">
            <div class="col">
              <div class="card cardio shadow-lg text-center mt-1">
                <h1 class="myfont">Benvenuto</h1>
                <h4><a class="myfont" href="profilo?id=${e:forUriComponent(id)}">${e:forHtml(nome)} ${e:forHtml(cognome)}</a></h4>
                <img src="image/avatar.gif" class="mx-auto d-block preview-size circle-form-border" alt="Cicerone is the way">
                <h3 class="myfont">Scegli il tuo ruolo..</h3>
                <a href="modificaProfilo">Modifica profilo</a>
                <a href="logout">Esci</a>
              </div>
            </div>
          </div>
          <div class="row border-up border-column-up">
            <div class="col mt-5 mb-5 border-column-right">

              <div class="card-block shadow" >
                <img src="image/globetrotter.gif" class="avatar-size circle-form-border overflow-image-up " alt="Cicerone is the way">
                <div class="card-body">
                  <a href="chooseGlobetrotter" class="card-title btn btn-info">Entra come Globetrotter</a>
                  <p>
                    <a class="btn btn-secondary" data-toggle="collapse" href="#Descrizioni" role="button" aria-expanded="true" aria-controls="Descrizioni">
                      Nascondi/Mostra
                    </a>
                  </p>
                  <div class="collapse show" id="Descrizioni">
                    <ul class="list-group">
                      <li class="bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">1</span>
                        <p>Cerca Attivit&#224; adatte a te</p>
                      </li>
                      <li class="bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">2</span>
                        <p>Iscriviti e gestisci le tue Attivit&#224;</p>
                      </li>
                      <li class=" bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">3</span>
                        <p>Partecipa alle Attivit&#224;</p>
                      </li>
                      <li class=" bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">4</span>
                        <p>Lascia un feedback</p>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <div class="col mt-5 border-column-left mb-5">
              <div class="card-block shadow" >
                <img src="image/cicerone.gif" class="avatar-size circle-form-border overflow-image-up " alt="Cicerone is the way">
                <div class="card-body">
                  <a href="chooseCicerone" class="card-title btn btn-danger">Entra come Cicerone</a>
                  <p>
                    <a class="btn btn-secondary" data-toggle="collapse" href="#Descrizioni" role="button" aria-expanded="true" aria-controls="Descrizioni">
                      Nascondi/Mostra
                    </a>
                  </p>
                  <div class="collapse show" id="Descrizioni">
                    <ul class="list-group">
                      <li class="bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">1</span>
                        <p>Dai sfogo alle tue idee e crea Attivit&#224;</p>
                      </li>
                      <li class="bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">2</span>
                        <p>Gestisci gli iscritti e le relative Attivit&#224;</p>
                      </li>
                      <li class=" bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">3</span>
                        <p>Tieniti in contatto con i tuoi iscritti</p>
                      </li>
                      <li class=" bg-transparent list-group-item d-flex justify-content-between align-items-center">
                        <span class="badge badge-primary badge-pill">4</span>
                        <p>Divertiti e guadagna</p>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row border-column-bottom">
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

</body>

</html>
