package com.example.progetto.csvClasses.csvRetrieve;

import com.example.progetto.csvClasses.dataType.ObjArray;
import com.example.progetto.csvClasses.dataType.UrlWithDescription;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * class used to validate all the data obtained in the csv
 */
public class CsvValidator
{
    /**
     * @param s string to pass at the parseInt method to return the integer
     * @param defaultValue value to assign in the case of a null string
     * @return or the integer value of the string or the default value in case of a null string
     */
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

    /**
     * @param s is the string of the Nid column
     * @return the result of the validateInt method where the string is the {@param s}
     * and the default value is -1.
     */
    public static int validateID(String s)
    {
        return validateInt(s, -1);
    }

    /**
     * @param s is the string of the euBudgetContribution or the totalProjectBudget column
     * @return the result of the validateInt method where the string is {@param s}
     * and the default value is 0.
     */
    public static int validateCurrency(String s)
    {
        return validateInt(s, 0);
    }

    /**
     * @param s is the string to pass at the method from every string type column
     * @return the string with some replacement to eliminate strange character, or NULL in case of a null string
     */
    public static String validateString(String s)
    {
        if (s.equals(""))
        {
            s = "NULL";
        }
        s = s.replaceAll("<.*?>", "");
        s = s.replaceAll("\"", "");
        s = s.trim();
        return s;
    }

    /**
     * @param s is the string of the Visual column
     * @return the url if the string is an url or NULL
     */
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

    /**
     * @param s is the string to pass at the validate method
     * @return the result of the validateString method of string s
     */
    public static String validateCounty(String s)
    {
        s = validateString(s);
        s = s.toUpperCase();
        return s;
    }


    /**
     * @param s is the string to pass at the validate method to obtain how result a String array
     * @param sep indicate the string to apply how separator of the split method
     * @return the String array where the element will be the split string
     */
    private static ObjArray<String> validateStringArray(String s, String sep)
    {
        /*
         * TODO
         *  handle
         *  --> Maritime House, 25 Marine Parade, null,
         * */
        s = validateString(s);that information contain the alias of the element, the sourceField 
        return new ObjArray<>(s.split(sep));
    }

    /**
     * @param s string where the separator inside is the ","
     * @return the String array of the split string.
     */
    public static ObjArray<String> validateStringArrayCommaSeparated(String s)
    {
        return validateStringArray(s, ", ");
    }

    /**
     * @param s string where the separator inside is the ";"
     * @return the String array of tthe split string
     */
    public static ObjArray<String> validateStringArraySemicolonSeparated(String s)
    {
        return validateStringArray(s, ";");
    }

    /**
     * @param s string that contain all the countries and that are separated with ";"
     * @return the String array of the split string
     */
    public static ObjArray<String> validateCountryArraySemicolonSeparated(String s)
    {
        String[] split = validateStringArray(s, ";").getData();
        String[] fin = new String[split.length];
        for (int i = 0; i < split.length; i++)
        {
            fin[i] = validateCounty(split[i]);
        }
        return new ObjArray<>(fin);
    }


    /**
     * @param s string used to obtain how result a float
     * @return in case of a correct number the float, or a NaN in case of bad format exception
     */
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

    /**
     * @param s string that is used to obtain how result an array of float
     * @return the array of float or NaN  
     */
    public static ObjArray<Float> validateFloatArraySemicolonSeparated(String s)
    {
        String[] tmp = s.split(";");
        Float[] floats = new Float[tmp.length];

        for (int i = 0; i < tmp.length; i++)
        {
            floats[i] = validateFloat(tmp[i]);
        }
        return new ObjArray<>(floats);
    }

    /**
     * @param s string that contain the url with the anchor of the link
     * @return an array with two element, the url and the description
     */
    public static ObjArray<UrlWithDescription> validateUrlArraySemicolonSeparated(String s)
    {
        String[] tmp = s.split(";");

        UrlWithDescription[] urls = new UrlWithDescription[tmp.length];

        for (int i = 0; i < tmp.length; i++)
        {
            urls[i] = new UrlWithDescription();
            urls[i].setDescription(validateString(tmp[i]));
            try
            {
                urls[i].setUrl(tmp[i].replace("\"<a href=\"", "").replace("<a href=\"", "").split("\"")[1]);
            }
            catch (Exception e)
            {
                urls[i].setUrl("NULL");
            }
        }

        return new ObjArray<>(urls);
    }

}
