package csvClasses.dataType;

import java.util.Arrays;

public class StringArray
{
    private String[] data;

    public StringArray(String[] data)
    {
        this.data = data;
    }

    public String[] getData()
    {
        return data;
    }

    public void setData(String[] data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "StringArray{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
