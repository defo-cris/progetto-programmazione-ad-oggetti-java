package com.example.progetto;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class CsvValidator
{

    private static int validateInt(String s, int defaultValue)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }

    public static int validateID(String s)
    {
        return validateInt(s, -1);
    }

    public static int validateCurrency(String s)
    {
        return validateInt(s, 0);
    }

    public static String validateString(String s)
    {
        if (s.equals(""))
        {
            s = "NULL";
        }
        s = s.replaceAll("<.*?>","");
        return s;
    }

    public static String validateUrl(String s)
    {
        s = s.replaceAll(" ", "");
        try
        {
            new URL(s).toURI();
        }
        catch (MalformedURLException | URISyntaxException e)
        {
            return "NULL";
        }
        return s;
    }

    private static String[] validateStringArray(String s, String sep)
    {
        /*
        * TODO
        *  handle
        *  --> Maritime House, 25 Marine Parade, null,
        * */
        s = validateString(s);
        return s.split(sep);
    }

    public static String[] validateStringArrayCommaSeparated(String s)
    {
        return validateStringArray(s, ",");
    }

    public static String[] validateStringArraySemicolonSeparated(String s)
    {
        return validateStringArray(s, ";");
    }

    public static float validateFloat(String s)
    {
        try
        {
            return Float.parseFloat(s);
        }
        catch (NumberFormatException e)
        {
            return Float.NaN;
        }
    }

    public static float[] validateFloatArraySemicolonSeparated(String s)
    {
        String[] tmp = s.split(";");
        float[] floats = new float[tmp.length];

        for (int i = 0; i < tmp.length; i++)
        {
            floats[i] = validateFloat(tmp[i]);
        }
        return floats;
    }

    public static String[] validateUrlArraySemicolonSeparated(String s)
    {
        String[] tmp = s.split(";");

        for (int i = 0; i < tmp.length; i++)
        {
            tmp[i] = validateUrl(tmp[i]);
        }

        return tmp;
    }

}
