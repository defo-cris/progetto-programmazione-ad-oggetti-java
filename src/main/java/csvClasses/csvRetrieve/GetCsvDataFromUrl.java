package csvClasses.csvRetrieve;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class GetCsvDataFromUrl
{
    private String url;
    private ReadLineFromBufferedReader br;

    public GetCsvDataFromUrl(String url)
    {
        this.url = url;
    }


    /**
     *
     *
     * @return
     * @throws IOException
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

    public String getLine() throws IOException
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

    public void resetPosition() throws IOException
    {
        br.reset();
    }

}
