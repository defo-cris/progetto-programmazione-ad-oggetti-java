package com.example.progetto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class GetCsvUrlFromJsonUrl
{


    String getLink(String url) throws IOException, JSONException
    {
        GetJsonFromUrl jsonFromUrl = new GetJsonFromUrl();
        JSONObject json = jsonFromUrl.readJsonFromUrl(url);
        GetCsvLinkFromJson csvLinkFromJson = new GetCsvLinkFromJson();
        return csvLinkFromJson.getLink(json);
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

        public JSONObject readJsonFromUrl(String url) throws IOException, JSONException
        {
            InputStream is = new URL(url).openStream();
            try
            {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                return new JSONObject(jsonText);
            }
            finally
            {
                is.close();
            }
        }
    }


    class GetCsvLinkFromJson
    {

        String getLink(JSONObject json) throws JSONException
        {
            String link = (String) json.getJSONObject("result")
                    .getJSONArray("resources").getJSONObject(0).get("url");
            return link;
        }


    }

}
