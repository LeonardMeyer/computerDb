function deleteEnabled(){
	
	var id = document.getElementById("computerId").getAttribute("value");
	document.getElementById("crudForm").setAttribute("action", "/computerdb-webapp/Computer/"+id+"/Delete"); 
	document.getElementById("crudForm").submit();
}
