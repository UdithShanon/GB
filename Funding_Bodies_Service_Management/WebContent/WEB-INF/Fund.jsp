<%@page import="model.Funding_Posts"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
			<title>Order Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Fund.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><u><i><b>Funding Bodies Management - GadgetBadget</b></i></u></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Fund Details</b></legend>
					<form id="ORDER" name="ORDER" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Fund ID:</label>
						    <input type="hidden" id="orderID" name="orderID">
						    <input type="text" id="FunderID" class="form-control" name="orderCode">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Funders Name:</label>
						    <input type="text" id="FunderName" class="form-control" name="customerName">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Funder Contact Number:</label>
						    <input type="text" id="FunderContact" class="form-control" name="customerContact">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Funding Amount:</label>
						    <input type="text" id="FundingAmount" class="form-control" name="totalAmount">						    
						</div>
						 
						<div class="row mb-4">
						    <div class="col">
						      <div class="form-outline">
						        <label class="form-label" for="form6Example1" class="col-sm-2 col-form-label col-form-label-sm">Credit/Debit Card No:</label>
						        <input type="number" id="cardNo" class="form-control" name="cardNo">						        
						      </div>
						    </div>
						    <div class="col">
						      <div class="form-outline">
								<label class="form-label" for="form6Example2" class="col-sm-2 col-form-label col-form-label-sm">CVV No:</label>
						        <input type="number" id="cvvNo" class="form-control" name="cvvNo" aria-describedby="passwordHelpInline">
						        <small id="passwordHelpInline" class="text-muted">
      								Must be 3 digit number.
    							</small>
						      </div>
						    </div>
						  </div>						
						<br> 
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>	
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						
					</form>
				
							
			</fieldset>
			
			<br> 
			
			<div class="container" id="OrderGrid">
				<fieldset>
					<legend><b>View Fund Details</b></legend>
					<form method="post" action="Fund.jsp" class="table table-striped">
						<%
						Funding_Posts viewOrder = new Funding_Posts();
							out.print(viewOrder.readFund());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>