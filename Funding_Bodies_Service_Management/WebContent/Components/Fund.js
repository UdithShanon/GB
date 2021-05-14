//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidOrderIDSave").val("");
	$("#Funding_Posts")[0].reset();
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
	var type = ($("#FunderID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "Funding_PostAPI",
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
	
	$("#FunderID").val("");
	$("#Funder_Posts")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "Funding_PostAPI",
		type : "DELETE",
		data : "FunderID=" + event.target.value,
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
			$("#ID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#FunderID").val($(this).closest("tr").find('td:eq(1)').text());
			$("#FunderName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#FunderContact").val($(this).closest("tr").find('td:eq(3)').text());
			$("#FundingAmount").val($(this).closest("tr").find('td:eq(4)').text());
			$("#cardNo").val($(this).closest("tr").find('td:eq(6)').text());
			$("#cvvNo").val($(this).closest("tr").find('td:eq(7)').text());		
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// funder Code
	if ($("#FunderID").val().trim() == "") {
		return "Please insert order code.";
	}
	
	// funder Type
	if ($("#FunderName").val().trim() == "") {
		return "Please insert order Type.";
	}
	
	// funder Name
	if ($("#FunderContact").val().trim() == "") {
		return "Please insert Customer Name.";
	}
	
	// funding Amount
	if ($("#FundingAmount").val().trim() == "") {
		return "Please insert total amount.";
	}
	
	// Card No
	if ($("#cardNo").val().trim() == "") {
		return "Please insert card no.";
	}
	
	// CVV No
	if ($("#cvvNo").val().trim() == "") {
		return "Please insert cvv no.";
	}
	
	return true;
}
