$(document).ready(function(){

    setRecipeRandom("");
    $("#cerca").click(function(){
        setRecipeRandom($("#inputIngredients").val());
    });
    
        
});
//ingredients=ciniewnsd,cdnsicns
function setRecipeRandom(ingredients){

    $.getJSON("/getRecipes?ingrdients="+ingredients, function(json){

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

function updateRecipeList(json){
	for (var i = 0; i < json.recipes.length && i<3; i++){
		
		
		$("#ricette").append( "<div class=\"row\">"+
			"<div class=\"col-md-7\">"+
			"<a href=\""+json.recipes[i].id+".jpg\">"+
			"<img class=\"img-responsive\" src=\"http://placehold.it/700x300\">"+
			"</a>"+
			"</div>"+
			"<div class=\"col-md-5\">"+
			"<h3 id=\"nomeRicetta\">"+json.recipes[i].name+"</h3>"+
			"<p>Descrizione Ricetta:  <span id=\"descRicetta\">"+json.recipes[i].instructions+"</span></p>"+
			"<a class=\"btn btn-primary\" href=\"buonappetito.html\">Accetta <span class=\"glyphicon glyphicon-chevron-right\"></span></a>"+
			"</div>"+
			"</div>" +
			"<br><br><br>" );
	}

}

