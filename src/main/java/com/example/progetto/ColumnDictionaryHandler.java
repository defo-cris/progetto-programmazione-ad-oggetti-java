package com.example.progetto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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


    public ColumnDictionaryHandler(String fileName)
    {
        this.fileName = fileName + ".csv";
    }


    /*
    * now work in write mode, it should work in append mode
    * */
    public void writeDictionary(ArrayList<String> columns, ArrayList<String> dataTypes) throws IOException
    {
        if (columns.size() != dataTypes.size()) throw new IllegalArgumentException("the two arrays must have the same size");

        try(FileWriter fileWriter = new FileWriter(fileName))
        {
            Iterator colIter = columns.iterator();
            Iterator datIter = dataTypes.iterator();
            do
            {
                String c = (String) colIter.next();
                String d = (String) datIter.next();

                fileWriter.write(c + "," + d + "\n");
            }
            while ((colIter.hasNext()) && (datIter.hasNext()));
        }
    }

}
