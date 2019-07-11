package com.example.progetto;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

@Deprecated
public class ColumnDictionaryHandler
{

    private String fileName;
    private Vector<String> columns;
    private Vector<String> dataTypes;
    private Vector<String> dataClass;


    public ColumnDictionaryHandler(String fileName)
    {
        this.fileName = fileName + ".csv";
        columns = new Vector<>();
        dataTypes = new Vector<>();
        dataClass = new Vector<>();
    }

    private void writeDictionary(Vector<String> columns, Vector<String> dataTypes, Vector<String> dataClass, boolean append) throws IOException
    {
        try (FileWriter fileWriter = new FileWriter(fileName, append))
        {
            for (int i = 0; i < columns.size(); i++)
            {
                fileWriter.write(columns.get(i) + "," + dataTypes.get(i) + "," + dataClass.get(i) + "\n");
            }
        }
    }

    public void overWriteDictionary(Vector<String> columns, Vector<String> dataTypes, Vector<String> dataClass) throws IOException
    {
        if (columns.capacity() != dataTypes.capacity())
            throw new IllegalArgumentException("the two arrays must have the same size");
        this.columns = columns;
        this.dataTypes = dataTypes;
        this.dataClass = dataClass;
        writeDictionary(columns, dataTypes, dataClass, false);
    }

    public void appendWriteDictionary(Vector<String> columns, Vector<String> dataTypes, Vector<String> dataClass) throws IOException
    {
        Vector<String> col = new Vector<>();
        Vector<String> data = new Vector<>();
        Vector<String> classes = new Vector<>();

        for (int i = 0; i < columns.size(); i++)
        {
            if (!isPresent(columns.get(i), dataTypes.get(i)))
            {
                col.add(columns.get(i));
                data.add(dataTypes.get(i));
                classes.add(dataClass.get(i));
            }
        }
        writeDictionary(col, data, classes, true);
    }

    public void readerDictionary() throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                String[] split = sCurrentLine.split(",", 3);
                columns.add(split[0]);
                dataTypes.add(split[1]);
                dataClass.add(split[2]);
            }
        }
    }

    private boolean isPresent(String col, String data)
    {
        int index;
        if ((index = columns.indexOf(col)) == -1)
        {
            return false;
        }
        if (dataTypes.get(index).equals(data))
        {
            return true;
        }
        throw new IllegalArgumentException("the column already exist but with a different data type");
    }

    public String getDataType(String columnName)
    {
        int index;
        if ((index = columns.indexOf(columnName)) == -1)
        {
            throw new IllegalArgumentException("column not present");
        }
        return dataTypes.get(index);
    }

    public String getDataClass(String columnName)
    {
        int index;
        if ((index = columns.indexOf(columnName)) == -1)
        {
            throw new IllegalArgumentException("column not present");
        }
        return dataClass.get(index);
    }

}
