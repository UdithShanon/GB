package model;

import java.sql.*;

public class Payment {
	private Connection connect()
	{
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide Correct Database Details
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public String createPayment(String ProductCode, String Status, String Date, String Amount) {
		String output="";
		
		try {
			Connection con=connect();
			
			if(con==null)
			{
				return "Error";
			}
			String  query= "insert into Payment('PaymentID','ProductCode','Status','Date','Amount')"
							+
							" values(?,?,?,?) ";
			PreparedStatement ps=con.prepareStatement(query);
			
			
			ps.setString(1, ProductCode);
			ps.setString(2, Status);
			ps.setString(3, Date.toString());
			ps.setString(4, Amount.toString());
			
			ps.execute();
			con.close();
			
			output="Insert Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			output="Error while inserting the Item";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readPayment() {
		String output="";
		
		try {
			Connection con=connect();
			
			if(con==null)
			{
				return "Error";
			}
			output="<table><tr><th>Payment Code</th><th>Product ID</th><th>Amount</th><th>Date</th><th>Time</th><th>Status</th><th>Update</th><th>Remove</th></tr>";
			String query="select * from Payment";
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			while(rs.next())
			{
				String Payment_ID= Integer.toString(rs.getInt("PaymentID"));
				String Payment_code= rs.getString("PaymentCode");
				String Product_ID = rs.getString("ProductID");
				String Amount = rs.getString("Amount");// How to Get Date as A String - Doubt
				String Time = rs.getString("Time");
				String Date = rs.getString("Date");
				String Status = rs.getString("Status");// How to Get Date as A String - Doubt
				
				output +="<tr><td>"+Payment_ID+"</td>";
				output +="<tr><td>"+Payment_code+"</td>";
				output +="<tr><td>"+Product_ID+"</td>";
				output +="<tr><td>"+Amount+"</td>";
				output +="<tr><td>"+Time+"</td>";
				output +="<tr><td>"+Date+"</td>";
				output +="<tr><td>"+Status+"</td>";
				
				output +="<td><input name=\\\"btnUpdate\\\" type=\\\"button\\\" \r\n" + 
						" value=\\\"Update\\\" class=\\\"btn btn-secondary\\\"></td>\"\r\n" + 
						" + \"<td><form method=\\\"post\\\" action=\\\"posts.jsp\\\">\"\r\n" + 
						" + \"<input name=\\\"btnRemove\\\" type=\\\"submit\\\" value=\\\"Remove\\\" \r\n" + 
						" class=\\\"btn btn-danger\\\">\"\r\n" + 
						" + \"<input name=\\\"id\\\" type=\\\"hidden\\\" value=\\\"\" + id\r\n" + 
						" + \"\\\">\" + \"</form></td></tr>";
				
			}
			con.close();
			output+="</tabel>";
		} catch (Exception e) {
			// TODO: handle exception
			output="Error while reading the Posts";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updatePost(String ID,String ProductCode, String Amount, String Date,String Status) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 //LocalDate date= LocalDate.now();
	 //LocalTime time= LocalTime.now();
	 String query = "UPDATE products SET PaymentID=?,ProductCode=?,Amount=?,Date=?,Status=? WHERE id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values 
	 preparedStmt.setString(1, ProductCode); 
	 preparedStmt.setString(2, Amount); 
	 preparedStmt.setString(3, Date); 
	 preparedStmt.setString(4, Status); 
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
	
	
	
	public String deletePayment(String ID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from products where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID)); 
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
