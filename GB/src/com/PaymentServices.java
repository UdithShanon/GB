package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Payment;

@Path("/Payment")
public class PaymentServices {
	Payment Payment_obj=new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPosts()
	{
		return Payment_obj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPost(
			
			@FormParam("Product Code") String ProductCode,
			@FormParam("Status") String Status,
			@FormParam("Date") String Date,
			@FormParam("Amount") String Amount
			) {
		String output= Payment_obj.createPayment(ProductCode, Status, Date, Amount);
				return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String postData)
	{
		//Convert the input string to a JSON object
		JsonObject postObj = new JsonParser().parse(postData).getAsJsonObject();
		
		String PaymentID = postObj.get("PaymentID").getAsString();
		String ProductCode = postObj.get("ProductCode").getAsString();
		String Status = postObj.get("Status").getAsString();
		String Date = postObj.get("Date").getAsString();
		String Amount = postObj.get("Amount").getAsString();
		
		String output=Payment_obj.updatePost(PaymentID, ProductCode, Status, Date,Amount);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
		Document doc= Jsoup.parse(itemData,"",Parser.xmlParser());
		
		String itemID=doc.select("PaymentID").text();
		
		String output=Payment_obj.deletePayment(itemID);
		return output;
	}
}
