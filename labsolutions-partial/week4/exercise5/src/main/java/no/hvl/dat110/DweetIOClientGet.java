package no.hvl.dat110;

import okhttp3.*;

public class DweetIOClientGet {

    public static void main(String[] args) throws Exception {


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url("https://dweet.io/get/latest/dweet/for/dat110-sensor")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        };

    }

}
