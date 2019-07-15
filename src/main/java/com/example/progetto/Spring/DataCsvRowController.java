package com.example.progetto.Spring;

import com.example.progetto.csvClasses.dataType.Metadata;
import com.fasterxml.jackson.annotation.JsonValue;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

@RestController
public class DataCsvRowController
{

    public DataCsvRowController()
    {
    }

    @GetMapping("/data")
    public Vector<DataCsvRow> retrieveData()
    {
        return DataCsvRowServices.getCsvData();
    }

    @GetMapping("/metadata")
    public Vector<Metadata> retrieveMetadata()
    {
        return DataCsvRowServices.getMetadata();
    }

    @GetMapping("/test")
    public String retrieveTest()
    {
        return "test";
    }

    /**
     * Gives stats based on the class {@link NumberStats}. <strong>Note:</strong>
     * the allowed fields are latitude and longitude.
     *
     * @param colName allowed: latitude or longitude
     * @return average, minimum, maximum, standard deviation and sum
     */
    @GetMapping("/stats/{colName}")
    public NumberStats stats(@PathVariable String colName)
    {
        return DataCsvRowServices.stats(colName);
    }


    @GetMapping("/data/{colName}")
    public Vector<Object> retrieveDataColumn(@PathVariable String colName)
    {
        return DataCsvRowServices.retrieveColumn(colName);
    }


    @GetMapping("/count/{fieldName}")
    public String count(@PathVariable String fieldName, @RequestParam(value = "value") String value)
    {
        FilterParameter filter = new FilterParameter(fieldName, "==", value);
        int count = DataCsvRowServices.filter(filter).size();
        return "{ \"count\": " + count + "}";
    }


    @PostMapping(value = "/filter")
    public Vector<Vector<DataCsvRow>> filter(@RequestBody String param)
    {
        Vector<Vector<DataCsvRow>> finalData = new Vector<>();
        JSONObject obj = null;
        FilterParameter filter = new FilterParameter();
        try
        {
            obj = new JSONObject(param);
        }
        catch (JSONException e)
        {
            /* TODO inserire errore */
            return null;
        }

        try
        {
            filter.readFields(obj);
            finalData.add(DataCsvRowServices.filter(filter));
            return finalData;
        }
        catch (JSONException e)
        {
            try
            {

                Iterator iterator = obj.keys();
                while (iterator.hasNext())
                {
                    Vector<Vector<DataCsvRow>> partialFilteredData = new Vector<>();
                    String operator = iterator.next().toString();
                    JSONArray filters = (JSONArray) obj.get(operator);

                    for (int i = 0; i < filters.length(); i++)
                    {
                        JSONObject objin = filters.getJSONObject(i);
                        filter.readFields(objin);
                        partialFilteredData.add(DataCsvRowServices.filter(filter));
                    }

                    if (operator.equals("$or"))
                    {
                        Vector<DataCsvRow> tmp = new Vector<>();
                        for (Vector<DataCsvRow> v: partialFilteredData)
                        {
                            tmp = DataCsvRowServices.or(tmp, v);
                        }
                        finalData.add(tmp);
                    }
                    else if (operator.equals("$and"))
                    {
                        Vector<DataCsvRow> tmp = DataCsvRowServices.getCsvData();
                        for (Vector<DataCsvRow> v: partialFilteredData)
                        {
                            tmp = DataCsvRowServices.and(tmp, v);
                        }
                        finalData.add(tmp);
                    }

                }
                return finalData;
            }
            catch (JSONException ex)
            {
                /* TODO da sistemare*/
                System.out.println("wrooooong");
            }

        }
        return null;
    }

}
