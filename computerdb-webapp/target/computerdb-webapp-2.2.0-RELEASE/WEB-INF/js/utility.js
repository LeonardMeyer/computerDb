function deleteEnabled(){
	
	var id = document.getElementById("computerId").getAttribute("value");
	var ctx = "${pageContext.request.contextPath}";
	document.getElementById("crudForm").setAttribute("action", ctx+"/Computer/"+id+"/Delete"); 
	document.getElementById("crudForm").submit();
}
