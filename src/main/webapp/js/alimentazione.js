$(document).ready(function(){

    setRecipeRandom("");
    $("#cerca").click(function(){
        setRecipeRandom($("#inputIngredients").val());
    });
    
    $(".btnAccetta").click(function(event){
        accept($(event.currentTarget).val());
    });
    
        
});
//ingredients=ciniewnsd,cdnsicns
function setRecipeRandom(ingredients){


    $.getJSON("/getRecipes?ingredients="+ingredients, function(json){

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

function accept(id){


    $.getJSON("/addRecipeToHistory?idRicetta="+id, function(json){

       
				
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
	$("#ricette").empty();
	for (var i = 0; i < json.recipes.length && i<3; i++){

		var ingrediens = "<ul>";
		for(var j=0; j < json.recipes[i].ingredients.length; j++){
			ingrediens += "<li>" + json.recipes[i].ingredients[j].name + "</li>";
		}
		ingrediens += "</ul>"
		
		$("#ricette").append( "<div class=\"row\">"+
			"<div class=\"col-md-7\">"+
			"<a href=\""+json.recipes[i].id+".jpg\">"+
			"<img class=\"img-responsive imgSport\" src=\"../img/"+json.recipes[i].id+".jpg\">"+
			"</a>"+
			"</div>"+
			"<div class=\"col-md-5\">"+
			"<h3 id=\"nomeRicetta\">"+json.recipes[i].name+"</h3>"+
			"<h4 id=\"ingredienti\">ingredienti:</h4>"+
				ingrediens +
			"<p>Preparazione: <br> <span id=\"descRicetta\">"+json.recipes[i].instructions+"</span></p>"+
			"<a class=\"btn btn-primary btnAccetta\" href=\"buonappetito.html\" id=\""+json.recipes[i].id+"\">Accetta <span class=\"glyphicon glyphicon-chevron-right\"></span></a>"+
			"</div>"+
			"</div>" +
			"<br><br><br>" );
	}

}

