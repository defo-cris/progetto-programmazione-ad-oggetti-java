package com.example.progetto.csvClasses.csvRetrieve;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


/**
 *
 */
public class ReadLineFromBufferedReader
{
    private BufferedReader br;
    private String endOfLineDelimiter;
    private int maxLineLength;
    CircularFifoBuffer buffer;


    /**
     * @param in
     * @param endOfLineDelimiter
     * @param maxLineLength
     */
    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter, int maxLineLength)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;

        this.maxLineLength = maxLineLength;
        buffer = new CircularFifoBuffer(endOfLineDelimiter.length());
    }


    /**
     * @param in
     * @param endOfLineDelimiter
     */
    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;
        maxLineLength = 8192;
        buffer = new CircularFifoBuffer(endOfLineDelimiter.length());
    }


    /**
     * @return
     * @throws IOException
     */
    public String readLine() throws IOException
    {
        StringBuilder str = new StringBuilder(maxLineLength);
        while (true)
        {
            int tmp = br.read();
            if (tmp != -1)
            {
                char c = (char)tmp;
                buffer.add(c);

                boolean flag = true;
                Object[] buff = buffer.toArray();
                char[] eol = endOfLineDelimiter.toCharArray();

                for (int i = 0; i < buff.length; i++)
                {
                    if ((char)buff[i] != eol[i])
                    {
                        flag = false;
                    }
                }

                if (!flag)
                {
                    str.append(c);
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }

        String s = str.toString();
        if (s.length() < endOfLineDelimiter.length() - 1)
        {
            return "";
        }
        s = s.substring(0, s.length() - endOfLineDelimiter.length() + 1);
        return s;

    }

    /**
     * @param i
     * @throws IOException
     */
    public void mark(int i) throws IOException
    {
        br.mark(i);
    }

    /**
     * @throws IOException
     */
    public void reset() throws IOException
    {
        br.reset();
    }

}
