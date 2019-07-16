package com.example.progetto.csvClasses.csvRetrieve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;

/**
 *
 */
public class CsvSplitter
{
    private GetCsvDataFromUrl csv;
    private ArrayList<String> commonSeparator;
    private String sep;
    private String firstline;

    /**
     * @param csv
     */
    public CsvSplitter(GetCsvDataFromUrl csv)
    {
        this.csv = csv;
        sep = "";
        commonSeparator = new ArrayList<>(Arrays.asList(";", ",", "|", "^"));
    }

    /**
     * @throws IOException
     * @throws DataFormatException
     */
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

    /**
     * @param sep
     */
    void addCommonSeparator(String sep)
    {
        commonSeparator.add(sep);
    }

    /**
     * @param sep
     * @throws IOException
     */
    public void setDelimiter(String sep) throws IOException
    {
        firstline = csv.getFirstLine();
        this.sep = sep;
    }

    /**
     * @return
     */
    public String[] splitFirstLine()
    {
        return firstline.split(sep + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
    }

    /**
     * @return
     */
    public String[] splitLine()
    {

        if (sep.equals("")) throw new IllegalStateException("you need to set or guess the separator first");

        String otherThanQuote = " [^\"] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) "+ // enable comments, ignore white spaces
                        sep                         + // match a comma
                        "(?=                       "+ // start positive look ahead
                        "  (?:                     "+ //   start non-capturing group 1
                        "    %s*                   "+ //     match 'otherThanQuote' zero or more times
                        "    %s                    "+ //     match 'quotedString'
                        "  )*                      "+ //   end group 1 and repeat it zero or more times
                        "  %s*                     "+ //   match 'otherThanQuote'
                        "  $                       "+ // match the end of the string
                        ")                         ", // stop positive look ahead
                otherThanQuote, quotedString, otherThanQuote);

        try
        {
            return csv.getLine().split(regex);
        }
        catch (IOException e)
        {
            /*TODO delete this println*/
            System.out.println("IOException --> " + e);
            return null;
        }
    }

}