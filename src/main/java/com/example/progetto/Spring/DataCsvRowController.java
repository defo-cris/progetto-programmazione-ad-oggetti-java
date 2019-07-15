package com.example.progetto.Spring;

import com.example.progetto.csvClasses.dataType.Metadata;
import com.fasterxml.jackson.annotation.JsonValue;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;


import java.util.Vector;

@RestController
public class DataCsvRowController
{
    private final DataCsvRowServices dataServices;

    public DataCsvRowController(DataCsvRowServices dataServices)
    {
        this.dataServices = dataServices;
    }

    @GetMapping("/data")
    public Vector<DataCsvRow> retrieveData()
    {
        return dataServices.getCsvData();
    }

    @GetMapping("/metadata")
    public Vector<Metadata> retrieveMetadata()
    {
        return dataServices.getMetadata();
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
    public Vector<DataCsvRow> filter(@RequestBody String param)
    {
        try
        {
            JSONObject obj = new JSONObject(param);
            FilterParameter filter = new FilterParameter();
            filter.readFields(obj);
            return DataCsvRowServices.filter(filter);
        }
        catch (JSONException e)
        {
            return null;
        }

    }

}
