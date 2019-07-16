package com.example.progetto.csvClasses.csvRetrieve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;

/**
 * Class used to split all the row of the csv and to get all the data of the fields from the rows
 */
public class CsvSplitter
{
    private GetCsvDataFromUrl csv;
    private ArrayList<String> commonSeparator;
    private String sep;
    private String firstline;

    /**
	 * Constructor of the class CsvSplitter
	 *
     * @param csv
     */
    public CsvSplitter(GetCsvDataFromUrl csv)
    {
        this.csv = csv;
        sep = "";
        commonSeparator = new ArrayList<>(Arrays.asList(";", ",", "|", "^"));
    }

    /**
	 * method used to guess the delimiter of the csv's fields
	 *
     * @throws IOException
     * @throws DataFormatException in case the csv have a delimiter that are not in commonSeparator
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
     * @param sep parameter to add at the array with all the elements used to separator
     */
    void addCommonSeparator(String sep)
    {
        commonSeparator.add(sep);
    }

    /**
	 * method used to get the first line and to ste the separator 
	 *
     * @param sep separator to use how a split of the line
     * @throws IOException in case of error of I/O
     */
    public void setDelimiter(String sep) throws IOException
    {
        firstline = csv.getFirstLine();
        this.sep = sep;
    }

    /**
	 *method used to split the first line with the separator
	 *
     * @return the line how an array where all the element are obtained from the split of the string
     */
    public String[] splitFirstLine()
    {
        return firstline.split(sep + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
    }

    /**
	 * method used to splitthe line of the csv other than the first line
	 *
     * @return the array where every element will be field of the row
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
