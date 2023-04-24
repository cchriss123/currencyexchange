package org.example.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ExchangeRateProvider {

    public static double getExchangeRate(String fromCode, String toCode) throws IOException {
        
        String apiKey = "nk74nn32lem10m8cl4tr808i3s7mm5ro35566liuo28ulltbn0b19";
        String urlString = "https://anyapi.io/api/v1/exchange/rates?base=" + fromCode.toUpperCase() + "&apiKey=" + apiKey;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject rates = jsonObject.getJSONObject("rates");
            //System.out.println(rates.toString());
            double rate = rates.getDouble(toCode.toUpperCase());
            return 1.0 / rate;
        } 
        else {
            System.out.println("Error: Unable to fetch data from the API. Response code: " + responseCode);
            return 0;
        }
    }

}
