package com.example.progetto;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.stream.Collectors;

public class ReadLineFromBufferedReader
{
    private BufferedReader br;
    private String endOfLineDelimiter;
    private int maxLineLength;
    CircularFifoBuffer buffer;




    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter, int maxLineLength)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;

        this.maxLineLength = maxLineLength;
        buffer = new CircularFifoBuffer(endOfLineDelimiter.length());
    }


    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;
        maxLineLength = 4096;
        buffer = new CircularFifoBuffer(endOfLineDelimiter.length());
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
                    String s = str.toString();
                    return s.substring(0, s.length() - endOfLineDelimiter.length() + 1);
                }
            }
            else
            {
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
