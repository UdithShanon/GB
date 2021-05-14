/**
 * 
 */
//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidOrderIDSave").val("");
	$("#ORDER")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#accountID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "RegistraionAPI",
		type : type,
		data : $("#ORDER").serialize(),
		dataType : "text",
		complete : function(response, status) {
			//console.log(status);
			onItemSaveComplete(response.responseText, status);
			window.location.reload(true);
		}
	});

});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#OrderGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#accountID").val("");
	$("#ORDER")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "RegistraionAPI",
		type : "DELETE",
		data : "accountID=" + event.target.value,
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
			window.location.reload(true);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#OrderGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#accountID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#firstName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#lastName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#email").val($(this).closest("tr").find('td:eq(3)').text());
			$("#password").val($(this).closest("tr").find('td:eq(4)').text());
					
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// firstName
	if ($("#firstName").val().trim() == "") {
		return "Please insert order code.";
	}
	
	// lastName
	if ($("#lastName").val().trim() == "") {
		return "Please insert order Type.";
	}
	
	// email
	if ($("#email").val().trim() == "") {
		return "Please insert Customer Name.";
	}
	
	// password
	if ($("#password").val().trim() == "") {
		return "Please insert Customer Contact Number.";
	}
	
	
	return true;
}
 