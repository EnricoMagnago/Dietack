$(document).ready(function(){

	setRecipeRandom("");

});
//ingredients=ciniewnsd,cdnsicns
function setRecipeRandom(ingredients){
    
	$.getJSON("/RecipeServlet?ingrdients="+ingredients, function(json){

				updateRecipeList(json);
				
	})
			.done(function() {
				console.log( "second success" );
			})
			.fail(function() {
				console.log( "error" );
			})
			.always(function() {
				console.log( "complete" );
			});

}

function setRecipeRandom(json){


	var source = $("#request-template").html();
	var template = Handlebars.compile(source);

	
	for (var i = 0; i < json.ricette.length && i<3; i++){
		
		
		$("#list").append( "<div class=\"row\">"+
            "<div class=\"col-md-7\">"+
                "<a href=\""+json.ricette[i].id+".jpg\">"+
                    "<img class=\"img-responsive\" src=\"http://placehold.it/700x300\">"+
                "</a>"+
            "</div>"+
            "<div class=\"col-md-5\">"+
                "<h3 id=\"nomeRicetta\">"+json.ricette[i].name+"</h3>"+
                "<h4>Totale Calorie: <span id=\tot\"></span></h4>"+
                "<p>Descrizione Ricetta:  <span id=\"descRicetta\"></span></p>"+
                "<a class=\"btn btn-primary\" href=\"buonappetito.html\">Accetta <span class=\"glyphicon glyphicon-chevron-right\"></span></a>"+
            "</div>"+
        "</div>" );
	}

}

