package com.example.progetto.Spring;

import csvClasses.dataType.Metadata;
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

    @GetMapping("/data/{colName}")
    public Vector<Object> retrieveDataColumn(@PathVariable String colName)
    {
        return DataCsvRowServices.retrieveColumn(colName);
    }

}
