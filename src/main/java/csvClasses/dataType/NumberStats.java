package csvClasses.dataType;


/**
 *
 */
public class NumberStats
{
    private double avg;
    private int min;
    private int max;
    private double std;
    private long sum;

    /**
     * @param avg
     * @param min
     * @param max
     * @param std
     * @param sum
     */
    public NumberStats(double avg, int min, int max, double std, long sum) {
        this.avg = avg;
        this.min = min;
        this.max = max;
        this.std = std;
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getStd() {
        return std;
    }

    public void setStd(double std) {
        this.std = std;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

}
