function GetMap() {
  Microsoft.Maps.loadModule('Microsoft.Maps.AutoSuggest', {
           callback: function () {
               var manager = new Microsoft.Maps.AutosuggestManager({

               });
               manager.attachAutosuggest('#placeauto', '#colonna', autoCompleteFun);
           },
           errorCallback: function(msg){
               
           }
       });
}

function autoCompleteFun(result) {

}
