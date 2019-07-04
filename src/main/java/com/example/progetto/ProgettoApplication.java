package com.example.progetto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProgettoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ProgettoApplication.class, args);

        String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-results-projects";
        try
        {
           GetCsvUrlFromJsonUrl csvUrlFromJsonUrl = new GetCsvUrlFromJsonUrl();
           String link = csvUrlFromJsonUrl.getLink(url);
           System.out.println(link);
        }
        catch (IOException e)
        {
            System.out.println("IOException");
        }
        catch (JSONException e)
        {
            System.out.println("JSONException");
        }


    }

}
