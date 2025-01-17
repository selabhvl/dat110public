package no.hvl.dat110.httpsappclient;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class SimpleHTTPSClient {

	public static void main(String[] args) throws Exception {
		
		System.out.print("URL:> ");

		Scanner scanner = new Scanner(System.in);
		String url = scanner.nextLine();

		URL urlobj = new URL(url);

		HttpsURLConnection con = (HttpsURLConnection) urlobj.openConnection();

		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) { // status code 200

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}

			in.close();
		}
		
		scanner.close();
		
		con.disconnect();
	}

}