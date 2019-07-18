package com.example.progetto.csvClasses.csvRetrieve;

import com.example.progetto.csvClasses.dataType.ObjArray;
import com.example.progetto.csvClasses.dataType.UrlWithDescription;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


/* TODO javadoc ok */

/**
 * class used to validate all the fields obtained in every csv row
 */
public class CsvValidator
{
    /**
     * function to parse an int and setting a default value if the parsing fails
     *
     * @param s            string that will be converted to int
     * @param defaultValue value to assign in the case of a string that cannot be parsed to an int
     *
     * @return the int correspondent to the String s
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
     * this method is specific to validate the ID column, that contain a positive int and so if the parse fail the default value will be -1
     *
     * @param s the string that will be converted to an int
     *
     * @return the result of the {@link CsvValidator#validateInt} method with -1 as default value
     */
    public static int validateID(String s)
    {
        return validateInt(s, -1);
    }

    /**
     * this method is used to validate and parse the columns that contain currency related value, the default value of this type of data is 0.
     *
     * @param s the string that will be converted to an int
     *
     * @return the result of the {@link #validateInt(String, int)} method with 0 as default value
     */
    public static int validateCurrency(String s)
    {
        return validateInt(s, 0);
    }

    /**
     * this method is used to validate a string, removing unwanted html tags and quotation marks.
     *
     * @param s the string that will be validated
     *
     * @return the string with some replacement to eliminate strange character, or "NULL" in case of a null string, so the default value of this type of fields is "NULL"
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
     * in case we need to check if an url is a correctly formatted we use this method, if the validation fail the default value is "NULL"
     *
     * @param s the url string that will be checked
     *
     * @return the url of "NULL"
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
     * this is used to validate the countries abbreviation/acronym (UK, IT, etc..), so the result will be a 2 char string all uppercase or the default value "NULL"
     *
     * @param s is the string to pass at the validate method
     *
     * @return the country acronym corresponding to the param <code>s</code>
     */
    private static String validateCountyAbbreviations(String s)
    {
        s = validateString(s);
        s = s.toUpperCase();
        if (s.length() != 2)
        {
            s = "NULL";
        }
        return s;
    }


    /**
     * method used to split a single line in more than one line and pass the result in an Object Array.
     *
     * @param s   is the string to pass at the validate method to obtain how result a String array
     * @param sep indicate the string to apply how separator of the split method
     *
     * @return the String array where the element will be the split string
     */
    private static ObjArray<String> validateStringArray(String s, String sep)
    {
        s = validateString(s);
        return new ObjArray<>(s.split(sep));
    }

    /**
	 * function to validate a string array separated by comma.
	 *
     * @param s string where the separator inside is the ","
     *
     * @return the ObjArray of the splitted string.
     */
    public static ObjArray<String> validateStringArrayCommaSeparated(String s)
    {
        return validateStringArray(s, ", ");
    }

    /**
	 * function to validate a string array separated by semicollon.
	 * 
     * @param s string where the separator inside is the ";"
     *
     * @return the ObjArray of the splitted string
     */
    public static ObjArray<String> validateStringArraySemicolonSeparated(String s)
    {
        return validateStringArray(s, ";");
    }

    /**
	 * this func
	 *
     * @param s string that contain all the countries and that are separated with ";"
     *
     * @return the String array of the split string
     */
    public static ObjArray<String> validateCountryArraySemicolonSeparated(String s)
    {
		/* TODO fin e' inutile al cazzo */
        String[] split = validateStringArray(s, ";").getData();
        String[] fin = new String[split.length];
        for (int i = 0; i < split.length; i++)
        {
            fin[i] = validateCountyAbbreviations(split[i]);
        }
        return new ObjArray<>(fin);
    }


    /**
	 * function to parse a float
	 *
     * @param s string used to be parsed
     *
     * @return in case of a correct number the float, or a NaN in case of bad format exception
     */
    private static float validateFloat(String s)
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
     *
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
	 * in the csv we have an url formatted as "<a href="url">"description"</a>" so in this function we split the url and the description.
	 *
     * @param s string that contain the url with the anchor of the link
     *
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
