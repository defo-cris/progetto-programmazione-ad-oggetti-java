package com.example.progetto.csvClasses.dataType;


/**
 * class used in DataCsv class to generate the information of every element; that information contain the alias, the
 * sourceField and the type of the element
 */
@SuppressWarnings("unused")
public class Metadata
{
    private String alias;
    private String sourceField;
    private String type;

    /**
     * Constructor of the class Metadata
     *
     * @param alias       param's name used in the DataCsvRow class
     * @param sourceField is the param where to pass the element's name from the csv
     * @param type        is the param where we pass the type of the element
     */
    public Metadata(String alias, String sourceField, String type)
    {
        this.alias = alias;
        this.sourceField = sourceField;
        this.type = type;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getSourceField()
    {
        return sourceField;
    }

    public void setSourceField(String sourceField)
    {
        this.sourceField = sourceField;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


    @Override
    public String toString()
    {
        return "Metadata [alias=" + alias + ", sourceField=" + sourceField + ", type=" + type + "]";
    }

}
