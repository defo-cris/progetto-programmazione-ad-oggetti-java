package com.example.progetto.Spring;

import com.example.progetto.csvClasses.dataType.Metadata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Vector;


/**
 * Manages the Spring application and define the filters using GET or POST requests.
 */
@RestController
public class DataCsvRowController
{

    public DataCsvRowController()
    {
    }

    /**
     * method used to retrieve all the data-set. It use a GET request
     *
     * @return the data-set in a vector format
     */
    @GetMapping("/data")
    public Vector<DataCsvRow> retrieveData()
    {
        return DataCsvRowServices.getCsvData();
    }

    /**
     * this method retrieve the metadata, that contain the alias, the sourceField and the type of the element
     * It use a GET request
     *
     * @return the metadata of all the element in a vector format
     */
    @GetMapping("/metadata")
    public Vector<Metadata> retrieveMetadata()
    {
        return DataCsvRowServices.getMetadata();
    }


    /**
     * Gives stats based on the class {@link NumberStats} using a GET request.
     *
     * @param colName allowed: euBudgetContribution and totalProjectBudget
     * @return average, minimum, maximum, standard deviation and sum
     */
    @GetMapping("/stats/{colName}")
    public NumberStats stats(@PathVariable String colName)
    {
        return DataCsvRowServices.stats(colName);
    }


    /**
     * method used to retrieve the data of a single column; both with the null values, or without them
     * It use a GET request, but with the param excludeNull, used to choose if the null values
     * will be displayed or not
     *
     * @param colName name of the column picked to show
     * @param value param used to choose if the null values will be displayed or not
     * @return the data column in a vector format
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
     * method used to count the number of times the string of the specified field repeats itself.
     *
     * @param fieldName column in where to do the count of the attribute
     * @param value the string to count
     * @return the number of unique items
     */
    @GetMapping(value = "/count/{fieldName}", produces = "application/json")
    public String count(@PathVariable String fieldName, @RequestParam(value = "value") String value)
    {
        FilterParameter filter = new FilterParameter(fieldName, "==", value);
        int count = DataCsvRowServices.filter(filter).size();
        return "{ \"count\": " + count + "}";
    }

    /**
     * method used to search if the value passed in the {@param value} is contained in the ata-set,
     * and with the help of the {@param bool} it can identify if the {@param value} corresponds to
     * the entire line
     *
     * @param value string to search in all the data-set
     * @param bool string to specify if the {@param value} corresponds to the entire line
     * @return the vector of the results of the search
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
     * Generic filter using a POST. If the body of the JSON is a single object it
     * searches for a field, an operator and an input value and returns the filtered
     * dataset. If it is found an attribute called "$or" or "$and" it applies
     * multiple filters, using the following array of objects, based on the
     * attribute. The "$or" filter does a filter for each object and then unites
     * them without considering multiple elements. The "$and" filter just
     * recursively filter the result of the previous decimation.
     *
     * @param param SON array with objects composed by a field, an operator and an input value
     * @return the vector that result from the filter
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect JSON body");
        }

        //case without operator
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
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect JSON body");
                }
            }

            return tmp;

        }
        catch (JSONException ignored)
        {
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect JSON body");    }

}
