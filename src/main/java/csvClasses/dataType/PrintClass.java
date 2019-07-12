package csvClasses.dataType;

import java.util.ArrayList;
import java.util.Vector;

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

    static void printVector(Vector a)
    {
        for (Object elem: a)
        {
            System.out.println(elem + "\n");
        }
    }
}
