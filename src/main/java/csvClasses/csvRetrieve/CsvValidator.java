package csvClasses.csvRetrieve;

import csvClasses.dataType.ObjArray;
import csvClasses.dataType.UrlWithDescription;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 */
public class CsvValidator
{
    /**
     * @param s
     * @param defaultValue
     * @return
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
     * @param s
     * @return
     */
    public static int validateID(String s)
    {
        return validateInt(s, -1);
    }

    /**
     * @param s
     * @return
     */
    public static int validateCurrency(String s)
    {
        return validateInt(s, 0);
    }

    /**
     * @param s
     * @return
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
     * @param s
     * @return
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
     * @param s
     * @return
     */
    public static String validateCounty(String s)
    {
        s = validateString(s);
        s = s.toUpperCase();
        return s;
    }


    /**
     * @param s
     * @param sep
     * @return
     */
    private static ObjArray<String> validateStringArray(String s, String sep)
    {
        /*
         * TODO
         *  handle
         *  --> Maritime House, 25 Marine Parade, null,
         * */
        s = validateString(s);
        return new ObjArray<>(s.split(sep));
    }

    /**
     * @param s
     * @return
     */
    public static ObjArray<String> validateStringArrayCommaSeparated(String s)
    {
        return validateStringArray(s, ", ");
    }

    /**
     * @param s
     * @return
     */
    public static ObjArray<String> validateStringArraySemicolonSeparated(String s)
    {
        return validateStringArray(s, ";");
    }

    /**
     * @param s
     * @return
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
     * @param s
     * @return
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
     * @param s
     * @return
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
     * @param s
     * @return
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
