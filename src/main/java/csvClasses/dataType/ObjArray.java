package csvClasses.dataType;

import java.util.Arrays;

/**
 * @param <T>
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
