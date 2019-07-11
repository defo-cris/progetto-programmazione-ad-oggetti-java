package com.example.progetto;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class ColumnDictionaryHandler
{
    /*
    * TODO
    *  make a method to append columns/data type on the go
    *  make a method to read the csv back into some data type
    *  make a verifier to check if some pair of columns/data type are duplicate
    *  make a method to remove a pair from the file (read the file, remove the line, rewrite the file)
    * */

    private String fileName;
    private Vector<String> columns;
    private Vector<String> dataTypes;


    public ColumnDictionaryHandler(String fileName)
    {
        this.fileName = fileName + ".csv";
        columns = new Vector<>();
        dataTypes = new Vector<>();
    }

    private void writeDictionary(Vector<String> columns, Vector<String> dataTypes, boolean append) throws IOException
    {
        try(FileWriter fileWriter = new FileWriter(fileName, append))
        {
            for (int i = 0; i < columns.size(); i++)
            {
                fileWriter.write(columns.get(i) + "," + dataTypes.get(i) + "\n");
            }
        }
    }

    public void overWriteDictionary(Vector<String> columns, Vector<String> dataTypes) throws IOException
    {
        if (columns.capacity() != dataTypes.capacity()) throw new IllegalArgumentException("the two arrays must have the same size");
        this.columns = columns;
        this.dataTypes = dataTypes;
        writeDictionary(columns, dataTypes, false);
    }

    public void appendWriteDictionary(Vector<String> columns, Vector<String> dataTypes) throws IOException
    {
        Vector<String> col = new Vector<>();
        Vector<String> data = new Vector<>();

        for (int i = 0; i < columns.size(); i++)
        {
            if(isPresent(columns.get(i),dataTypes.get(i)))
            {
                System.out.println("The element "+columns.get(i)+" is already present in the dictionary");
            }
            else
            {
                col.add(columns.get(i));
                data.add(dataTypes.get(i));
            }
        }
        writeDictionary(col, data, true);
    }

    public void readerDictionary() throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] split = sCurrentLine.split(",",2);
                String col = split[0];
                String data = split[1];
                columns.add(col);
                dataTypes.add(data);
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

}
