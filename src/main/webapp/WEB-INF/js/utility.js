function deleteEnabled(){
	
	var id = document.getElementById("computerId").getAttribute("value");
	document.getElementById("crudForm").setAttribute("action","/computerDb/Computer/"+id+"/Delete"); 
	document.getElementById("crudForm").submit();
}
