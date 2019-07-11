package com.example.progetto;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.zip.DataFormatException;
import java.lang.reflect.Method;

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
            String link = csvUrlFromJsonUrl.getLink(url, 0);
            System.out.println(link);

            GetCsvDataFromUrl csv = new GetCsvDataFromUrl(link);

            CsvSplitter splitter = new CsvSplitter(csv);

            //splitter.guessDelimiter();
            splitter.setDelimiter(",");

            String[] dataNames = {"ID", "String", "String", "String", "Url", "String", "String", "String", "StringArraySemicolonSeparated", "StringArrayCommaSeparated", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "FloatArraySemicolonSeparated", "FloatArraySemicolonSeparated", "Url", "String", "String", "Url", "UrlArraySemicolonSeparated", "String", "String", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "Currency", "Currency", "String", "String"};
            String[] colNames = {"Nid", "OriginalId", "Name", "ProjectAcronym", "Visual", "ProjectDescription", "Results", "Coordinators", "Partners", "ProjectAddresses", "ProjectPostalCodes", "ProjectTowns", "ProjectCountryies", "ProjectLocationLatitude", "ProjectLocationLongitude", "LinkToAVideo", "TimeframeStart", "TimeframeEnd", "ProjectWebpage", "RelatedLinks", "EuBudgetMffHeading", "ProgrammeName", "FundingArea", "EcsPriorities", "EuBudgetContribution", "TotalProjectBudget", "Author", "Language"};
            String[] dataType = {"java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "com.example.progetto.StringArray", "com.example.progetto.StringArray", "com.example.progetto.StringArray", "com.example.progetto.StringArray", "com.example.progetto.StringArray", "com.example.progetto.FloatArray", "com.example.progetto.FloatArray", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "com.example.progetto.StringArray", "java.lang.String", "java.lang.String", "com.example.progetto.StringArray", "com.example.progetto.StringArray", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String"};
            String[] line;

            ArrayList<DataCsvRow> csvData = new ArrayList<>();

            while ((line = splitter.splitLine()) != null)
            {
                DataCsvRow row = new DataCsvRow();

                for (int i = 0; i < dataNames.length; i++)
                {
                    Method validateMethod = CsvValidator.class.getMethod("validate" + dataNames[i], String.class);
                    Method setterMethod = row.getClass().getMethod("set" + colNames[i], Class.forName(dataType[i]));
                    setterMethod.invoke(row, validateMethod.invoke(CsvValidator.class, line[i]));
                }
                csvData.add(row);
            }

            PrintClass.printArrayList(csvData);

        }
        catch (IOException e)
        {
            System.out.println("IOException -> " + e);
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            System.out.println("JSONException -> " + e);
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            System.out.println("IllegalAccessException -> " + e);
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            System.out.println("InvocationTargetException -> " + e);
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            System.out.println("NoSuchMethodException -> " + e);
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException -> " + e);
            e.printStackTrace();
        }



        /*catch (DataFormatException e)
        {
            System.out.println("DataFormatException -> " + e);
        }*/


    }

}
