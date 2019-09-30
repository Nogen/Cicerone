$(document).ready(function() {

  $("#password_hider a").on("click", function(event) {
    event.preventDefault();
    if ($("#password_hider input").attr("type") == "password") {
      $("#password_hider input").attr("type", "text");
      $("#password_hider i").addClass("fa-eye-slash");
      $("#password_hider i").removeClass("fa-eye");
    } else {
      $("#password_hider input").attr("type", "password");
      $("#password_hider i").addClass("fa-eye");
      $("#password_hider i").removeClass("fa-eye-slash");
    }
  });
});
