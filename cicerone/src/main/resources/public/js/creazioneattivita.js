
$(document).ready(function() {
  $("#aggiungi").click(function () {
    var nuovaLingua = $("#languagesauto").val();
    var vecchieLingue = $("#Lingue-Aggiunte").val();
    $("#languagesauto").val("");
    if (!vecchieLingue.includes(nuovaLingua) &&
    languages.indexOf(nuovaLingua) > -1) {
      $("#Lingue-Aggiunte").val("#" + nuovaLingua + " " + vecchieLingue);
    }
  });
  $("#Retribuzione-chooser").change(function(){;
    if ($(this).val() == "Retribuito") {
      var input = document.createElement("input");
      input.setAttribute("id", "Valore-retribuzione");
      input.setAttribute("name", "valoreRetribuzione");
      input.setAttribute("type", "text");
      input.setAttribute("placeholder", "Inserisci qui il valore...");
      input.className = "form-control";
      document.getElementById("Campo-retribuzione").appendChild(input);
    } else {
      var container = document.getElementById("Campo-retribuzione");
      container.removeChild(container.lastChild);
    }
  });

  $("#hours").inputSpinner();
  $("#minutes").inputSpinner();
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1; //January is 0!
  var yyyy = today.getFullYear();
  if(dd<10){
    dd='0'+dd
  }
  if(mm<10){
    mm='0'+mm
  }

  today = yyyy+'-'+mm+'-'+dd;
  $("#dateUi").attr("min", today);




});
