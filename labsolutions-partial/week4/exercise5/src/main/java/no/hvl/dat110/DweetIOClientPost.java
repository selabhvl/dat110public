package no.hvl.dat110;

import okhttp3.*;

public class DweetIOClientPost {

	public static void main(String[] args) throws Exception {

		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create("{\n        \"Temperature\": 28\n}",mediaType);
		Request request = new Request.Builder()
				.url("https://dweet.io/dweet/for/dat110-sensor")
				.method("POST", body)
				.addHeader("Content-Type", "application/json")
				.build();

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		};
	}

}