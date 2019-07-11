package com.example.progetto;

import java.util.ArrayList;

public class PrintClass
{
    PrintClass()
    {

    }

    static void printFormatted(String[] col, String[] row)
    {
        System.out.println("\n");
        for (int i = 0; i < col.length; i++)
        {
            System.out.println(col[i] + " -> " + row[i]);
        }
        System.out.println("\n");
    }

    static void print(String[] arr)
    {
        for (String a: arr)
        {
            System.out.println(a);
        }
    }

    static void printArrayList(ArrayList a)
    {
        for (Object elem: a)
        {
            System.out.println(elem + "\n");
        }
    }
}
