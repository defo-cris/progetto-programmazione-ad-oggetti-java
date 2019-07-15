package csvClasses.dataType;

import java.util.ArrayList;
import java.util.Vector;

/**
 * class used to print in the console the formatted csv, a vector, an array list or an array
 */
public class PrintClass
{
    PrintClass()
    {

    }

    /**
     * @param col
     * @param row
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
     * @param arr
     */
    static void print(String[] arr)
    {
        for (String a: arr)
        {
            System.out.println(a);
        }
    }

    /**
     * @param a
     */
    static void printArrayList(ArrayList a)
    {
        for (Object elem: a)
        {
            System.out.println(elem + "\n");
        }
    }

    /**
     * @param a
     */
    static void printVector(Vector a)
    {
        for (Object elem: a)
        {
            System.out.println(elem + "\n");
        }
    }
}
