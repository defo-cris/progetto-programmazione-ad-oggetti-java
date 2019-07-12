package csvClasses;

import com.example.progetto.*;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class DataCsv
{
    private String[] dataNames = {"ID", "String", "String", "String", "Url", "String", "String", "String", "StringArraySemicolonSeparated", "StringArrayCommaSeparated", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "FloatArraySemicolonSeparated", "FloatArraySemicolonSeparated", "Url", "String", "String", "Url", "UrlArraySemicolonSeparated", "String", "String", "StringArraySemicolonSeparated", "StringArraySemicolonSeparated", "Currency", "Currency", "String", "String"};
    private String[] colNames = {"Nid", "OriginalId", "Name", "ProjectAcronym", "Visual", "ProjectDescription", "Results", "Coordinators", "Partners", "ProjectAddresses", "ProjectPostalCodes", "ProjectTowns", "ProjectCountryies", "ProjectLocationLatitude", "ProjectLocationLongitude", "LinkToAVideo", "TimeframeStart", "TimeframeEnd", "ProjectWebpage", "RelatedLinks", "EuBudgetMffHeading", "ProgrammeName", "FundingArea", "EcsPriorities", "EuBudgetContribution", "TotalProjectBudget", "Author", "Language"};
    private String[] dataType = {"java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "csvClasses.dataType.StringArray", "csvClasses.dataType.StringArray", "csvClasses.dataType.StringArray", "csvClasses.dataType.StringArray", "csvClasses.dataType.StringArray", "csvClasses.dataType.FloatArray", "csvClasses.dataType.FloatArray", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "csvClasses.dataType.StringArray", "java.lang.String", "java.lang.String", "csvClasses.dataType.StringArray", "csvClasses.dataType.StringArray", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String"};

    private String url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-results-projects";
    //url = "https://www.dati.gov.it/api/3/action/package_show?id=3c68b286-09fd-447a-b8e3-1b8430f70969";
    //url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=eu-cohesion-policy-historic-eu-payments-regionalised-and-modelled";
    //url = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=jrc-abcis-ap-dmpspc-2016";

    private Vector<Metadata> metadata;
    private Vector<DataCsvRow> csvData;

    private String[] firstLine;


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
            csvData = new Vector<>();

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

        }
        catch (IOException | JSONException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e)
        {
            System.out.println(e.getCause().toString() + e.toString());
            e.printStackTrace();
        }

    }

    public void createMetadata()
    {
        DataCsvRow pharmacyobj = new DataCsvRow();
        Class<?> pharmacy = pharmacyobj.getClass();

        int i = 0;
        for (Field field: pharmacy.getDeclaredFields())
        {
            try
            {
                metadata.add(new Metadata(field.getName(), firstLine[i], field.getType().toString()));
                i++;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
            }
        }
    }

}
