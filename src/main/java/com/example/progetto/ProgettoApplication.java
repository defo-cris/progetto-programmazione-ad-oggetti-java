package com.example.progetto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.DataFormatException;

@SpringBootApplication
public class ProgettoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ProgettoApplication.class, args);

        String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-results-projects";
        //url = "https://www.dati.gov.it/api/3/action/package_show?id=3c68b286-09fd-447a-b8e3-1b8430f70969";
        //url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-cohesion-policy-historic-eu-payments-regionalised-and-modelled";
        //url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=jrc-abcis-ap-dmpspc-2016";

        try
        {
            GetCsvUrlFromJsonUrl csvUrlFromJsonUrl = new GetCsvUrlFromJsonUrl();
            String link = csvUrlFromJsonUrl.getLink(url);
            System.out.println(link);

            GetCsvDataFromUrl csv = new GetCsvDataFromUrl(link);

            String firstline = csv.getFirstLine();
            //System.out.println(firstline);

            String line = csv.getLine();

            String[] commonSeparator = {";", ",", "|", "^"};

            String sep = "";

            String[] columns = {};
            String[] data = {};

            for(String s: commonSeparator)
            {
                columns = firstline.split(s);

                //PrintStringArray.print(columns);

                data = line.split(s + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                //PrintStringArray.print(data);

                if ((columns.length == data.length) && (columns.length != 1))
                {
                    sep = s;
                    break;
                }
            }

            if (sep.equals(""))
            {
                throw new DataFormatException("impossibile parsare il csv");
            }

            for (int i = 0; i < data.length; i++)
            {
                System.out.println(columns[i] + " -> " + data[i]);
            }








            /*
            try
            {
                while (true)
                {
                    System.out.println(csv.getLine());
                }
            }
            catch (EOFException e)
            {
                System.out.println("end of file");
            }
            */


        }
        catch (IOException e)
        {
            System.out.println("IOException -> " + e);
        }
        catch (JSONException e)
        {
            System.out.println("JSONException -> " + e);
        }
        catch (DataFormatException e)
        {
            System.out.println("DataFormatException -> " + e);
        }


    }

}
