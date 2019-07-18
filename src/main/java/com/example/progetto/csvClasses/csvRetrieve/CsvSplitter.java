package com.example.progetto.csvClasses.csvRetrieve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;


/* TODO javadoc ok */

/**
 * Class used to split all the row of the csv and to get all the data of the fields from the rows
 */
public class CsvSplitter
{
    private GetCsvDataFromUrl csv;
    private ArrayList<String> commonSeparator;
    private String sep;
    private String firstLine;

    /**
     * Constructor of the class CsvSplitter
     *
     * @param csv the csv reader object that wrap a BufferedReader that read the csv data from the url
     */
    public CsvSplitter(GetCsvDataFromUrl csv)
    {
        this.csv = csv;
        sep = "";
        commonSeparator = new ArrayList<>(Arrays.asList(";", ",", "|", "^"));
    }

    /**
     * method used to guess the delimiter of the csv's fields the guessing is made with trying a set of common separator
     * and verify if splitting the first line and the second line result in the same amount of element
     *
     * @throws IOException         in case that the BufferedReader used in {@link GetCsvDataFromUrl} GetCsvDataFromUrl
     *                             fails
     * @throws DataFormatException in case the csv have a delimiter that are not in commonSeparator
     */
    void guessDelimiter() throws IOException, DataFormatException
    {
        firstLine = csv.getFirstLine();

        String line = csv.getLine();

        String[] columns;
        String[] data;

        for (String s : commonSeparator)
        {
            columns = firstLine.split(s);

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
     * add another separator to the collection of common separator
     *
     * @param sep parameter to add at the array with all the elements used to separator
     */
    void addCommonSeparator(String sep)
    {
        commonSeparator.add(sep);
    }

    /**
     * method used to get the first line and to set the separator, this method must be called before the method {@link @splitLine}.
     *
     * @param sep separator to use how a split of the line
     *
     * @throws IOException in case of error of I/O
     */
    public void setDelimiter(String sep) throws IOException
    {
        firstLine = csv.getFirstLine();
        this.sep = sep;
    }

    /**
     * method used to split the first line with the separator, calling this method does't influence the functionality of {@link @splitLine} because it use a stored first line and it does't re-read it from the csv.
     *
     * @return the line how an array where all the element are obtained from the split of the string
     */
    public String[] splitFirstLine()
    {
        return firstLine.split(sep + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }


    /**
     * method used to split the every line of the csv other than the first line, this method can be used as iterable that return null at the end
     *
     * @return the array where every element will be a field of the row. if this method return null it means that the
     * file reached the end or that something went wrong on reading the csv
     *
     * @throws IllegalStateException if this method is invoked before setting or guessing the separator of the csv
     */
    public String[] splitLine()
    {
        if (sep.equals(""))
        {
            throw new IllegalStateException("you need to set or guess the separator first");
        }

        String otherThanQuote = " [^\"] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) " + // enable comments, ignore white spaces
                                             sep + // match a comma
                                             "(?=                       " + // start positive look ahead
                                             "  (?:                     " + //   start non-capturing group 1
                                             "    %s*                   " + //     match 'otherThanQuote' zero or more times
                                             "    %s                    " + //     match 'quotedString'
                                             "  )*                      " + //   end group 1 and repeat it zero or more times
                                             "  %s*                     " + //   match 'otherThanQuote'
                                             "  $                       " + // match the end of the string
                                             ")                         ", // stop positive look ahead
                                     otherThanQuote, quotedString, otherThanQuote);

        try
        {
            return csv.getLine().split(regex);
        }
        catch (IOException e)
        {
            return null;
        }
    }

}
