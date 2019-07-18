package com.example.progetto.csvClasses;


import com.example.progetto.Spring.DataCsvRow;
import com.example.progetto.Spring.DataCsvRowServices;
import com.example.progetto.csvClasses.csvRetrieve.CsvSplitter;
import com.example.progetto.csvClasses.csvRetrieve.CsvValidator;
import com.example.progetto.csvClasses.csvRetrieve.GetCsvDataFromUrl;
import com.example.progetto.csvClasses.csvRetrieve.GetCsvUrlFromJsonUrl;
import com.example.progetto.csvClasses.dataType.Metadata;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/* TODO javadoc ok */

/**
 * Class used to read, validate and store all the row of the csv in a vector; and from every field of the row with the
 * method createMetadata we retrieve the class type of every element.
 */
@SuppressWarnings("WeakerAccess")
public class DataCsv
{
    /**
     * contains all the validation type for every column of the csv
     */
    public static final String[] dataNames = {"ID", "String", "String", "String", "Url", "String", "String", "String", "StringArraySemicolonSeparated", "StringArrayCommaSeparated", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "CountryArraySemicolonSeparated", "FloatArraySemicolonSeparated", "FloatArraySemicolonSeparated", "Url", "String", "String", "Url", "UrlArraySemicolonSeparated", "String", "String", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "Currency", "Currency", "String", "String"};
    /**
     * contain the name of the fields in {@link DataCsvRow} with the first letter uppercase
     */
    public static final String[] colNames = {"Nid", "OriginalId", "Name", "ProjectAcronym", "Visual", "ProjectDescription", "Results", "Coordinators", "Partners", "ProjectAddresses", "ProjectPostalCodes", "ProjectTowns", "ProjectCountryies", "ProjectLocationLatitude", "ProjectLocationLongitude", "LinkToAVideo", "TimeframeStart", "TimeframeEnd", "ProjectWebpage", "RelatedLinks", "EuBudgetMffHeading", "ProgrammeName", "FundingArea", "EcsPriorities", "EuBudgetContribution", "TotalProjectBudget", "Author", "Language"};
    /**
     * contain the type of the fields in {@link DataCsvRow}
     */
    public static final String[] dataType = {"java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "com.example.progetto.csvClasses.dataType.ObjArray", "java.lang.String", "java.lang.String", "com.example.progetto.csvClasses.dataType.ObjArray", "com.example.progetto.csvClasses.dataType.ObjArray", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String"};

    /**
     * contain the link of the json used for this project
     */
    private final String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-results-projects";

    private Vector<Metadata> metadata;
    private Vector<DataCsvRow> csvData;

    private String[] firstLine;

    /**
     * constructor of the class
     */
    public DataCsv()
    {
        csvData = new Vector<>();
        metadata = new Vector<>();
    }

    /**
     * In this method form the json url we retrieve the csv url, we read it and download in ram it. From the Csv data we
     * read and split the first line, and for every row of the Csv we read, validate and set the element of every
     * column. this method use {@link GetCsvUrlFromJsonUrl}, {@link GetCsvDataFromUrl} and {@link CsvSplitter}.
     */
    public void readAndStore()
    {

        try
        {
            GetCsvUrlFromJsonUrl csvUrlFromJsonUrl = new GetCsvUrlFromJsonUrl();
            String link = csvUrlFromJsonUrl.getLink(url, 0);

            GetCsvDataFromUrl csv = new GetCsvDataFromUrl(link);

            CsvSplitter splitter = new CsvSplitter(csv);

            //splitter.guessDelimiter();
            splitter.setDelimiter(",");


            firstLine = splitter.splitFirstLine();

            String[] line;


            while ((line = splitter.splitLine()) != null)
            {
                DataCsvRow row = new DataCsvRow();

                for (int i = 0; i < dataNames.length; i++)
                {
                    Method validateMethod = CsvValidator.class.getMethod("validate" + dataNames[i], String.class);
                    Method setterMethod = row.getClass().getMethod("set" + colNames[i], Class.forName(dataType[i]));
                    Object validatedData = validateMethod.invoke(CsvValidator.class, line[i]);
                    setterMethod.invoke(row, validatedData);
                }
                csvData.add(row);

            }

            System.out.println("csv loaded!");

            createMetadata();

            System.out.println("metadata ok!");

        }
        catch (IOException | JSONException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e)
        {
            System.out.println(e.toString());
        }

    }

    /**
     * create the metadata of our csv
     */
    private void createMetadata()
    {
        DataCsvRow dataCsvRow = new DataCsvRow();
        Class<?> data = dataCsvRow.getClass();

        int i = 0;
        for (Field field : data.getDeclaredFields())
        {
            try
            {
                metadata.add(new Metadata(field.getName(), firstLine[i], field.getType().toString()));
                i++;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * In this method we set the data and the metadata in the {@link DataCsvRowServices}, spring will use that data to
     * answer the get/post request
     */
    public void setServicesData()
    {
        DataCsvRowServices.setCsvData(csvData);
        DataCsvRowServices.setMetadata(metadata);
    }

}
