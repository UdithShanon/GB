package com;

import model.FundingPosts;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Funding") 
public class FundingBodiesService {
	
	FundingPosts fundObj = new FundingPosts();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return fundObj.readItems();
		
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("Title") String title, 
							@FormParam("Content") String content, 
							@FormParam("postDate") String postdate, 
							@FormParam("postTime") String posttime) 
	{ 
			String output = fundObj.insert Item(title, content, postdate, posttime); 
			return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
			//Convert the input string to a JSON object 
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	
			//Read the values from the JSON object
			String pID = itemObject.get("postID").getAsString(); 
			String tlt = itemObject.get("title").getAsString(); 
			String cnt = itemObject.get("content").getAsString(); 
			String pd = itemObject.get("postDate").getAsString(); 
			String pt = itemObject.get("postTime").getAsString(); 
			
			String output = fundObj.updateItem(pID, tlt, cnt, pd, pt); 
	
			return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
			//Read the value from the element <itemID>
			String accountID = doc.select("postID").text(); 
	 
			String output = fundObj.deleteItem(accountID); 
	
			return output; 
	}

}
