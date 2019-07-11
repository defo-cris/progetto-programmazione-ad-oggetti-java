package com.example.progetto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

class GetCsvUrlFromJsonUrl
{


    String getLink(String url, int numCsv) throws IOException, JSONException
    {
        GetJsonFromUrl jsonFromUrl = new GetJsonFromUrl();
        JSONObject json = jsonFromUrl.readJsonFromUrl(url);
        GetCsvLinkFromJson csvLinkFromJson = new GetCsvLinkFromJson();
        return csvLinkFromJson.getLink(json, numCsv);
    }

    class GetJsonFromUrl
    {
        private String readAll(Reader rd) throws IOException
        {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1)
            {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        JSONObject readJsonFromUrl(String url) throws IOException, JSONException
        {
            HttpURLConnection httpCon = (HttpURLConnection) new URL(url).openConnection();
            httpCon.addRequestProperty("User-Agent", "Mozilla/5.0");
            try (InputStream is = httpCon.getInputStream())
            {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                return new JSONObject(jsonText);
            }
        }
    }


    class GetCsvLinkFromJson
    {
        String getLink(JSONObject json, int number) throws JSONException
        {
            return (String) json.getJSONObject("result").getJSONArray("resources").getJSONObject(number).get("url");
        }
    }

}
