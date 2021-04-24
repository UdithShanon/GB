package model;

import java.sql.*;

public class FundingPosts {
	
	//A common method to connect to the DB
	private Connection connect() 
		{ 
	
				Connection con = null; 
	
				try
				{ 
					//Class.forName("com.mysql.jdbc.Driver"); 
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fundingdb","root","");
					
					//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				} 
	
				return con; 
		}
		
	public String insertItem(String tlt, String cnt, String pd, String pt) 
	{ 

		String output = ""; 

		try
		{ 

			Connection con = connect(); 

			if (con == null) 

			{return "Error while connecting to the database for inserting."; } 

			// create a prepared statement
			String query = " insert into funds (`postID`,`title`,`content`,`postDate`,`postTime`)"
						+ " values (?, ?, ?, ?, ?)"; 

			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, tlt); 
			 preparedStmt.setString(3, cnt); 
			 preparedStmt.setString(4, pd); 
			 preparedStmt.setString(5, pt); 

			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
			 
		} 
		return output; 
	}
	
	public String readItems() 
	 { 
	 
			String output = ""; 
	 
			try
			{ 
	 
				Connection con = connect(); 
	 
				if (con == null) 
				{return "Error while connecting to the database for reading."; } 
	 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>postID</th><th>Title</th>" +
						"<th>Content</th>" +
						"<th>Post Date</th>" + 
						"<th>Post Time</th></tr>"; 
	 
				String query = "select * from funds"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
	 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
						String postID = Integer.toString(rs.getInt("postID")); 
						String tlt = rs.getString("title"); 
						String cnt = rs.getString("content"); 
						String pDate = rs.getString("postDate"); 
						String pTime = rs.getString("postTime"); 
	 
						// Add into the html table
						output += "<tr><td>" + postID + "</td>"; 
						output += "<td>" + tlt + "</td>"; 
						output += "<td>" + cnt + "</td>";
						output += "<td>" + pDate + "</td>";
						output += "<td>" + pDate + "</td></tr>"; 
				} 
	 
				con.close(); 
	 
				// Complete the html table
				output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 	output = "Error while reading the items."; 
		 	System.err.println(e.getMessage()); 
	 } 
	 
			return output; 
	 } 
	
	public String updateItem(String ID, String tlt, String cnt, String pd, String pt)
	{ 
		 String output = ""; 
		 
		 try
		 { 
		 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE funds SET title=?,content=?,postDate=?,postTime=? WHERE postID=?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, tlt); 
			 	preparedStmt.setString(2, cnt); 
			 	preparedStmt.setString(3, pd);
			 	preparedStmt.setString(4, pt); 
			 	preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
			 	// execute the statement
			 	preparedStmt.execute(); 
			 	con.close(); 
		 
			 	output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 	output = "Error while updating the item."; 
			 	System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}
	
	public String deleteItem(String postID)
	{ 
	 	String output = ""; 
	 
	 	try
	 	{ 
	 
	 			Connection con = connect(); 
	 
	 			if (con == null) 
	 			{return "Error while connecting to the database for deleting."; } 
	 
	 			// create a prepared statement
	 			String query = "delete from funds where postID=?"; 
	 			
	 			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 			// binding values
	 			preparedStmt.setInt(1, Integer.parseInt(postID)); 
	 
	 			// execute the statement
	 			preparedStmt.execute(); 
	 			con.close(); 
	 
	 			output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 		output = "Error while deleting the item."; 
		 		System.err.println(e.getMessage()); 
	 } 
	 return output; 
}

}
