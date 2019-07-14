package com.example.progetto.Spring;

import csvClasses.DataCsv;
import csvClasses.dataType.Metadata;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static Vector<Object> retrieveColumn(String name)
    {
        name = name.toLowerCase();
        for (String n: DataCsv.colNames)
        {
            if (n.toLowerCase().equals(name))
            {
                name = n;
                break;
            }
        }
        Vector<Object> col = new Vector<>();
        try
        {
            for (DataCsvRow row: csvData)
            {
                Method getterMethod = row.getClass().getMethod("get" + name);
                col.add(getterMethod.invoke(row));
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            /// display error message
        }
        return col;
    }

}
