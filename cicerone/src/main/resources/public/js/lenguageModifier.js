$(document).ready(function() {
  $("#aggiungi").click(function () {
    var nuovaLingua = $("#languagesauto").val();
    var vecchieLingue = $("#Lingue-Aggiunte").val();
    if (!vecchieLingue.includes(nuovaLingua) &&
    languages.indexOf(nuovaLingua) > -1) {
      html = '<div class="alert alert-primary alert-dismissible fade show mt-1" role="alert" id="' + nuovaLingua +'">'
         + nuovaLingua +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="removeLingua(\''+ nuovaLingua +'\')">' +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
      '</div>';
      var linguaContainer = document.getElementById('lingueContainer');
      linguaContainer.innerHTML = linguaContainer.innerHTML + html;
      $("#Lingue-Aggiunte").val("#" + nuovaLingua + " " + vecchieLingue);
    }
  });

  var lingueInit = $("#Lingue-Aggiunte").val();
  if (lingueInit.length > 0) {
    lingueInit = lingueInit.replace(" ", "");
    lingueInit = lingueInit.split("#");
    var html = "";
    var i;
    for (i = 1; i < lingueInit.length; i++) {
      html += '<div class="alert alert-primary alert-dismissible fade show mt-1" role="alert" id="'+ lingueInit[i] + '">'
         + lingueInit[i] +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="removeLingua(\''+ lingueInit[i] +'\')">' +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
      '</div>';
    }
    var linguaContainer = document.getElementById('lingueContainer');
    linguaContainer.innerHTML = linguaContainer.innerHTML + html;
  }

});


function removeLingua(elem) {
  elem = elem.replace(" ", "");
  var result = $("#Lingue-Aggiunte").val();
  result = result.replace("#" + elem + " ", "").replace("#" + elem, "");
  $("#Lingue-Aggiunte").val(result);
}
