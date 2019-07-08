package com.example.progetto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;

public class CsvSplitter
{
    private GetCsvDataFromUrl csv;
    private ArrayList<String> commonSeparator = new ArrayList<>(Arrays.asList(";", ",", "|", "^"));
    private String sep;
    private String firstline;

    CsvSplitter(GetCsvDataFromUrl csv)
    {
        this.csv = csv;
        sep = "";
    }

    void guessDelimiter() throws IOException, DataFormatException
    {
        firstline = csv.getFirstLine();

        String line = csv.getLine();

        String[] columns;
        String[] data;

        for(String s: commonSeparator)
        {
            columns = firstline.split(s);

            data = line.split(s + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if ((columns.length == data.length) && (columns.length != 1))
            {
                csv.resetPosition();
                sep = s;
                return;
            }
        }
        throw new DataFormatException("impossible to guess the delimiter of the csv");
    }

    void addCommonSeparator(String sep)
    {
        commonSeparator.add(sep);
    }

    void setSep(String sep)
    {
        this.sep = sep;
    }

    String[] getFirstLine()
    {
        return firstline.split(sep + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    String[] splitLine() throws IOException
    {
        if (sep.equals("")) throw new IllegalStateException("you need to set or guess the separator first");
        System.out.println(sep);
        String[] split = csv.getLine().split(sep + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        PrintStringArray.print(split);
        return split;
    }

}
