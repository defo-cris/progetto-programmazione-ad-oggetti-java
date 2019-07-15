package com.example.progetto.Spring;

import com.example.progetto.csvClasses.*;
import com.example.progetto.csvClasses.dataType.Metadata;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Vector;

@Component
public class DataCsvRowServices
{
    static private Vector<Metadata> metadata;
    static private Vector<DataCsvRow> csvData;


    public static Vector<Metadata> getMetadata()
    {
        return metadata;
    }

    public static void setMetadata(Vector<Metadata> metadata)
    {
        DataCsvRowServices.metadata = metadata;
    }

    public static Vector<DataCsvRow> getCsvData()
    {
        return csvData;
    }

    public static void setCsvData(Vector<DataCsvRow> csvData)
    {
        DataCsvRowServices.csvData = csvData;
    }

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
        return "NULL"; // ce sta da inventasse qualcosa nel caso non trova il nome
    }

    public static Vector<Object> retrieveColumn(String name)
    {
        name = checkColName(name);

        Vector<Object> col = new Vector<>();
        try
        {
            for (DataCsvRow row : csvData)
            {
                Method getterMethod = row.getClass().getMethod("get" + name);
                col.add(getterMethod.invoke(row));
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            /* TODO cambiare messaggio di errore */
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nome colonna non valido, scrivi uno di questi: " + Arrays.toString(DataCsv.colNames));
        }
        return col;
    }


    /**
     * @param fieldName
     * @return
     */
    public static NumberStats stats(String fieldName)
    {
        fieldName = checkColName(fieldName);

        int count;

        double avg = 0;
        int min = 0;
        int max = 0;
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

                min = (min < data ? min : data);
                max = (max > data ? max : data);

                sum += data;

                System.out.println("sum  = " + sum);
                System.out.println("i  = " + i);

                tmp[i++] = data;
            }

            avg = sum / count;

            for (int xi: tmp)
            {
                std += Math.pow(xi-avg, 2);
            }

            std = Math.sqrt(std/count);
        }
        catch (IllegalAccessException | NoSuchMethodException | SecurityException | ClassCastException | InvocationTargetException e)
        {
            System.out.println("cazzo di errore"); /* TODO da sistemare il messaggio*/
        }
        return new NumberStats(avg, min, max, std, sum);
    }


    private static boolean checkFilterValidity(Object leftValue, String operator, Object rightValue)
    {
        if (leftValue.getClass() == Integer.class)
        {
            rightValue = Integer.parseInt((String)rightValue);
            switch (operator)
            {
                case "<":
                    return (int)leftValue < (int)rightValue;
                case "<=":
                    return (int)leftValue <= (int)rightValue;
                case ">":
                    return (int)leftValue > (int)rightValue;
                case ">=":
                    return (int)leftValue >= (int)rightValue;
                case "==":
                    return (int)leftValue == (int)rightValue;
            }
        }
        else if ((leftValue.getClass() == String.class) && (rightValue.getClass() == String.class))
        {
            if (operator.equals("=="))
            {
                return leftValue.equals(rightValue);
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public static Vector<DataCsvRow> filter(FilterParameter parameter)
    {
        String colName = checkColName(parameter.getColName());
        Vector<DataCsvRow> out = new Vector<>();
        try
        {
            for (DataCsvRow row: csvData)
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
            e.printStackTrace();
        }
        return out;
    }


}
