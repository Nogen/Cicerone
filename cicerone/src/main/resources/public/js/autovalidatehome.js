function validateForm(nomeForm, n) {

  var form = document.forms[nomeForm].elements;
  var i;
  result = true;
  for (i = 0; i < n; i++) {
      var value = validatingOnFocus(form[i]);
      result = result && value;
  }
  return result;
}

function validatingOnFocusOut(obj) {
  var valore = obj.value;

  if (valore.length < 4 || valore.length > 100) {
    obj.classList.remove("is-valid");
    obj.classList.add("is-invalid");
    return false;
  } else {
    obj.classList.remove("is-invalid");
    obj.classList.add("is-valid");
    return true;
  }

}
