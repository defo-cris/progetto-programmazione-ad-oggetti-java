package com.example.progetto;

public class PrintStringArray
{
    PrintStringArray()
    {

    }

    static void printFormatted(String[] col, String[] row)
    {
        System.out.println("col.length -> " + col.length);
        System.out.println("row.length -> " + row.length);
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
}
