package com.example.progetto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
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

    String[] splitFirstLine()
    {
        return firstline.split(sep + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    String[] splitLine() throws IOException
    {
        final AtomicInteger commacounter = new AtomicInteger();


        if (sep.equals("")) throw new IllegalStateException("you need to set or guess the separator first");
        System.out.println(sep);
        String[] split;
        String tmp = csv.getLine();



        char[] tmp1 = tmp.toCharArray();

        for (char a: tmp1)
        {
            if (a == '"')
            {
                commacounter.incrementAndGet();
            }
        }

        if ((tmp1[0] == '1') && (tmp1[1] == '0') && (tmp1[2] == '7'))
        {
            System.out.println("\n" + tmp + "\n");

            for (char a: tmp1)
            {
                System.out.println(a + " -> " + (int)(a));
            }

            System.out.println("there was " + commacounter.get() + " \"");
        }

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

        split = tmp.split(regex);
        return split;
    }

}
