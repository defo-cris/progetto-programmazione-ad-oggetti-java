package csvClasses.dataType;

public class UrlWithDescription
{
    private String url;
    private String description;

    public UrlWithDescription(String url, String description)
    {
        this.url = url;
        this.description = description;
    }

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
