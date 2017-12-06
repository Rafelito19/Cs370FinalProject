import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	
	public static String getPlainText(String html) {
	    String htmlBody = html.replaceAll("<hr>", ""); // one off for horizontal rule lines
	    String plainTextBody = htmlBody.replaceAll("<[^<>]+>([^<>]*)<[^<>]+>", "$1");
	    plainTextBody = plainTextBody.replaceAll("<br ?/>", "");
	    return plainTextBody;
	}


		
		public static void test() {
		URL url;

	
	String out="";
	
	String htmlpg="" ; //we store the html here
	
	
	try {
		url = new URL("https://www.shopgoodwill.com/Item/46382050");
//		url = new URL ("https://www.shopgoodwill.com/Item/46339203");
		FileDownloader.Download(url, "good.html");
		

		File file =
			      new File("good.html");			    Scanner sc = new Scanner(file);
			    	boolean tFlag = false;
			    while (sc.hasNextLine()) {
			    //tmlpg= htmlpg +
			/*
			     out= sc.next().replaceAll("\\<.*","");
			     out =out.replaceAll("class","");
			     out = out.replaceAll("=","");
			     
			     if (out.equals("")|out.equals("0"))
			     
			     {tFlag=false;          
			     
			     System.out.println(title);
			    //itle="";
			     }
			     
			     
			     if (out.equals("\"title\">"))
			     {
			    	 tFlag=true;
			    	 continue;
			     }
			     
			      if (tFlag) {
			    	  title = title+" "+out;
			    	  
			    	  
			      }
			     
			  */ 
			    	
			    out =out+sc.nextLine();
			    
			    
			   
			    
			    }
			    
			    
			    ///regex pattern matching 
			    String regex = "(<img src=)([^<]+)<\\/a>";
			    
			    
			    String string = out;
		      Pattern pattern = Pattern.compile("(?<=</b>)([^<]+)(?=</li>)");
		
		        
		        Matcher matcher = pattern.matcher(string);
			
			//store matches here
			    List<String> listMatches = new ArrayList<String>();	    
				
			    List<String> images = new ArrayList<String>();	    
			    
			    String title ="";
			    //end
			    while(matcher.find())
		        {
		            listMatches.add(matcher.group(0));
		        }

			    
			    	for (String p:listMatches)
		   System.out.println(p);
		        
		//ystem.out.println(out);
			    
			    	//images
			    	   pattern = Pattern.compile(regex);
			    	   matcher = pattern.matcher(string);
			    	   while(matcher.find())
				        {
				          images.add(matcher.group(0));
				        }
			    	

				    	for (String p:images) {
				    	p=p.replaceAll("<img src=\"", "");
				    	p=p.replaceAll("</a>", "");
				    	p=p.replaceAll("\">" , "");
				    	
				    	
			   System.out.println(p);
				    	}
			    	
			    //listing tittle 
				  regex="(<h1 class=\"product-title\">)([^<]+)(<\\/h1>)";
				 
				  pattern = Pattern.compile(regex);
		    	   matcher = pattern.matcher(string);
		    	   matcher.find();
		    	   title = matcher.group(2);
			    
			    System.out.println(title);
			    
			    
			    
			    //                 "(?<=<a href=\"/peoria\">)([^<]+)(?=</a>))"
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			 
			    
			    
			   
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    Item test =new Item("46382050");	     
			    


			    
			    
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	System.out.println(htmlpg);
	

	}
	
	
}
