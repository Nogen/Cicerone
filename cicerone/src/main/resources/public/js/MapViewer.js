
function loadMap() {
  var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
    /* No need to set credentials if already passed in URL */
    center: new Microsoft.Maps.Location(document.getElementById("lat").value, document.getElementById("lon").value),
    zoom: 10 });
    var pushpin = new Microsoft.Maps.Pushpin(map.getCenter(), { title: "Punto d'incontro", subTitle: document.getElementById("luogo").value});
    map.entities.push(pushpin);
  }
