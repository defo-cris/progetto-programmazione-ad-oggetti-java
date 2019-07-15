package com.example.progetto.Spring;

import csvClasses.dataType.Metadata;
import csvClasses.dataType.NumberStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


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
     * @param fieldName allowed: latitude or longitude
     * @return average, minimum, maximum, standard deviation and sum
     */
    @GetMapping("/stats/{fieldName}")
    public NumberStats stats(@PathVariable String fieldName)
    {
        return DataCsvRowServices.stats(fieldName);
    }

    @GetMapping("/data/{colName}")
    public Vector<Object> retrieveDataColumn(@PathVariable String colName)
    {
        return DataCsvRowServices.retrieveColumn(colName);
    }

}
