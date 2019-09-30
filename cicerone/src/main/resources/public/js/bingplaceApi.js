var languages = [
  "Afrikanns",
  "Albanian",
  "Arabic",
  "Armenian",
  "Basque",
  "Bengali",
  "Bulgarian",
  "Catalan",
  "Cambodian",
  "Chinese (Mandarin)",
  "Croation",
  "Czech",
  "Danish",
  "Dutch",
  "English",
  "Estonian",
  "Fiji",
  "Finnish",
  "French",
  "Georgian",
  "German",
  "Greek",
  "Gujarati",
  "Hebrew",
  "Hindi",
  "Hungarian",
  "Icelandic",
  "Indonesian",
  "Irish",
  "Italian",
  "Japanese",
  "Javanese",
  "Korean",
  "Latin",
  "Latvian",
  "Lithuanian",
  "Macedonian",
  "Malay",
  "Malayalam",
  "Maltese",
  "Maori",
  "Marathi",
  "Mongolian",
  "Nepali",
  "Norwegian",
  "Persian",
  "Polish",
  "Portuguese",
  "Punjabi",
  "Quechua",
  "Romanian",
  "Russian",
  "Samoan",
  "Serbian",
  "Slovak",
  "Slovenian",
  "Spanish",
  "Swahili",
  "Swedish ",
  "Tamil",
  "Tatar",
  "Telugu",
  "Thai",
  "Tibetan",
  "Tonga",
  "Turkish",
  "Ukranian",
  "Urdu",
  "Uzbek",
  "Vietnamese",
  "Welsh",
  "Xhosa"
];
$(document).ready(function () {



        $("#placeauto").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "http://dev.virtualearth.net/REST/v1/Locations",
                    dataType: "jsonp",
                    data: {
                        key: "AmEx2BMNQU-8ypVbNrAOhucG--62omkRJBeaXbko6DksjOofTVcva-iadW0um1vk", //bing better than google
                        q: request.term
                    },
                    jsonp: "jsonp",
                    success: function (data) {
                        var result = data.resourceSets[0];
                        if (result) {
                            if (result.estimatedTotal > 0) {
                                response($.map(result.resources, function (item) {
                                    return {
                                        data: item,
                                        label: item.name + ' (' + item.address.countryRegion + ')',
                                        value: item.name
                                    }
                                }));
                            }
                        }
                    }
                });
            },
            minLength: 1
        });


        $("#languagesauto").autocomplete({source: languages});
        $("#languagesauto").autocomplete("option", "appendTo", ".ancora");
        $("#datepick").datepicker();
    });
