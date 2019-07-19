package com.example.progetto.csvClasses.csvRetrieve;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


/**
 * Class implemented because we couldn't use the method readline of the BufferedReader because in some of the row in
 * some field we had the character \n in the field, so the standard readline fails.
 */
public class ReadLineFromBufferedReader
{
    private BufferedReader br;
    private String endOfLineDelimiter;
    private int maxLineLength;
    private CircularFifoBuffer buffer;


    /**
     * Constructor of the class
     *
     * @param in                 is the {@link java.io.Reader} to pass at the reader for the character stream
     * @param endOfLineDelimiter is a string to identify the end of the line of a row
     * @param maxLineLength      int used in the StringBuilder to identify the dimension of the String
     */
    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter, int maxLineLength)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;

        this.maxLineLength = maxLineLength;
        buffer = new CircularFifoBuffer(endOfLineDelimiter.length());
    }


    /**
     * Constructor of the class with maxLineLength set to the default value.
     *
     * @param in                 streamer of character passed at the Reader
     * @param endOfLineDelimiter character that identify the end of the line
     */
    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;
        maxLineLength = 8192;
        buffer = new CircularFifoBuffer(endOfLineDelimiter.length());
    }


    /**
     * read the line from the buffered reader
     *
     * @return the line in string format
     *
     * @throws IOException in case of error
     */
    String readLine() throws IOException
    {
        StringBuilder str = new StringBuilder(maxLineLength);
        while (true)
        {
            int tmp = br.read();
            if (tmp != -1)
            {
                char c = (char) tmp;
                buffer.add(c);

                boolean flag = true;
                Object[] buff = buffer.toArray();
                char[] eol = endOfLineDelimiter.toCharArray();

                for (int i = 0; i < buff.length; i++)
                {
                    if ((char) buff[i] != eol[i])
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
     * method used to mark a position in the buffered reader
     *
     * @param i parameter to assign at the mark
     *
     * @throws IOException in case of error
     */
    void mark(int i) throws IOException
    {
        br.mark(i);
    }

    /**
     * method used to reset the buffered reader
     *
     * @throws IOException in case of error
     */
    void reset() throws IOException
    {
        br.reset();
    }

}
