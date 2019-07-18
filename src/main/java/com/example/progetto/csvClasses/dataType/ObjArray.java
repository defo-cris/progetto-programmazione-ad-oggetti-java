package com.example.progetto.csvClasses.dataType;

import java.util.Arrays;

/* TODO javadoc ok */

/**
 * class used to define an array of object
 *
 * @param <T> the type used to make the array
 */
public class ObjArray<T>
{
    private T[] data;

    public ObjArray(T[] data)
    {
        this.data = data;
    }

    public T[] getData()
    {
        return data;
    }

    public void setData(T[] data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ObjArray{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
