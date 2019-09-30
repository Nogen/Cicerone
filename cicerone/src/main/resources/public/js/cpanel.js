/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
function openNav() {
  if ($(window).width() <= 500) {
    document.getElementById("mySidenav").style.left = "0px";
    document.getElementById("sideOpenBtn").style.marginLeft ="250px";
  } else {
    document.getElementById("mySidenav").style.left = "0px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById("sideOpenBtn").style.marginLeft ="250px";
  }

}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.left = "-250px";
  document.getElementById("main").style.marginLeft = "0";
  document.getElementById("sideOpenBtn").style.marginLeft = "0";
}
function opencloseNav() {
  if (document.getElementById("mySidenav").style.left == "-250px") {
    openNav();
    document.getElementById("navImage").src="icons/navclose.png";
  } else {
    closeNav();
    document.getElementById("navImage").src="icons/navopen.png";
  }
}

$(document).ready(function() {
    if ($(window).width() <= 500) {
      closeNav();
      document.getElementById("navImage").src="icons/navopen.png";
    }
});
