<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Cicerone - login</title>
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

<body>
  <div class="container-fluid">
    <div class="row full-width">
      <div class="col-lg-12 bg-white-vivident mt-lg-3 mb-3 senzapadding">
        <div class="card bg-darker fullallrow">
          <div class="mr-lg-5 ml-lg-5 mt-lg-5">
            <div class="row">
              <div class="col-lg-12">
                <div class="card-block mt-5 shadow-lg">
                  <div class="card-body text-center">
                    <img src="image/error.png">
                    <h1>${error} ${status}</h1>
                    <h1>Qualcosa &egrave; andato storto!</h1>
                    <h2>I nostri ingegneri sono al lavoro</h2>
                    <a href="loggedHome" class="btn btn-info">Torna alla home</a>
                  </div>
                </div>
              </div>
            </div>
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
  <script src="js/bingplaceApi.js"></script>
  <script src="js/validationscript.js"></script>
  <script src="js/passwordHider.js"></script>
</body>

</html>
