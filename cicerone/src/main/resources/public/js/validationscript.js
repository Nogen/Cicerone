function validateNome(input) {
  if (!input.value.match(/^[A-Z][a-z]{2,99}$/)) {
     input.setCustomValidity("The name must start with capital letter, and its length will be  3 characters min and 100 characters max");
  } else {
     input.setCustomValidity("");
  }
}

function validateCognome(input) {
  if (!input.value.match(/^[A-Z][A-Za-z ]{2,99}$/)) {
     input.setCustomValidity("The surname must be length at least 3 characters and maximum 100 characters");
  } else {
     input.setCustomValidity("");
  }
}

function validateNTelefono(input) {
  if (!input.value.match(/^[0-9]{10}$/)) {
    input.setCustomValidity("Only 10 digit.");
  } else {
    input.setCustomValidity("");
  }
}


function validatePassword(input) {
  if (!input.value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/)) {
    input.setCustomValidity("Minimum 8 characters, maximum 16 characters,  at least one uppercase letter, one lowercase letter, one number and one special character: @$!%*?&");
  } else {
    input.setCustomValidity("");
  }
}

function validateDoublePassword(input, otherForm) {
  var psw = document.getElementById(otherForm).value;
  if (input.value !== psw) {
    input.setCustomValidity("The passwords must be equal.");
  } else {
    input.setCustomValidity("");
  }
}
