package com.example.progetto.Spring;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Class used to describe an elemental filter with a left value, represented by the name of the column that will be used
 * for the filter, an operator, such "==", and a right value that will be compared with every element of the column
 * corresponding to the name passed by the left value.
 */
@SuppressWarnings("unused")
public class FilterParameter
{
    private String colName;
    private String operator;
    private Object value;

    /**
     * constructor of the parameters
     *
     * @param colName  string of the element used for the filter
     * @param operator string used for the operation that the filter have to do, this may include "==", "&gt;", "&lt;",
     *                 ecc.
     * @param value    value used to compare all the data in the csv based on the operation to do
     */
    FilterParameter(String colName, String operator, Object value)
    {
        this.colName = colName;
        this.operator = operator;
        this.value = value;
    }

    /**
     * default constructor
     */
    FilterParameter()
    {
    }

    String getColName()
    {
        return colName;
    }

    void setColName(String colName)
    {
        this.colName = colName;
    }

    String getOperator()
    {
        return operator;
    }

    void setOperator(String operator)
    {
        this.operator = operator;
    }

    Object getValue()
    {
        return value;
    }

    void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * used to retrieve the parameters from the body request of postman formatted in JSON
     *
     * @param body is a JSONObject that contain the parameters fieldName, operator e value, enclosed in a json
     *
     * @throws JSONException in case the object obtained from postman isn't in JSON format
     */
    void readFields(JSONObject body) throws JSONException
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
