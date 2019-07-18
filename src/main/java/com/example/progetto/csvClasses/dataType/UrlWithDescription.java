package com.example.progetto.csvClasses.dataType;

/**
 * class used in { DataCsvRow} passed how a type of the class ObjArray, so that every link passed how an element of the
 * array contain its description
 */
public class UrlWithDescription
{
    private String url;
    private String description;

    /**
     * Constructor of the class UrlWithDescription
     *
     * @param url         is the string that contain the url
     * @param description is the string that is contained between the html tag <a> </a>
     */
    public UrlWithDescription(String url, String description)
    {
        this.url = url;
        this.description = description;
    }

    /**
     * default constructor
     */
    public UrlWithDescription()
    {

    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    @Override
    public String toString()
    {
        return "UrlWithDescription{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
