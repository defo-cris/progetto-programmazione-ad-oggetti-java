package com.example.progetto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class ReadLineFromBufferedReader
{
    private BufferedReader br;
    private String endOfLineDelimiter;
    private CharBuffer buffer;
    boolean eof;

    public ReadLineFromBufferedReader(Reader in, String endOfLineDelimiter)
    {
        br = new BufferedReader(in);
        this.endOfLineDelimiter = endOfLineDelimiter;
        buffer = CharBuffer.allocate(4096);
    }


    /* TODO fix this shit and make it to work
    *
    * Abstract:
    * this method should read an entire line of the BufferedReader.
    * the line is considered ended when we reach the endOfLineDelimiter.
    *
    * to do this we need to use br.read to read a fixed length of character then iter that bunch of char to
    * find if we get an endOfLineDelimiter, in this case we will need to patch up the array of char in a string
    * and return it. if a single read is not sufficient to reach an endOfLineDelimiter we must read another
    * bunch of character and search in that, when we reach a EOL (end of line) we must concatenate all the arrays
    * read before this point and return che correspondent string. if the EOL don't match the size of the array
    * used to read we will need to store the exceeded chars in a CharBuffer (or something like that) and concatenate
    * that with the reading of the new readLine.
    *
    * the effect must reflect the readLine() of BufferedReader
    *
    * */
    public String readLine() throws IOException
    {

        char[] tmp = new char[1024];
        if (br.read(tmp, 0, 1024) <= 1024)
        {
            eof = true;
        }

        int i;

        buffIter:
        for (i = 0; i < 1024; i++)
        {
            if (tmp[i] == '\n')
            {
                break buffIter;
            }
        }
        return null;

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
