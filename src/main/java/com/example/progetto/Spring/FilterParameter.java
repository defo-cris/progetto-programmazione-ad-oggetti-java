package com.example.progetto.Spring;

import org.json.JSONException;
import org.json.JSONObject;

public class FilterParameter
{
    private String colName;
    private String operator;
    private Object value;

    public FilterParameter(String colName, String operator, Object value)
    {
        this.colName = colName;
        this.operator = operator;
        this.value = value;
    }

    public FilterParameter()
    {

    }

    public String getColName()
    {
        return colName;
    }

    public void setColName(String colName)
    {
        this.colName = colName;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public void readFields(JSONObject body) throws JSONException
    {

        colName = (String) body.get("fieldName");
        operator = (String) body.get("operator");
        value = body.get("value");

    }

    @Override
    public String toString()
    {
        return "FilterParameter{" +
                "colName='" + colName + '\'' +
                ", operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
