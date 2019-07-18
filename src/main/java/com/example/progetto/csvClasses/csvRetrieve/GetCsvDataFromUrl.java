package com.example.progetto.csvClasses.csvRetrieve;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/* TODO javadoc ok */

/**
 * class used to retrieve the data of the csv from the url obtained from the {@link GetCsvUrlFromJsonUrl} class
 */
@SuppressWarnings("WeakerAccess")
public class GetCsvDataFromUrl
{
    private String url;

    /**
     * a {@link java.io.BufferedReader} wrap that provide methods to set the EOL delimiter and a revisited readLine()
     */
    private ReadLineFromBufferedReader br;

    public GetCsvDataFromUrl(String url)
    {
        this.url = url;
    }


    /**
     * used to catch the first line of the csv from the {@link ReadLineFromBufferedReader} class
     *
     * @return the first line of the csv
     *
     * @throws IOException in case the file ended on the first line
     */
    public String getFirstLine() throws IOException
    {
        URL urlCSV = new URL(url);

        URLConnection urlConn = urlCSV.openConnection();

        br = new ReadLineFromBufferedReader(new InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8),
                                            "\r\n");

        String line;

        if ((line = br.readLine()) != null)
        {
            br.mark(999);
            return line;
        }
        throw new IOException("file ended too early");
    }

    /**
     * used to read the next line of the csv
     *
     * @return all the csv line apart of the first that we read in previous method
     *
     * @throws IOException           in case we reach the the end of the file.
     * @throws IllegalStateException if someone call this method before calling {@link GetCsvDataFromUrl#getFirstLine()}
     */
    String getLine() throws IOException
    {
        if (br == null)
        {
            throw new IllegalStateException("you need to read the first line first");
        }
        String line;
        if (!(line = br.readLine()).equals(""))
        {
            return line;
        }
        throw new EOFException("file reached the end");
    }

    /**
     * reset the position of the BufferReader to te last marked position
     *
     * @throws IOException in case of an error
     */
    void resetPosition() throws IOException
    {
        br.reset();
    }

}
