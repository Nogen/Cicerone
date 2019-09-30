
 var elementi = document.getElementsByClassName("viewclass");
 var i;
/* setting full width column when screen is small or large*/
function listView() {
  for (i=0; i < elementi.length; i++) {
    elementi[i].classList.remove("col-6");
    elementi[i].classList.add("col-12");
    elementi[i].classList.remove("col-lg-6");
    elementi[i].classList.add("col-lg-12");
  }
  $('.card').addClass("cardiolist");
  $("#listView").addClass("active");
  $("#gridView").removeClass("active");

}

/*Setting grid element width policies when screen is small or large*/
function gridView() {
  for (i=0; i < elementi.length; i++) {
    elementi[i].classList.remove("col-12");
    elementi[i].classList.add("col-6");
    elementi[i].classList.remove("col-lg-12");
    elementi[i].classList.add("col-lg-6");
  }
  $('.card').removeClass("cardiolist");
  $("#gridView").addClass("active");
  $("#listView").removeClass("active");
}
