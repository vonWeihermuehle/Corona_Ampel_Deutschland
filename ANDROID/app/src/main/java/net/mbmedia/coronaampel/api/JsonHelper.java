package net.mbmedia.coronaampel.api;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class JsonHelper {


    private static final String encoding = "UTF-8";

    public static String getJSON(String string_url, HashMap<String, String> postDataParams) {

        URL url = null;
        try {
            url = new URL(string_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.getOutputStream().write(getPostDataString(postDataParams));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }


        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] getPostDataString(HashMap<String, String> postDataParams) throws UnsupportedEncodingException {

        StringBuilder postData = new StringBuilder();
        for(Map.Entry<String, String> entry : postDataParams.entrySet()){
            if(postData.length() != 0){
                postData.append('&');
            }

            postData.append(URLEncoder.encode(entry.getKey(), encoding));
            postData.append('=');
            postData.append(URLEncoder.encode(entry.getValue(), encoding));
        }
        return postData.toString().getBytes(encoding);
    }

}
