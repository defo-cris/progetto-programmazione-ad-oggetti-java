package com.example.progetto.Spring;

import com.example.progetto.csvClasses.DataCsv;
import com.example.progetto.csvClasses.dataType.Metadata;
import com.example.progetto.csvClasses.dataType.UrlWithDescription;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Vector;

/**
 * Contains the methods used by the controller or the service itself to manage the filters.
 */
@Component
public class DataCsvRowServices
{
    static private Vector<Metadata> metadata;
    static private Vector<DataCsvRow> csvData;

    /* TODO mettere un if per vedere se i dati ci sono davvero o sono null */


    static Vector<Metadata> getMetadata()
    {
        return metadata;
    }

    public static void setMetadata(Vector<Metadata> metadata)
    {
        DataCsvRowServices.metadata = metadata;
    }

    static Vector<DataCsvRow> getCsvData()
    {
        return csvData;
    }

    public static void setCsvData(Vector<DataCsvRow> csvData)
    {
        DataCsvRowServices.csvData = csvData;
    }

    /**
     * method used to validate if the column name insert in the url il valid
     *
     * @param name string of the column passed in postman
     * @return the name of the column or null in case {@param name} isn't correct
     */
    private static String checkColName(String name)
    {
        name = name.toLowerCase();
        for (String n : DataCsv.colNames)
        {
            if (n.toLowerCase().equals(name))
            {
                return n;
            }
        }
        return "NULL";
    }

    /**
     * method used to retrieve all the column, both with null values or without them
     *
     * @param name string of the column used to retrieve the data,
     * @param excludeNull boolean param used to specify if the null data will be displayed or not,
     * @return the vector of the column with or without the null values
     */
    static Vector<Object> retrieveColumn(String name, boolean excludeNull)
    {
        name = checkColName(name);

        Vector<Object> col = new Vector<>();
        try
        {
            for (DataCsvRow row : csvData)
            {
                Method getterMethod = row.getClass().getMethod("get" + name);
                Object data = getterMethod.invoke(row);
                if (data.getClass() == String.class)
                {
                    if (!data.equals("NULL") || !excludeNull)
                    {
                        col.add(data);
                    }
                } else
                {
                    col.add(data);
                }

            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "column name not valid, please write some of them: " + Arrays.toString(DataCsv.colNames));
        }
        return col;
    }


    /**
     * method used to calculate the statistics, based on {@link NumberStats}
     *
     * @param fieldName name of the field where to calculate the statistics, the accepted field are:totalProjectBudget and euBudgetContribution
     * @return all the statistics calculated on the field.
     */
    static NumberStats stats(String fieldName)
    {
        fieldName = checkColName(fieldName);

        int count;

        double avg;
        Integer min = null;
        Integer max = null;
        double std = 0;
        long sum = 0;

        count = csvData.size();

        int[] tmp = new int[count];
        int i = 0;
        try
        {
            for (DataCsvRow item : csvData)
            {
                Method m = item.getClass().getMethod("get" + fieldName);
                int data = (int) m.invoke(item);

                min = (min == null ? data : (min < data ? min : data));
                max = (max == null ? data : (max > data ? max : data));

                sum += data;

                tmp[i++] = data;
            }

            avg = sum / count;

            for (int xi : tmp)
            {
                std += Math.pow(xi - avg, 2);
            }

            std = Math.sqrt(std / count);
        }
        catch (IllegalAccessException | NoSuchMethodException | SecurityException | ClassCastException | InvocationTargetException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in the declaration of the route");
        }
        return new NumberStats(avg, min, max, std, sum);
    }


    /**
     * method used to validate and check the filter
     *
     * @param leftValue value of the column to compare at the value passed from postman
     * @param operator string that indicate the filter operation to do
     * @param rightValue value used to compare the data of the data-set
     * @return a boolean value
     */
    private static boolean checkFilterValidity(Object leftValue, String operator, Object rightValue)
    {
        if (leftValue.getClass() == Integer.class)
        {
            rightValue = Integer.parseInt((String) rightValue);
            switch (operator)
            {
                case "<":
                    return (int) leftValue < (int) rightValue;
                case "<=":
                    return (int) leftValue <= (int) rightValue;
                case ">":
                    return (int) leftValue > (int) rightValue;
                case ">=":
                    return (int) leftValue >= (int) rightValue;
                case "==":
                    return (int) leftValue == (int) rightValue;
                case "!=":
                    return (int) leftValue != (int) rightValue;
            }
        } else if ((leftValue.getClass() == String.class) && (rightValue.getClass() == String.class))
        {
            if (operator.equals("=="))
            {
                return leftValue.equals(rightValue);
            } else if (operator.equals("!="))
            {
                return !leftValue.equals(rightValue);
            }
        }
        return false;
    }

    /**
     * method used to filter the data-set and return only the row that meet the condition of the input value
     * and the operation. It use {@link #checkColName(String)} to verify the correctness of the column name
     * and the {@link #checkFilterValidity(Object, String, Object)} to verify the condition
     *
     * @param parameter an object that contains operator, value and field name
     * @return vector made by the data that met the conditions
     */
    static Vector<DataCsvRow> filter(FilterParameter parameter)
    {
        String colName = checkColName(parameter.getColName());
        Vector<DataCsvRow> out = new Vector<>();
        try
        {
            for (DataCsvRow row : csvData)
            {
                Method m = row.getClass().getMethod("get" + colName);
                Object data = m.invoke(row);
                if (checkFilterValidity(data, parameter.getOperator(), parameter.getValue()))
                {
                    out.add(row);
                }
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in the declaration of the route");
        }
        return out;
    }


    /**
     * method used to implement the logical operation AND in the filter
     *
     * @param a vector that contain or all the data-set or a portion of it
     * @param b vector that is used to filter the data-set
     * @return the vector with the filtered data
     */
    public static Vector<DataCsvRow> and(Vector<DataCsvRow> a, Vector<DataCsvRow> b)
    {
        Vector<DataCsvRow> c = new Vector<>();

        for (DataCsvRow d : a)
        {
            if (b.contains(d))
            {
                c.add(d);
            }
        }
        return c;
    }

    /**
     * method used to implement the logical operation OR in the filter
     *
     * @param a vector that contain or all the data-set or a portion of it
     * @param b vector that is used to filter the data-set
     * @return the vector with the filtered data
     */
    public static Vector<DataCsvRow> or(Vector<DataCsvRow> a, Vector<DataCsvRow> b)
    {
        for (DataCsvRow d : a)
        {
            if (!b.contains(d))
            {
                b.add(d);
            }
        }
        return b;
    }

    /**
     * method used in {@link #search(String, String, Class)} to add in the vector of the results only one time
     * the row of the data-set
     *
     * @param result vector used to store the results
     * @param row row of the data-set
     * @param data is the name of the field
     * @param value is the value of the field
     * @param method string used to identify the method to use
     * @param type is the class type
     * @throws NoSuchMethodException when a particular method cannot be found
     * @throws InvocationTargetException wraps an exception thrown by an invoked method or constructor
     * @throws IllegalAccessException when the currently executing method does
     *                                not have access to the definition of the
     *                                specified field
     */
    private static void compareAndAdd(Vector<DataCsvRow> result, DataCsvRow row, String data, String value, String method, Class type) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method m = String.class.getMethod(method, type);
        if ((boolean) m.invoke(data.toLowerCase(), value.toLowerCase()))
        {
            if (!result.contains(row))
            {
                result.add(row);
            }
        }
    }

    /**
     * method used to search in the data-set only the elements that contain the {@param value} inside them
     *
     * @param value string to research inside all the row
     * @param param string used to identify if the {@param value} is the exact match of the field
     * @param type class used to identify if the element is an object or a Char sequence
     * @return the vector with only the data that contain the {@param value}
     */
    static Vector<DataCsvRow> search(String value, String param, Class type)
    {
        Vector<DataCsvRow> result = new Vector<>();
        for (DataCsvRow row : csvData)
        {
            for (String fieldName : DataCsv.colNames)
            {
                try
                {
                    Method m = row.getClass().getMethod("get" + fieldName);
                    Object data = m.invoke(row);

                    if (data.getClass() == String.class)
                    {
                        compareAndAdd(result, row, (String) data, value, param, type);
                    } else if (data.getClass() == String[].class)
                    {
                        for (String s : (String[]) data)
                        {
                            compareAndAdd(result, row, s, value, param, type);
                        }
                    } else if (data.getClass() == UrlWithDescription[].class)
                    {
                        for (UrlWithDescription url : (UrlWithDescription[]) data)
                        {
                            compareAndAdd(result, row, url.getDescription(), value, param, type);
                        }
                    }

                }
                catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored)
                {
                }
            }
        }
        return result;
    }

}
