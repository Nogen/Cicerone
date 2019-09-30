function round(value, decimals) {
  return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
}

function loadMapScenario() {
  var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
    zoom: 12 });
    Microsoft.Maps.loadModule('Microsoft.Maps.AutoSuggest', function () {
      var options = {
        maxResults: 4,
        businessSuggestions: true,
        map: map
      };
      var manager = new Microsoft.Maps.AutosuggestManager(options);
      manager.attachAutosuggest('#searchBox', '#searchBoxContainer', selectedSuggestion);
    });
    function selectedSuggestion(suggestionResult) {
      map.entities.clear();
      map.setView({ bounds: suggestionResult.bestView });
      var pushpin = new Microsoft.Maps.Pushpin(suggestionResult.location,  { title: "Punto d'incontro", subTitle: suggestionResult.title});
      map.entities.push(pushpin);
      document.getElementById("lat").value = suggestionResult.location.latitude;
      document.getElementById("lon").value = suggestionResult.location.longitude;
    }

    Microsoft.Maps.Events.addHandler(map, 'click', function (e) {
      map.entities.clear();
      var loc = e.location;
      var searchManager = new Microsoft.Maps.Search.SearchManager(map);
      document.getElementById("lat").value = round(loc.latitude, 5);
      document.getElementById("lon").value = round(loc.longitude, 5);
      var reverseGeocodeRequestOptions = {
        location: loc,
        callback: function (answer, userData) {
          map.entities.push(new Microsoft.Maps.Pushpin(reverseGeocodeRequestOptions.location, { title: "Punto d'incontro", subTitle: answer.address.addressLine}));
          document.getElementById("searchBox").value =
          answer.address.formattedAddress;
        }
      };
      searchManager.reverseGeocode(reverseGeocodeRequestOptions);

    });
}
