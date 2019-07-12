package csvClasses.dataType;

import java.util.Arrays;

public class FloatArray
{
    private float[] data;

    public FloatArray(float[] data)
    {
        this.data = data;
    }

    public float[] getData()
    {
        return data;
    }

    public void setData(float[] data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "FloatArray{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
