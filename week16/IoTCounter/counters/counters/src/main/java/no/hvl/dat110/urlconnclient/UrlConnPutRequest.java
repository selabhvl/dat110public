package no.hvl.dat110.urlconnclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import no.hvl.dat110.cloudservice.Counters;

public class UrlConnPutRequest  {
	
	private static final String URL = "http://localhost:8080/counters";
	
	public static void main(String[] args) throws Exception {

		Counters counters = new Counters(3, 7);
		
		String countersjson = counters.toJson();
		
		URL urlobj = new URL(URL);
		
		HttpURLConnection con = (HttpURLConnection) urlobj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-type","application/json");
		
		con.setDoOutput(true);
		PrintWriter out = new PrintWriter(con.getOutputStream());
		out.print(countersjson);
		out.flush();
		out.close();
		
		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) { // status code 200

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}

			in.close();
		}

		con.disconnect();
	}

}