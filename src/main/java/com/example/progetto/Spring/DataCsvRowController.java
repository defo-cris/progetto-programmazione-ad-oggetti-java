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
        return DataCsvRowServices.retrieveColumn(colName, false);
    }

    @GetMapping("/data/{colName}/{param}")
    public Vector<Object> retrieveDataColumn(@PathVariable String colName, @PathVariable String param)
    {
        boolean excludeNull = false;
        if (param.equals("excludeNull"))
        {
            excludeNull = true;
        }
        else
        {
            ///// errrrooreee /* TODO */
        }

        return DataCsvRowServices.retrieveColumn(colName, excludeNull);
    }

    @GetMapping(value = "/count/{fieldName}", produces = "application/json")
    public String count(@PathVariable String fieldName, @RequestParam(value = "value") String value)
    {
        FilterParameter filter = new FilterParameter(fieldName, "==", value);
        int count = DataCsvRowServices.filter(filter).size();
        return "{ \"count\": " + count + "}";
    }

    @GetMapping(value = "/search", produces = "application/json")
    public String search(@RequestParam(value = "value") String value)
    {
        DataCsvRowServices.search(value);
        return null;
    }

    @PostMapping(value = "/filter")
    public Vector<DataCsvRow> filter(@RequestBody String param)
    {
        JSONObject obj;
        FilterParameter filter = new FilterParameter();
        try
        {
            obj = new JSONObject(param);
        }
        catch (JSONException e)
        {
            /* TODO inserire errore json formattato male */
            return null;
        }

        // caso senza operatori
        try
        {
            filter.readFields(obj);
            return DataCsvRowServices.filter(filter);
        }
        catch (JSONException ignored) { }

        try
        {
            Iterator iterator = obj.keys();

            Vector<DataCsvRow> tmp = null;

            String operator = iterator.next().toString();

            JSONArray filters = (JSONArray) obj.get(operator);
            for (int i = 0; i < filters.length(); i++)
            {
                JSONObject objin = filters.getJSONObject(i);
                filter.readFields(objin);

                if (operator.equals("$or"))
                {
                    tmp = (tmp==null?(new Vector<>()):tmp);
                    tmp = DataCsvRowServices.or(tmp, DataCsvRowServices.filter(filter));
                }
                else if (operator.equals("$and"))
                {
                    tmp = (tmp==null?DataCsvRowServices.getCsvData():tmp);
                    tmp = DataCsvRowServices.and(tmp, DataCsvRowServices.filter(filter));
                }
            }

            return tmp;

        }
        catch (JSONException ignored) { }

        return null;
    }





}
