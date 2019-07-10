package com.example.progetto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Queue;

public class ReadLineFromBufferedReader
{
    private BufferedReader br;
    private String endOfLineDelimiter;
    private int maxLineLength;


    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter, int maxLineLength)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;
        this.maxLineLength = maxLineLength;
    }


    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;
        maxLineLength = 4096;

    }


    /* TODO fix this shit and make it to work
    *
    * Abstract:
    *
    *
    * */
    public String readLine() throws IOException
    {
        StringBuilder str = new StringBuilder(maxLineLength);
        while (true)
        {
            int tmp = br.read();
            if (tmp > 255)
            {
                tmp = 63;
            }
            char c = (char)tmp;
            if ((c != '\n') && (tmp != -1))
            {
                str.append(c);
            }
            else
            {
                System.out.println("str.length() --->> " + str.length());
                return str.toString();
            }
        }

    }

    public void mark(int i) throws IOException
    {
        br.mark(i);
    }

    public void reset() throws IOException
    {
        br.reset();
    }

}
