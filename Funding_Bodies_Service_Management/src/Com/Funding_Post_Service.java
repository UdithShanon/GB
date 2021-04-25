package Com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Funding_Posts;

@Path("/FundingPosts")
public class Funding_Post_Service {
	Funding_Posts funding_obj=new Funding_Posts();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPosts()
	{
		return funding_obj.read_Post();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPost(
			@FormParam("posttitle") String postTitle,
			@FormParam("postContent") String postcontent) {
		String output= funding_obj.createPost(postTitle, postcontent); //date issue
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
		
		String ID = postObj.get("itemID").getAsString();
		String title = postObj.get("title").getAsString();
		String content = postObj.get("content").getAsString();

		
		
		String output=funding_obj.updatePost(ID, title, content);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
		Document doc= Jsoup.parse(itemData,"",Parser.xmlParser());
		
		String itemID=doc.select("ItemID").text();
		
		String output=funding_obj.deletePost(itemID);
		return output;
	}
}
