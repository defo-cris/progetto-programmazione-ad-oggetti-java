package com.example.progetto.Spring;

import com.example.progetto.csvClasses.dataType.Metadata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Vector;

@RestController
public class DataCsvRowController
{

    /**
     *
     */
    public DataCsvRowController()
    {
    }

    /**
     *
     *
     * @return
     */
    @GetMapping("/data")
    public Vector<DataCsvRow> retrieveData()
    {
        return DataCsvRowServices.getCsvData();
    }

    /**
     *
     *
     * @return
     */
    @GetMapping("/metadata")
    public Vector<Metadata> retrieveMetadata()
    {
        return DataCsvRowServices.getMetadata();
    }


    /**
     * Gives stats based on the class {@link NumberStats}. <strong>Note:</strong> the allowed fields are latitude and longitude.
     *
     * @param colName allowed: latitude or longitude TODO what?? su latitudine e longitudine non funziona nulla
     * @return average, minimum, maximum, standard deviation and sum
     */
    @GetMapping("/stats/{colName}")
    public NumberStats stats(@PathVariable String colName)
    {
        return DataCsvRowServices.stats(colName);
    }


    /**
     *
     *
     * @param colName
     * @param value
     * @return
     */
    @GetMapping("/data/{colName}")
    public Vector<Object> retrieveDataColumn(@PathVariable String colName, @RequestParam(value = "excludeNull", required = false) String value)
    {
        boolean excludeNull = false;
        if (value.replace("\"", "").equals("true"))
        {
            excludeNull = true;
        }

        return DataCsvRowServices.retrieveColumn(colName.replace("\"", ""), excludeNull);
    }

    /**
     *
     *
     * @param fieldName
     * @param value
     * @return
     */
    @GetMapping(value = "/count/{fieldName}", produces = "application/json")
    public String count(@PathVariable String fieldName, @RequestParam(value = "value") String value)
    {
        FilterParameter filter = new FilterParameter(fieldName, "==", value);
        int count = DataCsvRowServices.filter(filter).size();
        return "{ \"count\": " + count + "}";
    }

    /**
     *
     *
     * @param value
     * @param bool
     * @return
     */
    @GetMapping(value = "/search")
    public Vector<DataCsvRow> search(@RequestParam(value = "value") String value, @RequestParam(value = "exactMatch", required = false) String bool)
    {
        String param = "contains";
        Class type = CharSequence.class;
        if (bool.replace("\"", "").equals("true"))
        {
            param = "equals";
            type = Object.class;
        }

        return DataCsvRowServices.search(value.replace("\"", ""), param, type);
    }

    /**
     *
     *
     * @param param
     * @return
     */
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
        catch (JSONException ignored)
        {
        }

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
                    tmp = (tmp == null ? (new Vector<>()) : tmp);
                    tmp = DataCsvRowServices.or(tmp, DataCsvRowServices.filter(filter));
                }
                else if (operator.equals("$and"))
                {
                    tmp = (tmp == null ? DataCsvRowServices.getCsvData() : tmp);
                    tmp = DataCsvRowServices.and(tmp, DataCsvRowServices.filter(filter));
                }
                else
                {
                    /* TODO insert errrror */
                    //// errore
                }
            }

            return tmp;

        }
        catch (JSONException ignored)
        {
        }

        return null; /* TODO inserire errore */
    }


}
