



package my.apps;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import share.utils.*;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

 
public class TaxiDriverLocationUpdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String lat = "";
		String lon = "";
		String id = "";
		String action = "";
		
		String usage = "USAGE: java -jar TaxiDriverLocationUpdate\n";
		usage += "a - [action]\n";
		usage += "i - [id]\n";
		usage += "x - [latitude]\n";
		usage += "y - [longtitude]\n";
		
		
		OptionParser parser = new OptionParser( "a:i:x:y:?" );

		OptionSet options = parser.parse( args );

		if (options.has("?"))
		{
			System.out.println(usage);
			System.exit(0);
		}
		
		
		if (options.has("a"))
		{
			action = StringUtils.getTrimWhiteSpaces((String) options.valueOf("a")); 
		}
		
		if (options.has("i"))
		{
			id = StringUtils.getTrimWhiteSpaces((String) options.valueOf("i")); 
		}
		
		if (options.has("x"))
		{
			lat = StringUtils.getTrimWhiteSpaces((String) options.valueOf("x")); 
		}
		
		if (options.has("y"))
		{
			lon = StringUtils.getTrimWhiteSpaces((String) options.valueOf("y")); 
		}
		
		System.out.println(action);
		System.out.println(id);
		System.out.println(lat);
		System.out.println(lon);
		
		try 
		{
			String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("set", "UTF-8");
		    data += "&" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
		    data += "&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8");
		    data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
		    
			URL url = new URL("http://localhost:8080/taxibest/TaxiCenterController");
			URLConnection conn =  url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			
			String query = "action=set&lat=" + lat + "&lon=" + lon + "&id=" + id;
			PrintWriter pw = new PrintWriter(conn.getOutputStream());
			pw.println(query);
			pw.close();
			
			// get the input from the request
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String res = in.readLine();
			in.close();
			
			System.out.println("Done... Updating....");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

}
