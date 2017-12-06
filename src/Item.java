import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Item {
public int itemid;
public String currentWbid;
public String storeName;
public String bidding_amount;
private String itemUrl; //url to buy item
public int timeRem;//we store the time remidng in seconds
public String itemName;
public URL imageUrl;
public String endDate;
public List<String> itemImages = new ArrayList<String>();	    
private final String baseUrl="https://www.shopgoodwill.com/Item/";

/*The item class takes a url from shopGoodWill.com and generates an item object 
that contains the name of the listing , timing remainding, location, seller, images
and more

*/

public Item(int id, int prc , int bids, String u,int tmr,String name,String URL) {

	 itemName=name;

}


public Item(String id) {

itemUrl=baseUrl+id;       ///contruct url
File localfile = new File(id+".html"); //we keep local copies of the hmtl so we can ask queeries offline



if(localfile.exists()==false) {// Here we download the html file for the given item n# 
    
	
	try {
		URL u=new URL(itemUrl);
		FileDownloader.Download(u, id+".html");
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		JOptionPane.showMessageDialog(null, "Opps something went wrong", "isuse", 1);
	}
	

}


///time to read the file to process
String htmlContents=""; //this string stores the html code in the file
 //id +.html is the file name 			    
 
File file = new File (id +".html");
try {
Scanner	sc = new Scanner(file); // just the old good scaner


while (sc.hasNextLine()) {

	
	htmlContents = htmlContents+sc.nextLine();



}// end of while 

}// end of try catch 
	
	
	 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	



///regex pattern matching 
String regex = "(<img src=)([^<]+)<\\/a>";



Pattern pattern = Pattern.compile("(?<=</b>)([^<]+)(?=</li>)"); //patern for getting general info
Matcher matcher = pattern.matcher(htmlContents);

//store matches here
List<String> listMatches = new ArrayList<String>();	    
List<String> images = new ArrayList<String>();	    

//end

while(matcher.find())
{
    listMatches.add(matcher.group(0));
}


	for (int i =0;i<listMatches.size();i++) {
String p = listMatches.get(i);
	//	System.out.println(p);
		 switch (i) {
         case 0:  bidding_amount=p;
         System.out.println("bidding increment: "+p);
	break;
         case 1:currentWbid=p;
         System.out.println("curren winning bid bidd : "+p);
	break;
	
         case 2:endDate=p; //End time and date
         System.out.println("End time and date : "+p);
	break;
	
         case 4:storeName=p; //sSELLER STORE NAME
         System.out.println("seller : "+p);
	break;
	
	
	
	}
	}


// match title 
	//listing tittle 
	  regex="(<h1 class=\"product-title\">)([^<]+)(<\\/h1>)";
	 
	  pattern = Pattern.compile(regex);
	   matcher = pattern.matcher(htmlContents);
	   matcher.find();
	   itemName = matcher.group(2);
	   System.out.println("item name "+ itemName);
  
 // System.out.println(itemName);


///pictures now
//images
  regex = "(<img src=)([^<]+)<\\/a>";
  pattern = Pattern.compile(regex);
  matcher = pattern.matcher(htmlContents);
  while(matcher.find())
   {
     images.add(matcher.group(0));
   }

int pk=0;
	for (String p1:images) {
	p1=p1.replaceAll("<img src=\"", "");
	p1=p1.replaceAll("</a>", "");
	p1=p1.replaceAll("\">" , "");
	
	if (pk>0&&pk<images.size()-2) {
		itemImages.add(p1);
System.out.println(p1);
}pk++;
	}
	














   //System.out.println("test");
}

}