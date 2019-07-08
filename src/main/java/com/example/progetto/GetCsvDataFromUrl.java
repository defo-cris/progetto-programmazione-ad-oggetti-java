package com.example.progetto;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class GetCsvDataFromUrl
{
    private String url;
    private BufferedReader br;

    GetCsvDataFromUrl(String url)
    {
        this.url = url;
    }


    /**
     *
     *
     * @return
     * @throws IOException
     */
    String getFirstLine() throws IOException
    {
        URL urlCSV = new URL(url);

        URLConnection urlConn = urlCSV.openConnection();

        InputStreamReader inputCSV = new InputStreamReader(urlConn.getInputStream());

        br = new BufferedReader(inputCSV);

        String line;

        if ((line = br.readLine()) != null)
        {
            return line;
        }
        throw new IOException("file ended too early");
    }

    String getLine() throws IOException
    {
        if (br == null)
        {
            throw new IllegalStateException("you need to read the first line first");
        }
        String line;
        if ((line = br.readLine()) != null)
        {
            return line;
        }
        throw new EOFException("file reached the end");
    }

    void resetPosition() throws IOException
    {
        br.mark(0);
        br.reset();
    }

}
