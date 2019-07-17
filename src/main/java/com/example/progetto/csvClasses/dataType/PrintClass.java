package com.example.progetto.csvClasses.dataType;

import java.util.ArrayList;
import java.util.Vector;

/**
 * class used to print in the console the formatted csv, a vector, an array list or an array
 */
public class PrintClass
{
    /**
     *
     */
    PrintClass()
    {

    }

    /**
     *
     *
     * @param col is the param used to stamp the field name of the csv
     * @param row is the param used to stamp the value of the field for that row of the csv
     */
    static void printFormatted(String[] col, String[] row)
    {
        System.out.println("\n");
        for (int i = 0; i < col.length; i++)
        {
            System.out.println(col[i] + " -> " + row[i]);
        }
        System.out.println("\n");
    }

    /**
     *
     *
     * @param arr used to print in the console an array of data
     */
    static void print(String[] arr)
    {
        for (String a: arr)
        {
            System.out.println(a);
        }
    }

    /**
     *
     *
     * @param a used to stamp an ArrayList in the console
     */
    static void printArrayList(ArrayList a)
    {
        for (Object elem: a)
        {
            System.out.println(elem + "\n");
        }
    }

    /**
     *
     *
     * @param a used to stamp a Vector in the console
     */
    static void printVector(Vector a)
    {
        for (Object elem: a)
        {
            System.out.println(elem + "\n");
        }
    }
}
