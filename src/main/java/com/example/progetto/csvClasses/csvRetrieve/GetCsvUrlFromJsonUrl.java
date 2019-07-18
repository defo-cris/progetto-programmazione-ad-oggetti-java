package com.example.progetto.csvClasses.csvRetrieve;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/* TODO javadoc ok */

/**
 * class that from the url of the json retrieve the link of the url of csv
 */
public class GetCsvUrlFromJsonUrl
{


    /**
     * method used to get the link of the csv from the json url
     *
     * @param url param where we pass the link of the Json
     * @param numCsv param used to indicate the number of the element in the Json
     * @return the link of the csv to download from the Json
     * @throws IOException exception that occur in the case of an I/O error
     * @throws JSONException exception that occur in the case of an Json error
     */
    public String getLink(String url, int numCsv) throws IOException, JSONException
    {
        GetJsonFromUrl jsonFromUrl = new GetJsonFromUrl();
        JSONObject json = jsonFromUrl.readJsonFromUrl(url);
        GetCsvLinkFromJson csvLinkFromJson = new GetCsvLinkFromJson();
        return csvLinkFromJson.getLink(json, numCsv);
    }

    /**
     * class that from the url format the page how a string
     */
    class GetJsonFromUrl
    {
        /**
         * method used to read all the character from a {@link java.io.Reader}
         *
         * @param the reader
         * @return the Json in the string format
         * @throws IOException 
         */
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

        /**
         * method to get the {@link JSONObject} from the url
         *
         * @param url contain the Json url
         * @return the Json string with a casting of a JSONObject
         * @throws IOException in case the reading from url fail
         * @throws JSONException in case the parsing of the json fail
         */
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


    /**
     * class used to retrieve the link of the csv from the Json 
     */
    class GetCsvLinkFromJson
    {
        /**
         * method used to get the link of the csv from the Json
         *
         * @param json JSONObject of the Json casting of the json url
         * @param number number of the element of the Json
         * @return the string of the link of the csv
         * @throws JSONException in the case of an error in the Json
         */
        String getLink(JSONObject json, int number) throws JSONException
        {
            return (String) json.getJSONObject("result").getJSONArray("resources").getJSONObject(number).get("url");
        }
    }

}
