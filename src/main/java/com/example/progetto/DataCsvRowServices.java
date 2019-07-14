package com.example.progetto;

import csvClasses.dataType.Metadata;
import org.springframework.stereotype.Component;

import java.util.Vector;

@Component
public class DataCsvRowServices
{
    static private Vector<Metadata> metadata;
    static private Vector<DataCsvRow> csvData;


    public Vector<Metadata> getMetadata()
    {
        return metadata;
    }

    public static void setMetadata(Vector<Metadata> metadata)
    {
        DataCsvRowServices.metadata = metadata;
    }

    public Vector<DataCsvRow> getCsvData()
    {
        return csvData;
    }

    public static void setCsvData(Vector<DataCsvRow> csvData)
    {
        DataCsvRowServices.csvData = csvData;
    }
}
